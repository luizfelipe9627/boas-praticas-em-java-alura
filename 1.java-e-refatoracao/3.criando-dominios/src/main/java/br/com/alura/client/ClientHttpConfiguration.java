package br.com.alura.client;

import br.com.alura.domain.Abrigo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/*
    (S)OLID -> Single Responsibility Principle (SRP)

    - O princípio da responsabilidade única diz que uma classe deve ter apenas uma única responsabilidade, ou seja é uma classe deve ter apenas um objetivo.
    - O código abaixo está seguindo o princípio da responsabilidade única, pois a classe ClientHttpConfiguration tem apenas um objetivo, que é configurar o cliente HTTP.
*/
public class ClientHttpConfiguration {

    // Método responsável por disparar uma requisição GET, recebe um cliente HTTP e uma URI como parâmetros e retorna a resposta da requisição.
    public HttpResponse<String> dispararRequisicaoGet(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.

        HttpRequest request = HttpRequest.newBuilder() // Cria uma requisição HTTP.
                .uri(URI.create(uri)) // Adiciona a URI na requisição.
                .method("GET", HttpRequest.BodyPublishers.noBody()) // Adiciona o método GET na requisição.
                .build(); // Constrói a requisição.

        return client.send(request, HttpResponse.BodyHandlers.ofString()); // Envia a requisição e retorna a resposta.
    }

    // Método responsável por disparar uma requisição POST.
    public HttpResponse<String> dispararRequisicaoPost(String uri, Object object) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); // Cria um cliente HTTP para fazer requisições.

        HttpRequest request = HttpRequest.newBuilder() // Cria uma requisição HTTP.
                .uri(URI.create(uri)) // Adiciona a URI na requisição.
                .header("Content-Type", "application/json") // Adiciona o cabeçalho Content-Type na requisição.
                .method("POST", HttpRequest.BodyPublishers.ofString(new Gson().toJson(object))) // Adiciona o método POST na requisição e o corpo da requisição.
                .build(); // Constrói a requisição.

        return client.send(request, HttpResponse.BodyHandlers.ofString()); // Envia a requisição e retorna a resposta.
    }

}
