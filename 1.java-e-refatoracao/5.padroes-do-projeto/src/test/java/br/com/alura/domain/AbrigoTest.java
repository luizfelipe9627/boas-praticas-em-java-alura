package br.com.alura.domain;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AbrigoServiceTest {

    // A anotação @Mock é usada para criar um mock da classe ClientHttpConfiguration, ou seja, um objeto falso.
    @Mock
    private ClientHttpConfiguration client; // Cria uma variável do tipo ClientHttpConfiguration, responsável por fazer requisições HTTP.

    // A anotação @InjectMocks é usada para injetar o mock da classe ClientHttpConfiguration na classe AbrigoService.
    @InjectMocks
    private AbrigoService abrigoService; // Cria uma variável do tipo AbrigoService, responsável por lidar com as operações relacionadas a abrigos.

    // A anotação @Mock é usada para criar um mock da classe HttpResponse, ou seja, um objeto falso.
    @Mock
    private HttpResponse<String> response; // Cria uma variável do tipo HttpResponse<String>, responsável por armazenar a resposta da requisição HTTP.

    // Cria um objeto do tipo Abrigo com os valores já preenchidos.
    private final Abrigo abrigo = new Abrigo("Teste", "61981880392", "abrigo_alura@gmail.com");

    // A anotação @BeforeEach é usada para executar o método setUp() antes de cada teste.
    @BeforeEach
    // O método setUp() é responsável por inicializar os mocks.
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks.
    }

    // A anotação @Test é usada para indicar que o método é um teste.
    @Test
    // O método deveVerificarQuandoHaAbrigo() é responsável por testar o método listarAbrigo() quando há abrigo cadastrado.
    void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L); // Atribui o valor 0 para o campo "id" do objeto abrigo.
        String expectedAbrigosCadastrados = "Abrigos cadastrados:"; // Cria uma variável do tipo string com a mensagem "Abrigos cadastrados:".
        String expectedIdENome = "0 - Teste"; // Cria uma variável do tipo string com o id e o nome do abrigo.

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // Cria um objeto do tipo ByteArrayOutputStream, responsável por armazenar os dados de saída.
        PrintStream printStream = new PrintStream(byteArrayOutputStream); // Cria um objeto do tipo PrintStream.
        System.setOut(printStream); // Define o objeto printStream como a saída padrão.

        when(response.body()).thenReturn("[{" + abrigo.toString() + "}]"); // Quando o método body() for chamado, retorna o objeto abrigo convertido para string.
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response); // Quando o método dispararRequisicaoGet for chamado, retorna o objeto response.

        abrigoService.listarAbrigo(); // Chama o método listarAbrigo().

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator()); // Divide a saída em linhas.
        String actualAbrigosCadastrados = lines[0]; // Armazena a primeira linha da saída.
        String actualIdENome = lines[1]; // Armazena a segunda linha da saída.

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados); // Verifica se a mensagem de abrigos cadastrados é igual a esperada.
        Assertions.assertEquals(expectedIdENome, actualIdENome); // Verifica se o id e o nome do abrigo são iguais aos esperados.
    }

    // A anotação @Test é usada para indicar que o método é um teste.
    @Test
    // O método deveVerificarQuandoNaoHaAbrigo() é responsável por testar o método listarAbrigo() quando não há abrigo cadastrado.
    void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L); // Atribui o valor 0 para o campo "id" do objeto abrigo.
        String expected = "Não há abrigos cadastrados!"; // Cria uma variável do tipo string com a mensagem "Não há abrigos cadastrados!".

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // Cria um objeto do tipo ByteArrayOutputStream, responsável por armazenar os dados de saída.
        PrintStream printStream = new PrintStream(byteArrayOutputStream); // Cria um objeto do tipo PrintStream.
        System.setOut(printStream); // Define o objeto printStream como a saída padrão.

        when(response.body()).thenReturn("[]"); // Quando o método body() for chamado, retorna um array JSON vazio.
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response); // Quando o método dispararRequisicaoGet for chamado, retorna o objeto response.

        abrigoService.listarAbrigo(); // Chama o método listarAbrigo().

        String[] lines = byteArrayOutputStream.toString().split(System.lineSeparator()); // Divide a saída em linhas.
        String actual = lines[0]; // Armazena a primeira linha da saída.

        Assertions.assertEquals(expected, actual); // Verifica se a mensagem de abrigos cadastrados é igual a esperada.
    }

}