package br.edu.fincore.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transacao {
    public String id;
    public String fromConta;
    public String toConta;
    public String tipoTransferencia;
    public BigDecimal valor;
    public String status;
    public LocalDateTime criadaEm = LocalDateTime.now();

    public Transacao(String id, String fromConta, String toConta, String tipoTransferencia, BigDecimal valor) {
        this.id=id; this.fromConta=fromConta; this.toConta=toConta; this.tipoTransferencia=tipoTransferencia; this.valor=valor; this.status="CREATED";
    }
}
