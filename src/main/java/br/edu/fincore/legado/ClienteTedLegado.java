package br.edu.fincore.legado;
public class ClienteTedLegado {
    public int transferir(String contaOrigem, String banco, String agencia, String conta, long centavos){
        return centavos > 500_000 ? 12 : 0;
    }
}
