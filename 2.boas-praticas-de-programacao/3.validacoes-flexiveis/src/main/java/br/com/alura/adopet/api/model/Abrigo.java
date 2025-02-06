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
@Table(name = "abrigos")
// A classe Abrigo é uma classe de modelo que representa um abrigo de animais.
public class Abrigo {

    // A anotação @Id indica que o atributo id é a chave primária da tabela abrigos.
    @Id
    // A anotação @GeneratedValue indica que o valor do atributo id é gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Cria a coluna id na tabela abrigos.
    @Column(name = "id")
    private Long id; // Criado um atributo id do tipo Long responsável por identificar o abrigo.

    // A anotação @NotBlank indica que o atributo nome não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Column indica que o atributo nome está mapeado para a coluna nome da tabela abrigos.
    @Column(name = "nome")
    private String nome; // Criado um atributo nome do tipo String responsável por armazenar o nome do abrigo.

    // A anotação @NotBlank indica que o atributo telefone não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Pattern indica que o atributo telefone deve seguir o padrão de telefone brasileiro.
    @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
    // A anotação @Column indica que o atributo telefone está mapeado para a coluna telefone da tabela abrigos.
    @Column(name = "telefone")
    private String telefone; // Criado um atributo telefone do tipo String responsável por armazenar o telefone do abrigo.

    // A anotação @NotBlank indica que o atributo email não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Email indica que o atributo email deve seguir o padrão de email.
    @Email
    // A anotação @Column indica que o atributo email está mapeado para a coluna email da tabela abrigos.
    @Column(name = "email")
    private String email; // Criado um atributo email do tipo String responsável por armazenar o email do abrigo.

    @OneToMany(mappedBy = "abrigo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference("abrigo_pets")
    private List<Pet> pets; // Criado um atributo pets do tipo List<Pet> responsável por armazenar os pets do abrigo.

    // A anotação @Override indica que o método equals foi sobrescrito.
    @Override
    // O método equals é responsável por comparar se dois objetos são iguais.
    public boolean equals(Object o) {
        if (this == o) return true; // Se o objeto for igual a ele mesmo, retorna true.
        if (o == null || getClass() != o.getClass()) return false; // Se o objeto for nulo ou a classe do objeto for diferente da classe do objeto atual, retorna false.
        Abrigo abrigo = (Abrigo) o; // Cria uma variável abrigo do tipo Abrigo e atribui o objeto o a ela.
        return Objects.equals(id, abrigo.id); // Retorna true se os ids dos abrigos forem iguais, caso contrário, retorna false.
    }

    // A anotação @Override indica que o método hashCode foi sobrescrito.
    @Override
    // O método hashCode é responsável por retornar um valor numérico que representa o objeto.
    public int hashCode() {
        return Objects.hash(id); // Retorna o hash do id do abrigo.
    }

    // O método getNome é responsável por retornar o nome do abrigo.
    public String getNome() {
        return nome; // Retorna o nome do abrigo.
    }

    // O método getTelefone é responsável por retornar o telefone do abrigo.
    public String getTelefone() {
        return telefone; // Retorna o telefone do abrigo.
    }

    // O método getEmail é responsável por retornar o email do abrigo.
    public String getEmail() {
        return email; // Retorna o email do abrigo.
    }

    // O método getPets é responsável por retornar a lista de pets do abrigo.
    public List<Pet> getPets() {
        return pets; // Retorna a lista de pets do abrigo.
    }

}
