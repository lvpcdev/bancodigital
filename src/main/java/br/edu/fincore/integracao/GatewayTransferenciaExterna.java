package br.edu.fincore.integracao;
import java.math.BigDecimal;
public interface GatewayTransferenciaExterna { String transferir(String contaOrigem, String contaDestino, BigDecimal valor); }
