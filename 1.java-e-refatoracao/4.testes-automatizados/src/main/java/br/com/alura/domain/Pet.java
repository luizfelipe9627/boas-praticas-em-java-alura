package br.com.alura.domain;

public class Pet {

    private Long id; // Cria uma variável do tipo long para armazenar o id do abrigo.
    private String tipo; // Cria uma variável do tipo string para armazenar o tipo do pet.
    private String nome; // Cria uma variável do tipo string para armazenar o nome do pet.
    private String raca; // Cria uma variável do tipo string para armazenar a raça do pet.
    private int idade; // Cria uma variável do tipo inteiro para armazenar a idade do pet.
    private String cor; // Cria uma variável do tipo string para armazenar a cor do pet.
    private Float peso; // Cria uma variável do tipo float para armazenar o peso do pet.
    private Abrigo[] abrigos; // Cria uma variável do tipo Abrigo para armazenar os abrigos do pet.

    // Método construtor padrão para ser utilizado quando não for passado nenhum parâmetro.
    public Pet() {
    }

    // Método construtor que recebe o tipo, nome, raça, idade, cor e peso do pet.
    public Pet(String tipo, String nome, String raca, int idade, String cor, Float peso) {
        this.tipo = tipo; // Atribui o tipo do pet.
        this.nome = nome; // Atribui o nome do pet.
        this.raca = raca; // Atribui a raça do pet.
        this.idade = idade; // Atribui a idade do pet.
        this.cor = cor; // Atribui a cor do pet.
        this.peso = peso; // Atribui o peso do pet.
    }

    // Método getter para retornar o id do pet.
    public Long getId() {
        return id; // Retorna o id do pet.
    }

    // Método getter para retornar o tipo do pet.
    public String getTipo() {
        return tipo; // Retorna o tipo do pet.
    }

    // Método getter para retornar o nome do pet.
    public String getNome() {
        return nome; // Retorna o nome do pet.
    }

    // Método getter para retornar a raça do pet.
    public String getRaca() {
        return raca; // Retorna a raça do pet.
    }

    // Método getter para retornar a idade do pet.
    public int getIdade() {
        return idade; // Retorna a idade do pet.
    }

    // Método getter para retornar a cor do pet.
    public String getCor() {
        return cor; // Retorna a cor do pet.
    }

    // Método getter para retornar o peso do pet.
    public Float getPeso() {
        return peso; // Retorna o peso do pet.
    }

}
