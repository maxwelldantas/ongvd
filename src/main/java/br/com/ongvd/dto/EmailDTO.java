package br.com.ongvd.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDTO {
	
	@Email(message = "Não é um endereço de e-mail válido")
	@NotBlank(message = "Por favor preencha este campo")
	private String emailDe;
	private String emailPara;
	@NotBlank(message = "Por favor preencha este campo")
	private String assunto;
	@NotBlank(message = "Por favor preencha este campo")
	private String corpo;

	public String getEmailDe() {
		return emailDe;
	}

	public void setEmailDe(String emailDe) {
		this.emailDe = emailDe;
	}

	public String getEmailPara() {
		return emailPara;
	}

	public void setEmailPara(String emailPara) {
		this.emailPara = emailPara;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getCorpo() {
		return corpo;
	}

	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

}
