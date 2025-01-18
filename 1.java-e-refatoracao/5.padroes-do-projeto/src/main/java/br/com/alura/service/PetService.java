package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
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

        Pet[] pets = new ObjectMapper().readValue(responseBody, Pet[].class); // Converte o corpo da resposta para um array JSON do tipo Pet.
        List<Pet> petLista = Arrays.stream(pets).toList(); // Converte o array JSON para uma lista e armazena na variável petLista.

        System.out.println("Pets cadastrados:"); // Exibe a mensagem de pets cadastrados.

        // Percorre o array JSON e executa o bloco de código para cada elemento.
        for (Pet pet : petLista) {
            long id = pet.getId(); // Pega o valor do campo "id" da lista de pets.
            String tipo = pet.getTipo(); // Pega o valor do campo "tipo" da lista de pets.
            String nome = pet.getNome(); // Pega o valor do campo "nome" da lista de pets.
            String raca = pet.getRaca(); // Pega o valor do campo "raca" da lista de pets.
            int idade = pet.getIdade(); // Pega o valor do campo "idade" da lista de pets.
            System.out.println(id + " - " + tipo + " - " + nome + " - " + raca + " - " + idade + " ano(s)"); // Exibe o id, tipo, nome, raça e idade do pet.
        }
    }

    // Método responsável por importar pets de um arquivo CSV.
    public void importarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:"); // Exibe a mensagem para o usuário digitar o id ou nome do abrigo.
        String idOuNome = new Scanner(System.in).nextLine(); // Lê o id ou nome do abrigo e armazena na variável idOuNome.

        System.out.println("Digite o nome do arquivo CSV:"); // Exibe a mensagem para o usuário digitar o nome do arquivo CSV.
        String nomeArquivo = new Scanner(System.in).nextLine(); // Lê o nome do arquivo CSV e armazena na variável nomeArquivo.



        // Tenta carregar o arquivo, caso ocorra uma exceção, o bloco catch é executado.
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(nomeArquivo)); // Carrega o arquivo CSV.
        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo: " + nomeArquivo); // Exibe a mensagem de erro.
        }

        String line; // Declara a variável line do tipo string.

        // Enquanto a linha for diferente de nulo, o bloco de código é executado.
        while ((line = Objects.requireNonNull(reader).readLine()) != null) {
            String[] campos = line.split(","); // Divide a linha em campos separados por vírgula.
            String tipo = campos[0].toUpperCase(); // Pega o primeiro campo.
            String nome = campos[1]; // Pega o segundo campo.
            String raca = campos[2]; // Pega o terceiro campo.
            int idade = Integer.parseInt(campos[3]); // Converte o terceiro campo para inteiro.
            String cor = campos[4]; // Pega o quarto campo.
            Float peso = Float.parseFloat(campos[5]); // Converte o quinto campo para float.

            Pet pet = new Pet(tipo, nome, raca, idade, cor, peso); // Cria um objeto do tipo Pet com os campos informados e armazena na variável pet.

            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets"; // Cria uma variável do tipo string com a URI da API.

            HttpResponse<String> response = client.dispararRequisicaoPost(uri, pet); // Passa o cliente, a URI e o objeto JSON para o método dispararRequisicaoPost que retorna a resposta da requisição POST da API.

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
