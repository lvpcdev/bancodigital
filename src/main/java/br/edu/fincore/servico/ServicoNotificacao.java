package br.edu.fincore.servico;
import br.edu.fincore.legado.*;
public class ServicoNotificacao {
    private final GatewaySmsLegado sms = new GatewaySmsLegado();
    private final ApiWhatsappLegada whats = new ApiWhatsappLegada();
    public void notificar(String canal, String destinatario, String mensagem){
        if("EMAIL".equals(canal)) System.out.println("EMAIL "+destinatario+": "+mensagem);
        else if("SMS".equals(canal)) sms.enviarSms(destinatario,mensagem);
        else if("WHATSAPP".equals(canal)) whats.enviar("{destino:'"+destinatario+"',msg:'"+mensagem+"'}");
        else System.out.println("CANAL DESCONHECIDO: "+canal);
    }
}
