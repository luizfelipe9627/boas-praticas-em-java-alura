package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
    (S)OLID -> Single Responsibility Principle (SRP)

    - O princípio da responsabilidade única diz que uma classe deve ter apenas uma única responsabilidade, ou seja é uma classe deve ter apenas um objetivo.
    - O código abaixo está seguindo o princípio da responsabilidade única, pois a classe AbrigoService tem apenas a responsabilidade de lidar com as operações relacionadas a abrigos.
*/
public class AbrigoService {

    private final ClientHttpConfiguration client; // Cria uma variável do tipo ClientHttpConfiguration.

    // Criado um construtor para receber a instância de ClientHttpConfiguration.
    public AbrigoService(ClientHttpConfiguration client) {
        this.client = client; // Atribui a instância de ClientHttpConfiguration para a variável client.
    }

    // Método responsável por listar os abrigos cadastrados.
    public void listarAbrigo() throws IOException, InterruptedException {
        String uri = "http://localhost:8080/abrigos"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = client.dispararRequisicaoGet(uri); // Passa o cliente e a URI para o método dispararRequisicaoGet que retorna a resposta da requisição GET da API.
        String responseBody = response.body(); // Armazena o corpo da resposta da requisição GET.

        Abrigo[] abrigos = new ObjectMapper().readValue(responseBody, Abrigo[].class); // Converte o corpo da resposta para um array JSON do tipo Abrigo.
        List<Abrigo> abrigoLista = Arrays.stream(abrigos).toList(); // Converte o array JSON para uma lista e armazena na variável abrigoLista.

        System.out.println("Abrigos cadastrados:"); // Exibe a mensagem de abrigos cadastrados.

        // Percorre o array JSON e executa o bloco de código para cada elemento.
        for (Abrigo abrigo : abrigoLista) {
            long id = abrigo.getId(); // Pega o valor do campo "id" dentro da lista de abrigos.
            String nome = abrigo.getNome(); // Pega o valor do campo "nome" dentro da lista de abrigos.
            System.out.println(id + " - " + nome); // Exibe o id e o nome do abrigo.
        }
    }

    // Método responsável por cadastrar um novo abrigo.
    public void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:"); // Exibe a mensagem para o usuário digitar o nome do abrigo.
        String nome = new Scanner(System.in).nextLine(); // Lê o nome do abrigo e armazena na variável nome.

        System.out.println("Digite o telefone do abrigo:"); // Exibe a mensagem para o usuário digitar o telefone do abrigo.
        String telefone = new Scanner(System.in).nextLine(); // Lê o telefone do abrigo e armazena na variável telefone.

        System.out.println("Digite o email do abrigo:");  // Exibe a mensagem para o usuário digitar o email do abrigo.
        String email = new Scanner(System.in).nextLine(); // Lê o email do abrigo e armazena na variável email.

        Abrigo abrigo = new Abrigo(nome, telefone, email); // Cria um objeto do tipo Abrigo com o nome, telefone e email informados e armazena na variável abrigo.

        String uri = "http://localhost:8080/abrigos"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = client.dispararRequisicaoPost(uri, abrigo); // Passa o cliente, a URI e o objeto JSON para o método dispararRequisicaoPost que retorna a resposta da requisição POST da API.

        int statusCode = response.statusCode(); // Armazena o código de status da resposta da requisição POST.
        String responseBody = response.body(); // Armazena o corpo da resposta da requisição POST.

        // Verifica se o código de status é 200.
        if (statusCode == 200) {
            System.out.println("Abrigo cadastrado com sucesso!"); // Exibe a mensagem de sucesso.
            System.out.println(responseBody); // Exibe o corpo da resposta.
        }
        // Verifica se o código de status é 400 ou 500.
        else if (statusCode == 400 || statusCode == 500) {
            System.out.println("Erro ao cadastrar o abrigo:"); // Exibe a mensagem de erro.
            System.out.println(responseBody); // Exibe o corpo da resposta.
        }
    }

}
