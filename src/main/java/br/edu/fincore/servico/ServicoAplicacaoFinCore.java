package br.edu.fincore.servico;

import br.edu.fincore.legado.ClientePixLegado;
import br.edu.fincore.legado.ClienteTedLegado;
import br.edu.fincore.modelo.Conta;
import br.edu.fincore.modelo.Cartao;
import br.edu.fincore.modelo.Transacao;
import br.edu.fincore.repositorio.RepositorioContasEmMemoria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ServicoAplicacaoFinCore {
    private final RepositorioContasEmMemoria contas = new RepositorioContasEmMemoria();
    private final ClientePixLegado pix = new ClientePixLegado();
    private final ClienteTedLegado ted = new ClienteTedLegado();
    private final ServicoAntifraude antifraude = new ServicoAntifraude();
    private final ServicoNotificacao notificacoes = new ServicoNotificacao();
    private final List<Transacao> historico = new ArrayList<>();

    public void abrirConta(String numero, String nome, double saldoInicial, String tipo) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("conta");
        }
        contas.salvar(new Conta(numero, nome, new BigDecimal(saldoInicial), tipo));
        notificacoes.notificar("EMAIL", nome + "@email.local", "Conta criada: " + numero);
    }

    public Transacao transferir(String tipoTransferencia, String origem, String destino, double valor) {
        Conta contaOrigem = contas.buscar(origem);
        Conta contaDestino = contas.buscar(destino);
        if (contaOrigem == null) {
            throw new IllegalArgumentException("origem inexistente");
        }

        BigDecimal valorMonetario = new BigDecimal(valor);
        BigDecimal tarifa = calcularTarifa(tipoTransferencia, valorMonetario);
        Transacao transacao = new Transacao(
                UUID.randomUUID().toString(), origem, destino, tipoTransferencia, valorMonetario);
        historico.add(transacao);

        if (contaOrigem.bloqueada || contaOrigem.saldo.compareTo(valorMonetario.add(tarifa)) < 0) {
            transacao.status = "DENIED";
            registrarAlteracaoTransacao(transacao);
            return transacao;
        }
        if (!antifraude.aprovado(contaOrigem.nomeCliente, valorMonetario)) {
            transacao.status = "DENIED_FRAUD";
            registrarAlteracaoTransacao(transacao);
            return transacao;
        }

        // Problema intencional: o debito ocorre antes da confirmacao externa.
        // Nao existe compensacao automatica quando PIX/TED falha.
        contaOrigem.saldo = contaOrigem.saldo.subtract(valorMonetario).subtract(tarifa);
        if (contaDestino != null) {
            contaDestino.saldo = contaDestino.saldo.add(valorMonetario);
        }

        if ("PIX".equals(tipoTransferencia)) {
            Map<String, String> dados = new HashMap<>();
            dados.put("origem", origem);
            dados.put("destino", destino);
            dados.put("valor", String.valueOf(valor));
            String resultado = pix.enviarPixLegado(dados);
            transacao.status = resultado.startsWith("OK") ? "DONE" : "ERROR";
        } else if ("TED".equals(tipoTransferencia)) {
            int codigo = ted.transferir(origem, "999", "0001", destino, Math.round(valor * 100));
            transacao.status = codigo == 0 ? "DONE" : "ERROR_" + codigo;
        } else {
            transacao.status = "DONE";
        }

        registrarAlteracaoTransacao(transacao);
        notificacoes.notificar(
                "SMS",
                "62999990000",
                "Transferencia " + transacao.status + " no valor " + valor);
        return transacao;
    }

    // Problema intencional: regras de tarifa continuam centralizadas e comandadas por texto.
    private BigDecimal calcularTarifa(String tipoTransferencia, BigDecimal valorMonetario) {
        if ("PIX".equals(tipoTransferencia)) {
            return BigDecimal.ZERO;
        }
        if ("TED".equals(tipoTransferencia)) {
            return new BigDecimal("8.50");
        }
        return valorMonetario.multiply(new BigDecimal("0.01"));
    }

    // Problema intencional: criacao de cartao permanece condicional e dentro do mesmo servico.
    public Cartao emitirCartao(String conta, String tipo) {
        if ("BLACK".equals(tipo)) {
            return new Cartao(tipo, conta, 900.00);
        }
        if ("GOLD".equals(tipo)) {
            return new Cartao(tipo, conta, 350.00);
        }
        return new Cartao(tipo, conta, 120.00);
    }

    private void registrarAlteracaoTransacao(Transacao transacao) {
        // Ainda nao ha mecanismo desacoplado de eventos: a evidencia e apenas textual.
        System.out.println("AUDIT " + transacao.id + " -> " + transacao.status);
    }

    public RepositorioContasEmMemoria getContas() {
        return contas;
    }

    public List<Transacao> getHistorico() {
        return historico;
    }

    public ServicoAntifraude getAntifraude() {
        return antifraude;
    }

    public ServicoNotificacao getNotificacoes() {
        return notificacoes;
    }
}
