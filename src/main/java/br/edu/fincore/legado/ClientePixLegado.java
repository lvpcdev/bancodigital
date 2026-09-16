package br.edu.fincore.legado;
import java.util.*;
public class ClientePixLegado {
    public String enviarPixLegado(Map<String,String> dados){
        if(dados.get("destino")==null) return "ERRO_91";
        return "OK_PIX_"+System.currentTimeMillis();
    }
}
