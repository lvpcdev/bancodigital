package br.edu.fincore;

import br.edu.fincore.modelo.Transacao;
import br.edu.fincore.servico.ServicoAplicacaoFinCore;

public class Main {
    public static void main(String[] args) {
        ServicoAplicacaoFinCore banco = new ServicoAplicacaoFinCore();
        banco.abrirConta("1001", "Ana", 1500.00, "CHECKING");
        banco.abrirConta("2002", "Bruno", 500.00, "CHECKING");

        Transacao t1 = banco.transferir("PIX", "1001", "2002", 120.00);
        Transacao t2 = banco.transferir("TED", "1001", "2002", 80.00);

        System.out.println("PIX=" + t1.status + " TED=" + t2.status);
        System.out.println("Saldo Ana=" + banco.getContas().buscar("1001").saldo);
        System.out.println("Saldo Bruno=" + banco.getContas().buscar("2002").saldo);
    }
}
