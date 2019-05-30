package br.com.ongvd.service;

public interface EmailService {
	
    void enviarTexto(String emailDe, String emailPara, String assunto, String corpo);
    void enviarHTML(String emailDe, String emailPara, String assunto, String corpo);
}
