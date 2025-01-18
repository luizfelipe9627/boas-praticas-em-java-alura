package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

/*
    (S)OLID -> Single Responsibility Principle (SRP)

    - O princípio da responsabilidade única diz que uma classe deve ter apenas uma única responsabilidade, ou seja é uma classe deve ter apenas um objetivo.
    - O código abaixo está seguindo o princípio da responsabilidade única, pois a classe PetService tem apenas a responsabilidade de lidar com as operações relacionadas a pets.
*/
public class PetService {

    private final ClientHttpConfiguration client; // Cria uma variável do tipo ClientHttpConfiguration.

    // Criado um construtor para receber a instância de ClientHttpConfiguration.
    public PetService(ClientHttpConfiguration client) {
        this.client = client; // Atribui a instância de ClientHttpConfiguration para a variável client.
    }


    // Método responsável por listar os pets de um abrigo.
    public void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:"); // Exibe a mensagem para o usuário digitar o id ou nome do abrigo.
        String idOuNome = new Scanner(System.in).nextLine(); // Lê o id ou nome do abrigo e armazena na variável idOuNome.

        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = client.dispararRequisicaoGet(uri); // Passa o cliente e a URI para o método dispararRequisicaoGet que retorna a resposta da requisição GET da API.

        int statusCode = response.statusCode(); // Armazena o código de status da resposta da requisição GET.

        // Verifica se o código de status é 404 ou 500.
        if (statusCode == 404 || statusCode == 500) {
            System.out.println("ID ou nome não cadastrado!"); // Exibe a mensagem de erro.
        }

        String responseBody = response.body(); // Armazena o corpo da resposta da requisição GET.
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray(); // Converte o corpo da resposta para um array JSON.
        System.out.println("Pets cadastrados:"); // Exibe a mensagem de pets cadastrados.

        // Percorre o array JSON e executa o bloco de código para cada elemento.
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject(); // Converte o elemento para um objeto JSON.
            long id = jsonObject.get("id").getAsLong(); // Pega o valor do campo "id" do objeto JSON.
            String tipo = jsonObject.get("tipo").getAsString(); // Pega o valor do campo "tipo" do objeto JSON.
            String nome = jsonObject.get("nome").getAsString(); // Pega o valor do campo "nome" do objeto JSON.
            String raca = jsonObject.get("raca").getAsString(); // Pega o valor do campo "raca" do objeto JSON.
            int idade = jsonObject.get("idade").getAsInt(); // Pega o valor do campo "idade" do objeto JSON.
            System.out.println(id + " - " + tipo + " - " + nome + " - " + raca + " - " + idade + " ano(s)"); // Exibe o id, tipo, nome, raça e idade do pet.
        }
    }

    // Método responsável por importar pets de um arquivo CSV.
    public void importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:"); // Exibe a mensagem para o usuário digitar o id ou nome do abrigo.
        String idOuNome = new Scanner(System.in).nextLine(); // Lê o id ou nome do abrigo e armazena na variável idOuNome.

        System.out.println("Digite o nome do arquivo CSV:"); // Exibe a mensagem para o usuário digitar o nome do arquivo CSV.
        String nomeArquivo = new Scanner(System.in).nextLine(); // Lê o nome do arquivo CSV e armazena na variável nomeArquivo.

        BufferedReader reader = null; // Inicializa a variável reader com null.

        // Tenta carregar o arquivo, caso ocorra uma exceção, o bloco catch é executado.
        try {
            reader = new BufferedReader(new FileReader(nomeArquivo)); // Carrega o arquivo CSV.
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo); // Exibe a mensagem de erro.
        }

        String line; // Declara a variável line do tipo string.

        // Enquanto houver linhas no arquivo, executa o bloco de código.
        while ((line = Objects.requireNonNull(reader).readLine()) != null) {
            String[] campos = line.split(","); // Divide a linha em campos separados por vírgula.
            String tipo = campos[0]; // Pega o primeiro campo.
            String nome = campos[1]; // Pega o segundo campo.
            String raca = campos[2]; // Pega o terceiro campo.
            int idade = Integer.parseInt(campos[3]); // Converte o terceiro campo para inteiro.
            String cor = campos[4]; // Pega o quarto campo.
            Float peso = Float.parseFloat(campos[5]); // Converte o quinto campo para float.

            JsonObject json = new JsonObject(); // Cria um objeto JSON.
            json.addProperty("tipo", tipo.toUpperCase()); // Adiciona o campo "tipo" no objeto JSON.
            json.addProperty("nome", nome); // Adiciona o campo "nome" no objeto JSON.
            json.addProperty("raca", raca); // Adiciona o campo "raca" no objeto JSON.
            json.addProperty("idade", idade); // Adiciona o campo "idade" no objeto JSON.
            json.addProperty("cor", cor); // Adiciona o campo "cor" no objeto JSON.
            json.addProperty("peso", peso); // Adiciona o campo "peso" no objeto JSON.

            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets"; // Cria uma variável do tipo string com a URI da API.

            HttpResponse<String> response = client.dispararRequisicaoPost(uri, json); // Passa o cliente, a URI e o objeto JSON para o método dispararRequisicaoPost que retorna a resposta da requisição POST da API.

            int statusCode = response.statusCode(); // Armazena o código de status da resposta da requisição POST.
            String responseBody = response.body(); // Armazena o corpo da resposta da requisição POST.

            // Se o código de status for 200, exibe a mensagem de sucesso.
            if (statusCode == 200) {
                System.out.println("Pet cadastrado com sucesso: " + nome); // Exibe a mensagem de sucesso.
            }
            // Se o código de status for 404, exibe a mensagem de erro.
            else if (statusCode == 404) {
                System.out.println("Id ou nome do abrigo não encontado!"); // Exibe a mensagem de erro.
                break; // Interrompe o loop.
            }
            // Se o código de status for 400 ou 500, exibe a mensagem de erro.
            else if (statusCode == 400 || statusCode == 500) {
                System.out.println("Erro ao cadastrar o pet: " + nome); // Exibe a mensagem de erro.
                System.out.println(responseBody); // Exibe o corpo da resposta.
                break; // Interrompe o loop.
            }
        }
        reader.close(); // Fecha o arquivo.
    }

}
