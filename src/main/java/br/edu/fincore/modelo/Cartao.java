package br.edu.fincore.modelo;
public class Cartao {
    public final String tipo;
    public final String conta;
    public final double anuidade;
    public Cartao(String tipo, String conta, double anuidade) { this.tipo=tipo; this.conta=conta; this.anuidade=anuidade; }
    @Override public String toString(){ return tipo+"/"+conta+" fee="+anuidade; }
}
