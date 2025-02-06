package br.com.alura.adopet.api.model;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
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
    private Long id; // O idAbrigo é o identificador do tutor.

    private String nome; // O nome é o nome do tutor.

    private String telefone; // O telefone é o telefone do tutor.

    private String email; // O email é o email do tutor.

    // A anotação @OneToMany indica que a relação é de um para muitos.
    @OneToMany(mappedBy = "tutor")
    private List<Adocao> adocoes; // As adoções são as adoções do tutor.

    // Criado um construtor vazio para a classe Tutor para ser utilizado pelo Hibernate.
    public Tutor() {
    }

    // Criado um construtor que recebe um objeto do tipo CadastroTutorDto como parâmetro.
    public Tutor(CadastroTutorDto dto) {
    }

    // O método atualizarDados é responsável por atualizar os dados do tutor, recebendo um objeto do tipo AtualizacaoTutorDto como parâmetro.
    public void atualizarDados(AtualizacaoTutorDto dto) {
        this.nome = dto.nome(); // O nome do tutor é atualizado com o nome do dto.
        this.telefone = dto.telefone(); // O telefone do tutor é atualizado com o telefone do dto.
        this.email = dto.email(); // O email do tutor é atualizado com o email do dto.
    }

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
        return Objects.hash(id); // Retorna o hash do idAbrigo do tutor.
    }

    // O método getId é responsável por retornar o idAbrigo do tutor.
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
