package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

// A anotação @Entity indica que a classe Abrigo é uma entidade do banco de dados.
@Entity
// A anotação @Table define o nome da tabela no banco de dados.
@Table(name = "tutores")
// A classe Tutor é uma classe de modelo que representa um tutor de animais.
public class Tutor {

    // A anotação @Id indica que o atributo é a chave primária.
    @Id
    // A anotação @GeneratedValue indica que o valor do atributo é gerado automaticamente.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Cria a coluna id na tabela tutores.
    @Column(name = "id")
    private Long id; // O id é o identificador do tutor.

    // A anotação @NotBlank indica que o atributo não pode ser vazio.
    @NotBlank
    // Cria a coluna nome na tabela tutores.
    @Column(name = "nome")
    private String nome; // O nome é o nome do tutor.

    // A anotação @NotBlank indica que o atributo não pode ser vazio.
    @NotBlank
    // A anotação @Pattern indica que o atributo telefone deve seguir o padrão de telefone brasileiro.
    @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
    // Cria a coluna telefone na tabela tutores.
    @Column(name = "telefone")
    private String telefone; // O telefone é o telefone do tutor.

    // A anotação @NotBlank indica que o atributo não pode ser vazio.
    @NotBlank
    // A anotação @Email indica que o atributo email deve seguir o padrão de email.
    @Email
    // Cria a coluna email na tabela tutores.
    @Column(name = "email")
    private String email; // O email é o email do tutor.

    // A anotação @OneToMany indica que um tutor pode ter várias adoções de pets.
    @OneToMany
    // A anotação @JsonManagedReference indica que o tutor é o lado proprietário da relação por padrão o fetch é LAZY.
    @JsonManagedReference("tutor_adocoes")
    private List<Adocao> adocoes; // As adoções são as adoções do tutor.

    // A anotação @Override indica que o método equals foi sobrescrito.
    @Override
    // O método equals é responsável por comparar se dois objetos são iguais.
    public boolean equals(Object o) {
        if (this == o) return true; // Se o objeto for igual a ele mesmo, retorna true.
        if (o == null || getClass() != o.getClass()) return false; // Se o objeto for nulo ou a classe do objeto for diferente da classe do objeto atual, retorna false.
        Tutor tutor = (Tutor) o; // Cria uma variável tutor do tipo Tutor e atribui o objeto o a ela.
        return Objects.equals(id, tutor.id); // Retorna true se os ids dos tutores forem iguais, caso contrário, retorna false.
    }

    // A anotação @Override indica que o método hashCode foi sobrescrito.
    @Override
    public int hashCode() {
        return Objects.hash(id); // Retorna o hash do id do tutor.
    }

    // O método getId é responsável por retornar o id do tutor.
    public String getNome() {
        return nome; // Retorna o nome do tutor.
    }

    // O método getTelefone é responsável por retornar o telefone do tutor.
    public String getTelefone() {
        return telefone; // Retorna o telefone do tutor.
    }

    // O método getEmail é responsável por retornar o email do tutor.
    public String getEmail() {
        return email; // Retorna o email do tutor.
    }

}
