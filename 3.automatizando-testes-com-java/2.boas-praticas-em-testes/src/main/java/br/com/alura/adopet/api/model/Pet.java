package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

// A anotação @Entity indica que a classe Abrigo é uma entidade do banco de dados.
@Entity
// A anotação @Table define o nome da tabela no banco de dados.
@Table(name = "pets")
// A classe Pet é uma classe de modelo que representa um pet.
public class Pet {

    // A anotação @Id indica que o atributo id é a chave primária da tabela pets.
    @Id
    // A anotação @GeneratedValue indica que o valor do atributo id é gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Criado um atributo id do tipo Long responsável por identificar o pet.

    // A anotação @Enumerated indica que o atributo tipo é um enum do tipo string.
    @Enumerated(EnumType.STRING)
    private TipoPet tipo; // Criado um atributo tipo do tipo TipoPet responsável por armazenar o tipo do pet.

    private String nome; // Criado um atributo nome do tipo String responsável por armazenar o nome do pet.

    private String raca; // Criado um atributo raca do tipo String responsável por armazenar a raça do pet.

    private Integer idade; // Criado um atributo idade do tipo Integer responsável por armazenar a idade do pet.

    private String cor; // Criado um atributo cor do tipo String responsável por armazenar a cor do pet.

    private Float peso; // Criado um atributo peso do tipo Float responsável por armazenar o peso do pet.

    private Boolean adotado; // Criado um atributo adotado do tipo Boolean responsável por armazenar o status de adoção do pet.

    // A anotação @ManyToOne indica que a entidade Pet possui vinculo com a entidade Abrigo, sendo LAZY o tipo de carregamento fazendo com que o abrigo seja carregado apenas quando necessário.
    @ManyToOne(fetch = FetchType.LAZY)
    private Abrigo abrigo; // Criado um atributo abrigo do tipo Abrigo responsável por armazenar o abrigo do pet.

    // A anotação @OneToOne indica que a entidade Pet possui vinculo com a entidade Adocao.
    @OneToOne(mappedBy = "pet")
    private Adocao adocao; // Criado um atributo adocao do tipo Adocao responsável por armazenar a adoção do pet.

    // O construtor Pet é responsável por criar uma instância da classe Pet.
    public Pet() {
    }

    // O construtor Pet é responsável por criar uma instância da classe Pet.
    public Pet(Abrigo abrigo, CadastroPetDto dto) {
        this.tipo = dto.tipo(); // Define o tipo do pet.
        this.nome = dto.nome(); // Define o nome do pet.
        this.raca = dto.raca(); // Define a raça do pet.
        this.idade = dto.idade(); // Define a idade do pet.
        this.cor = dto.cor(); // Define a cor do pet.
        this.peso = dto.peso(); // Define o peso do pet.
        this.adotado = false; // Define o status de adoção do pet como falso.
        this.abrigo = abrigo; // Define o abrigo do pet.
    }

    // A anotação @Override indica que o método equals foi sobrescrito.
    @Override
    // O método equals é responsável por comparar se dois objetos são iguais.
    public boolean equals(Object o) {
        if (this == o) return true; // Se o objeto for igual a ele mesmo, retorna true.
        if (o == null || getClass() != o.getClass()) return false; // Se o objeto for nulo ou a classe do objeto for diferente da classe do objeto atual, retorna false.
        Pet pet = (Pet) o; // Cria uma variável pet do tipo Pet e atribui o objeto o a ela.
        return Objects.equals(id, pet.id); // Retorna true se os ids dos pets forem iguais, caso contrário, retorna false.
    }

    // O método hashCode é responsável por retornar o hash do objeto.
    @Override
    // O método hashCode é responsável por retornar o hash do objeto.
    public int hashCode() {
        return Objects.hash(id); // Retorna o hash do id do pet.
    }

    // O método getId é responsável por retornar o id do pet.
    public Long getId() {
        return id; // Retorna o id do pet.
    }

    // O método getTipo é responsável por retornar o tipo do pet.
    public TipoPet getTipo() {
        return tipo; // Retorna o tipo do pet.
    }

    // O método getNome é responsável por retornar o nome do pet.
    public String getNome() {
        return nome; // Retorna o nome do pet.
    }

    // O método getRaca é responsável por retornar a raça do pet.
    public String getRaca() {
        return raca; // Retorna a raça do pet.
    }

    // O método getIdade é responsável por retornar a idade do pet.
    public Integer getIdade() {
        return idade; // Retorna a idade do pet.
    }

    // O método getRaca é responsável por retornar a raça do pet.
    public Boolean getAdotado() {
        return adotado; // Retorna o status de adoção do pet.
    }

    // O método getRaca é responsável por retornar a raça do pet.
    public Abrigo getAbrigo() {
        return abrigo; // Retorna o abrigo do pet.
    }

    // O método getPeso é responsável por retornar o peso do pet.
    public Float getPeso() {
        return peso; // Retorna o peso do pet.
    }

    // O método setAbrigo é responsável por definir o abrigo do pet.
    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo; // Define o abrigo do pet.
    }

}
