package br.edu.fincore.modelo;

import java.math.BigDecimal;

public class Conta {
    public String numero;
    public String nomeCliente;
    public BigDecimal saldo;
    public String tipo;
    public boolean bloqueada;

    public Conta(String numero, String nomeCliente, BigDecimal saldo, String tipo) {
        this.numero = numero; this.nomeCliente = nomeCliente; this.saldo = saldo; this.tipo = tipo;
    }
}
