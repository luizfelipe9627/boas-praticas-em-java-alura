package br.com.alura.domain;

public class Abrigo {

    private Long id; // Cria uma variável do tipo long para armazenar o id do abrigo.
    private String nome; // Cria uma variável do tipo string para armazenar o nome do abrigo.
    private String telefone; // Cria uma variável do tipo string para armazenar o telefone do abrigo.
    private String email; // Cria uma variável do tipo string para armazenar o email do abrigo.
    private Pet[] pets; // Cria uma variável do tipo Pet para armazenar os pets do abrigo.

    // Método construtor padrão para ser utilizado quando não for passado nenhum parâmetro.
    public Abrigo() {
    }

    // Método construtor que recebe o nome, telefone e email do abrigo.
    public Abrigo(String nome, String telefone, String email) {
        this.nome = nome; // Atribui o nome do abrigo.
        this.telefone = telefone; // Atribui o telefone do abrigo.
        this.email = email; // Atribui o email do abrigo.
    }

    // Método getter para retornar o id do abrigo.
    public Long getId() {
        return id; // Retorna o id do abrigo.
    }

    // Método getter para retornar o nome do abrigo.
    public String getNome() {
        return nome; // Retorna o nome do abrigo.
    }

    // Método getter para retornar o telefone do abrigo.
    public String getTelefone() {
        return telefone; // Retorna o telefone do abrigo.
    }

    // Método getter para retornar o email do abrigo.
    public String getEmail() {
        return email; // Retorna o email do abrigo.
    }

    // Método getter para retornar os pets do abrigo.
    public Pet[] getPets() {
        return pets; // Retorna os pets do abrigo.
    }

}
