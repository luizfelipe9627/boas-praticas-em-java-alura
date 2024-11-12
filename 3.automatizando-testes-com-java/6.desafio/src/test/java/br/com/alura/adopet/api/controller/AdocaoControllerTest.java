package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// Teste de integração -> O teste de integração é um tipo de teste que verifica se os componentes de um sistema funcionam corretamente quando são combinados. Ele testa a integração entre os componentes do sistema, como classes, módulos, banco de dados, etc.

// A anotação @SpringBootTest é usada para indicar que a classe de teste é uma classe de teste de integração e que o Spring Boot deve carregar o contexto da aplicação.
@SpringBootTest
// A anotação @AutoConfigureMockMvc é usada para indicar que o Spring Boot deve configurar o MockMvc.
@AutoConfigureMockMvc
class AdocaoControllerTest {
    // A anotação @Autowired é usada para injetar uma instância de uma classe em outra classe.
    @Autowired
    private MockMvc mockMvc; // O MockMvc é uma classe que simula o comportamento de um objeto real do tipo Mvc.

    // A anotação @MockBean é usada para criar um mock de um bean e injetá-lo no contexto da aplicação com o Spring Boot.
    @MockBean
    private AdocaoService adocaoService;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /adocoes retorna o código 400 quando é feita uma solicitação de adoção com erros.
    void deveriaDevolverCodigo400ParaSolicitacaoDeAdocaoComErros() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = "{}"; // Cria uma string vazia que simula um JSON inválido.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/adocoes") // Faz uma requisição POST para a rota /adocoes.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(400, response.getStatus()); // Verifica se o código de status retornado é 400.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /adocoes retorna o código 400 quando é feita uma solicitação de adoção com erros.
    void deveriaDevolverCodigo200ParaSolicitacaoDeAdocaoSemErros() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // Cria um JSON válido com os dados da solicitação de adoção.
        String json = """
                {
                     "idPet": 1,
                     "idTutor": 1,
                     "motivo": "Teste"
                }
                """;

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/adocoes") // Faz uma requisição POST para a rota /adocoes.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200.
    }
}