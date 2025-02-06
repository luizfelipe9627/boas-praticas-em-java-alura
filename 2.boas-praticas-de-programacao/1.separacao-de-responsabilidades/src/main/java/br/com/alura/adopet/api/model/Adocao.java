package br.com.alura.adopet.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

// A anotação @Entity indica que a classe Abrigo é uma entidade do banco de dados.
@Entity
// A anotação @Table define o nome da tabela no banco de dados.
@Table(name = "adocoes")
// A classe Adocao é uma classe de modelo que representa uma adoção de um pet.
public class Adocao {

    // A anotação @Id indica que o atributo id é a chave primária da tabela abrigos.
    @Id
    // A anotação @GeneratedValue indica que o valor do atributo id é gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Cria a coluna id na tabela abrigos.
    @Column(name = "id")
    private Long id; // Criado um atributo id do tipo Long responsável por identificar o abrigo.

    // Cria a coluna data na tabela adocoes.
    @Column(name = "data")
    private LocalDateTime data; // Criado um atributo data do tipo LocalDateTime responsável por armazenar a data da adoção.

    // A anotação @NotNull indica que o atributo tutor não pode ser nulo.
    @NotNull
    // A anotação @ManyToOne indica que a entidade Adocao possui vinculo com a entidade Tutor.
    @ManyToOne
    // A anotação @JsonBackReference indica que a entidade Tutor é o lado inverso da relação.
    @JsonBackReference("tutor_adocoes")
    // A anotação @JoinColumn indica que o atributo tutor está mapeado para a coluna tutor_id da tabela adocoes.
    @JoinColumn(name = "tutor_id")
    private Tutor tutor; // Criado um atributo tutor do tipo Tutor responsável por armazenar o tutor que adotou o pet.

    // A anotação @NotNull indica que o atributo pet não pode ser nulo.
    @NotNull
    // A anotação @OneToOne indica que a entidade Adocao possui vinculo com a entidade Pet.
    @OneToOne
    // A anotação @JoinColumn indica que o atributo pet está mapeado para a coluna pet_id da tabela adocoes.
    @JoinColumn(name = "pet_id")
    // A anotação @JsonManagedReference indica que a entidade Pet é o lado dono da relação.
    @JsonManagedReference("adocao_pets")
    private Pet pet; // Criado um atributo pet do tipo Pet responsável por armazenar o pet adotado.

    // A anotação @NotBlank indica que o atributo motivo não pode ser nulo ou vazio.
    @NotBlank
    // Cria a coluna motivo na tabela adocoes.
    @Column(name = "motivo")
    private String motivo; // Criado um atributo motivo do tipo String responsável por armazenar o motivo da adoção.

    // A anotação @Enumerated indica que o atributo status é um enum do tipo string.
    @Enumerated(EnumType.STRING)
    // Cria a coluna status na tabela adocoes.
    @Column(name = "status")
    private StatusAdocao status; // Criado um atributo status do tipo StatusAdocao responsável por armazenar o status da adoção.

    // Cria a coluna justificativa_status na tabela adocoes.
    @Column(name = "justificativa_status")
    private String justificativaStatus; // Criado um atributo justificativaStatus do tipo String responsável por armazenar a justificativa do status da adoção.

    // A anotação @Override indica que o método equals foi sobrescrito.
    @Override
    // O método equals é responsável por comparar se dois objetos são iguais.
    public boolean equals(Object o) {
        if (this == o) return true; // Se o objeto for igual a ele mesmo, retorna true.
        if (o == null || getClass() != o.getClass()) return false; // Se o objeto for nulo ou a classe do objeto for diferente da classe do objeto atual, retorna false.
        Adocao adocao = (Adocao) o; // Cria uma variável adocao do tipo Adocao e atribui o objeto o a ela.
        return Objects.equals(id, adocao.id); // Retorna true se os ids das adoções forem iguais, caso contrário, retorna false.
    }

    // A anotação @Override indica que o método hashCode foi sobrescrito.
    @Override
    // O método hashCode é responsável por retornar um valor numérico que representa o objeto.
    public int hashCode() {
        return Objects.hash(id); // Retorna o hash do id da adoção.
    }

    // O método getId é responsável por retornar o id da adoção.
    public LocalDateTime getData() {
        return data; // Retorna o id da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public void setData(LocalDateTime data) {
        this.data = data; // Retorna o motivo da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public Tutor getTutor() {
        return tutor; // Retorna o motivo da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public Pet getPet() {
        return pet; // Retorna o motivo da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public StatusAdocao getStatus() {
        return status; // Retorna o motivo da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public void setStatus(StatusAdocao status) {
        this.status = status; // Retorna o motivo da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public String getJustificativaStatus() {
        return justificativaStatus; // Retorna o motivo da adoção.
    }

}
