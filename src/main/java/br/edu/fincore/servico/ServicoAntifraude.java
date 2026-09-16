package br.edu.fincore.servico;
import java.math.BigDecimal;
public class ServicoAntifraude {
    public boolean aprovado(String nomeCliente, BigDecimal valor){
        if(nomeCliente != null && nomeCliente.toLowerCase().contains("teste")) return false;
        return valor.doubleValue() <= 10000.0;
    }
}
