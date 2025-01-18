package br.com.alura;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        // Tenta executar o bloco de código, caso ocorra uma exceção, o bloco catch é executado.
        try {
            int opcaoEscolhida = 0; // Inicializa a variável com 0 fazendo com que o programa entre no loop.

            // Enquanto a opção escolhida for diferente de 5, o programa continua rodando.
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine(); // Lê a opção digitada pelo usuário.
                opcaoEscolhida = Integer.parseInt(textoDigitado); // Converte a opção para inteiro.

                // Se a opção escolhida for 1, chama o método listarAbrigo responsável por listar os abrigos.
                if (opcaoEscolhida == 1) {
                    listarAbrigo();
                }
                // Se a opção escolhida for 2, chama o método cadastrarAbrigo responsável por cadastrar um novo abrigo.
                else if (opcaoEscolhida == 2) {
                    cadastrarAbrigo();
                }
                // Se a opção escolhida for 3, chama o método listarPetsDoAbrigo responsável por listar os pets de um abrigo.
                else if (opcaoEscolhida == 3) {
                    listarPetsDoAbrigo();
                }
                // Se a opção escolhida for 4, chama o método importarPetsDoAbrigo responsável por importar pets de um arquivo CSV.
                else if (opcaoEscolhida == 4) {
                    importarPetsDoAbrigo();
                }
                // Se a opção escolhida for 5, o programa é finalizado.
                else if (opcaoEscolhida == 5) {
                    break;
                }
                // Se a opção escolhida for diferente de 1, 2, 3, 4 ou 5, exibe uma mensagem de erro.
                else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }

            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método responsável por listar os abrigos cadastrados.
    private static void listarAbrigo() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.
        String uri = "http://localhost:8080/abrigos"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = dispararRequisicaoGet(client, uri); // Passa o cliente e a URI para o método dispararRequisicaoGet que retorna a resposta da requisição GET da API.
        String responseBody = response.body(); // Armazena o corpo da resposta da requisição GET.
        JsonArray jsonArray = JsonParser.parseString(responseBody).getAsJsonArray(); // Converte o corpo da resposta para um array JSON.

        System.out.println("Abrigos cadastrados:"); // Exibe a mensagem de abrigos cadastrados.

        // Percorre o array JSON e executa o bloco de código para cada elemento.
        for (JsonElement element : jsonArray) {
            JsonObject jsonObject = element.getAsJsonObject(); // Converte o elemento para um objeto JSON.
            long id = jsonObject.get("id").getAsLong(); // Pega o valor do campo "id" do objeto JSON.
            String nome = jsonObject.get("nome").getAsString(); // Pega o valor do campo "nome" do objeto JSON.
            System.out.println(id + " - " + nome); // Exibe o id e o nome do abrigo.
        }
    }

    // Método responsável por cadastrar um novo abrigo.
    private static void cadastrarAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o nome do abrigo:"); // Exibe a mensagem para o usuário digitar o nome do abrigo.
        String nome = new Scanner(System.in).nextLine(); // Lê o nome do abrigo e armazena na variável nome.

        System.out.println("Digite o telefone do abrigo:"); // Exibe a mensagem para o usuário digitar o telefone do abrigo.
        String telefone = new Scanner(System.in).nextLine(); // Lê o telefone do abrigo e armazena na variável telefone.

        System.out.println("Digite o email do abrigo:");  // Exibe a mensagem para o usuário digitar o email do abrigo.
        String email = new Scanner(System.in).nextLine(); // Lê o email do abrigo e armazena na variável email.

        JsonObject json = new JsonObject(); // Cria um objeto JSON.
        json.addProperty("nome", nome); // Adiciona o campo "nome" no objeto JSON.
        json.addProperty("telefone", telefone); // Adiciona o campo "telefone" no objeto JSON.
        json.addProperty("email", email); // Adiciona o campo "email" no objeto JSON.

        HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.
        String uri = "http://localhost:8080/abrigos"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = dispararRequisicaoPost(client, uri, json); // Passa o cliente, a URI e o objeto JSON para o método dispararRequisicaoPost que retorna a resposta da requisição POST da API.

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

    // Método responsável por listar os pets de um abrigo.
    private static void listarPetsDoAbrigo() throws IOException, InterruptedException {
        System.out.println("Digite o id ou nome do abrigo:"); // Exibe a mensagem para o usuário digitar o id ou nome do abrigo.
        String idOuNome = new Scanner(System.in).nextLine(); // Lê o id ou nome do abrigo e armazena na variável idOuNome.

        HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.
        String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets"; // Cria uma variável do tipo string com a URI da API.

        HttpResponse<String> response = dispararRequisicaoGet(client, uri); // Passa o cliente e a URI para o método dispararRequisicaoGet que retorna a resposta da requisição GET da API.

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
    private static void importarPetsDoAbrigo() throws IOException, InterruptedException {
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

            HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.
            String uri = "http://localhost:8080/abrigos/" + idOuNome + "/pets"; // Cria uma variável do tipo string com a URI da API.

            HttpResponse<String> response = dispararRequisicaoPost(client, uri, json); // Passa o cliente, a URI e o objeto JSON para o método dispararRequisicaoPost que retorna a resposta da requisição POST da API.

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

    // Método responsável por disparar uma requisição GET, recebe um cliente HTTP e uma URI como parâmetros e retorna a resposta da requisição.
    private static HttpResponse<String> dispararRequisicaoGet(HttpClient client, String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder() // Cria uma requisição HTTP.
                .uri(URI.create(uri)) // Adiciona a URI na requisição.
                .method("GET", HttpRequest.BodyPublishers.noBody()) // Adiciona o método GET na requisição.
                .build(); // Constrói a requisição.

        return client.send(request, HttpResponse.BodyHandlers.ofString()); // Envia a requisição e retorna a resposta.
    }

    // Método responsável por disparar uma requisição POST.
    private static HttpResponse<String> dispararRequisicaoPost(HttpClient client, String uri, JsonObject json) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder() // Cria uma requisição HTTP.
                .uri(URI.create(uri)) // Adiciona a URI na requisição.
                .header("Content-Type", "application/json") // Adiciona o cabeçalho Content-Type na requisição.
                .method("POST", HttpRequest.BodyPublishers.ofString(json.toString())) // Adiciona o método POST e o corpo da requisição na requisição.
                .build(); // Constrói a requisição.

        return client.send(request, HttpResponse.BodyHandlers.ofString()); // Envia a requisição e retorna a resposta.
    }

}
