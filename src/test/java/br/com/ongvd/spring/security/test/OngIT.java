package br.com.ongvd.spring.security.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@ActiveProfiles("it")
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class OngIT {
	@Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationAccountExists() throws Exception {
        this.mockMvc
                .perform(
                        post("/ong/registro")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("areaDeAtuacao", "Adolescentes e Crianças")
                                .param("ativo", "true")
                                .param("cnpj", "16.908.250/0001-64")
                                .param("contato", "Maxwell")
                                .param("email", "maxwelldsouza@hotmail.com")
                                .param("confirmarEmail", "maxwelldsouza@hotmail.com")
                                .param("fundacao", "2009-12-12")
                                .param("nomeFantasia", "Missão Africa")
                                .param("razaoSocial", "Missão Africa")
                                .param("responsavel", "Maxwell")
                                .param("senha", "123456")
                                .param("confirmarSenha", "123456")
                                .param("telefone", "(34) 99204-0640")
                                .param("website", "www.missaoafrica.org.br")
                                .param("whatsapp", "+55 (34) 9204-0640")
                                .param("bairro", "Planalto")
                                .param("cep", "38413-195")
                                .param("cidade", "Uberlândia")
                                .param("complemento", "")
                                .param("logradouro", "Rua do Carteiro")
                                .param("numero", "8686")
                                .param("uf", "MG")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("ong", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/ong/registro")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("areaDeAtuacao", "Adolescentes e Crianças")
                                .param("ativo", "true")
                                .param("cnpj", "16.908.250/0001-64")
                                .param("contato", "Maxwell")
                                .param("email", "maxwelldsouza@hotmail.com")
                                .param("confirmarEmail", "maxwelldsouza@hotmail.com")
                                .param("fundacao", "2009-12-12")
                                .param("nomeFantasia", "Missão Africa")
                                .param("razaoSocial", "Missão Africa")
                                .param("responsavel", "Maxwell")
                                .param("senha", "123456")
                                .param("confirmarSenha", "654321")
                                .param("telefone", "(34) 99204-0640")
                                .param("website", "www.missaoafrica.org.br")
                                .param("whatsapp", "+55 (34) 9204-0640")
                                .param("bairro", "Planalto")
                                .param("cep", "38413-195")
                                .param("cidade", "Uberlândia")
                                .param("complemento", "")
                                .param("logradouro", "Rua do Carteiro")
                                .param("numero", "8686")
                                .param("uf", "MG")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("ong"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationEmailNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/ong/registro")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("areaDeAtuacao", "Adolescentes e Crianças")
                                .param("ativo", "true")
                                .param("cnpj", "16.908.250/0001-64")
                                .param("contato", "Maxwell")
                                .param("email", "maxwelldsouza@hotmail.com")
                                .param("confirmarEmail", "diferente@hotmail.com")
                                .param("fundacao", "2009-12-12")
                                .param("nomeFantasia", "Missão Africa")
                                .param("razaoSocial", "Missão Africa")
                                .param("responsavel", "Maxwell")
                                .param("senha", "123456")
                                .param("confirmarSenha", "123456")
                                .param("telefone", "(34) 99204-0640")
                                .param("website", "www.missaoafrica.org.br")
                                .param("whatsapp", "+55 (34) 9204-0640")
                                .param("bairro", "Planalto")
                                .param("cep", "38413-195")
                                .param("cidade", "Uberlândia")
                                .param("complemento", "")
                                .param("logradouro", "Rua do Carteiro")
                                .param("numero", "8686")
                                .param("uf", "MG")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("ong"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/ong/registro")
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("areaDeAtuacao", "Adolescentes e Crianças")
                                .param("ativo", "true")
                                .param("cnpj", "16.908.250/0001-64")
                                .param("contato", "Maxwell")
                                .param("email", "maxwelldsouza@hotmail.com")
                                .param("confirmarEmail", "maxwelldsouza@hotmail.com")
                                .param("fundacao", "2009-12-12")
                                .param("nomeFantasia", "Missão Africa")
                                .param("razaoSocial", "Missão Africa")
                                .param("responsavel", "Maxwell")
                                .param("senha", "123456")
                                .param("confirmarSenha", "123456")
                                .param("telefone", "(34) 99204-0640")
                                .param("website", "www.missaoafrica.org.br")
                                .param("whatsapp", "+55 (34) 9204-0640")
                                .param("bairro", "Planalto")
                                .param("cep", "38413-195")
                                .param("cidade", "Uberlândia")
                                .param("complemento", "")
                                .param("logradouro", "Rua do Carteiro")
                                .param("numero", "8686")
                                .param("uf", "MG")
                )
                .andExpect(redirectedUrl("/home?ong"))
                .andExpect(status().is3xxRedirection());
    }
}
