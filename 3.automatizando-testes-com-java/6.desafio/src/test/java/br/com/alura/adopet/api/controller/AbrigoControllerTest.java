package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.EmailService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// Teste de integração -> O teste de integração é um tipo de teste que verifica se os componentes de um sistema funcionam corretamente quando são combinados. Ele testa a integração entre os componentes do sistema, como classes, módulos, banco de dados, etc.

// A anotação @SpringBootTest é usada para indicar que a classe de teste é uma classe de teste de integração e que o Spring Boot deve carregar o contexto da aplicação.
@SpringBootTest
// A anotação @AutoConfigureMockMvc é usada para indicar que o Spring Boot deve configurar o MockMvc.
@AutoConfigureMockMvc
// A anotação @ActiveProfiles é usada para indicar que o perfil de execução do teste é o perfil de teste, esse perfil faz com que o Spring Boot use o banco de dados H2 em memória.
@ActiveProfiles("test")
class AbrigoControllerTest {
    // A anotação @Autowired é usada para injetar uma instância de uma classe em outra classe.
    @Autowired
    private MockMvc mockMvc; // O MockMvc é uma classe que simula o comportamento de um objeto real do tipo Mvc.

    // A anotação @MockBean é usada para criar um mock de um bean e injetá-lo no contexto da aplicação com o Spring Boot.
    @MockBean
    private AbrigoService abrigoService;
    @MockBean
    private PetService petService;
    @MockBean
    private Abrigo abrigo;
    @MockBean
    private EmailService emailService;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /abrigos retorna o código 200 quando é feita a listagem de abrigos.
    void deveriaDevolverCodigo200ParaListagemDeAbrigos() throws Exception {
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                get("/abrigos") // Faz uma requisição GET para a rota /abrigos.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }


    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /abrigos retorna o código 200 quando é feito o cadastro de um abrigo com um JSON válido.
    void deveriaDevolverCodigo200ParaCadastroDeAbrigoValido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.

        String json = """
                    {
                        "nome": "Abrigo xpto",
                        "telefone": "61977777777",
                        "email": "abrigoxpto@email.com.br"
                    }
                """; // Cria um JSON válido com os dados do abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos") // Faz uma requisição POST para a rota /abrigos.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /abrigos retorna o código 400 quando é feito o cadastro de um abrigo com um JSON inválido.
    void deveriaDevolverCodigo400ParaCadastroDeAbrigoInvalido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = "{}"; // Cria uma string vazia que simula um JSON inválido.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos") // Faz uma requisição POST para a rota /abrigos.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(400, response.getStatus()); // Verifica se o código de status retornado é 400.
    }


    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feita a listagem de pets passando um id válido.
    void deveriaDevolverCodigo200ParaListagemDePetsComIdValido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String id = "1"; // Cria uma string com o id do abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                get("/abrigos/{id}/pets", id) // Faz uma requisição GET para a rota /abrigos/{id}/pets passando o id do abrigo.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 400 quando é feita a listagem de pets de um abrigo passando um id inválido.
    void deveriaDevolverCodigo404ParaListagemDePetsComIdInvalido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String id = "2"; // Cria uma string com o id do abrigo.

        given(abrigoService.listar(id)).willThrow(ValidacaoException.class); // Simula uma exceção ao chamar o método listarPetsDoAbrigo do abrigoService.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                get("/abrigos/{id}/pets", id) // Faz uma requisição GET para a rota /abrigos/{id}/pets passando o id do abrigo.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(404, response.getStatus()); // Verifica se o código de status retornado é 404, ou seja, não encontrado.
    }


    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feita a listagem de pets passando um nome válido.
    void deveriaDevolverCodigo200ParaListagemDePetsComNomeValido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String name = "bidu"; // Cria uma string com o nome do pet.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                get("/abrigos/{name}/pets", name) // Faz uma requisição GET para a rota /abrigos/{name}/pets passando o nome do abrigo.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 404 quando é feita a listagem de pets usando um nome inválido.
    void deveriaDevolverCodigo404ParaListagemDePetsComNomeInvalido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String name = "bidu"; // Cria uma string com o nome do pet.

        given(abrigoService.listar(name)).willThrow(ValidacaoException.class); // Simula uma exceção ao chamar o método listarPetsDoAbrigo do abrigoService.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                get("/abrigos/{name}/pets", name) // Faz uma requisição GET para a rota /abrigos/{name}/pets passando o nome do abrigo.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(404, response.getStatus()); // Verifica se o código de status retornado é 404, ou seja, não encontrado.
    }


    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feito o cadastro de um pet com um JSON e um nome válido.
    void deveriaDevolverCodigo200ParaCadastroDePetComNomeValido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = """
                    {
                        "tipo": "CACHORRO",
                        "nome": "bidu",
                        "raca": "SRD",
                        "idade": 5,
                        "cor": "preto",
                        "peso": 5.45
                    }
                """; // Cria um JSON válido com os dados do pet.

        String name = "bidu"; // Cria uma string com o nome do pet.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos/{name}/pets", name) // Faz uma requisição POST para a rota /abrigos/{name}/pets passando o nome do pet.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feito o cadastro de um pet com um JSON e um nome inválido.
    void deveriaDevolverCodigo400ParaCadastroDePetComNomeInvalido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = "{}"; // Cria uma string vazia que simula um JSON inválido.

        String name = "bidu"; // Cria uma string com o nome do pet.

        given(abrigoService.carregarAbrigo(name)).willThrow(ValidacaoException.class); // Simula uma exceção ao chamar o método carregarAbrigo do abrigoService.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos/{name}/pets", name) // Faz uma requisição POST para a rota /abrigos/{name}/pets passando o nome do pet.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(400, response.getStatus()); // Verifica se o código de status retornado é 400, ou seja, erro.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feito o cadastro de um pet com um JSON válido e um id válido.
    void deveriaDevolverCodigo200ParaCadastroDePetComIdValido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = """
                    {
                        "tipo": "CACHORRO",
                        "nome": "bidu",
                        "raca": "SRD",
                        "idade": 5,
                        "cor": "preto",
                        "peso": 5.45
                    }
                """; // Cria um JSON válido com os dados do pet.

        String name = "1"; // Cria uma string com o id do pet.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos/{name}/pets", name) // Faz uma requisição POST para a rota /abrigos/{name}/pets passando o nome do pet.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(200, response.getStatus()); // Verifica se o código de status retornado é 200 ou seja, sucesso.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é responsável por testar se a rota /{idOuNome}/pets retorna o código 200 quando é feito o cadastro de um pet com um JSON e um id inválido.
    void deveriaDevolverCodigo200ParaCadastroDePetComIdInvalido() throws Exception {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String json = "{}"; // Cria um JSON válido com os dados do pet.

        String id = "3"; // Cria uma string com o id do pet.

        given(abrigoService.carregarAbrigo(id)).willThrow(ValidacaoException.class); // Simula uma exceção ao chamar o método carregarAbrigo do abrigoService.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método perform do mockMvc é usado para fazer uma requisição HTTP.
        var response = mockMvc.perform(
                post("/abrigos/{id}/pets", id) // Faz uma requisição POST para a rota /abrigos/{id}/pets passando o id do pet.
                        .contentType("application/json") // Define o tipo de conteúdo da requisição como application/json.
                        .content(json)// Define o conteúdo da requisição como o JSON criado acima.
        ).andReturn().getResponse(); // Retorna a resposta da requisição e armazena na variável response.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(400, response.getStatus()); // Verifica se o código de status retornado é 400, ou seja, erro.
    }
}
