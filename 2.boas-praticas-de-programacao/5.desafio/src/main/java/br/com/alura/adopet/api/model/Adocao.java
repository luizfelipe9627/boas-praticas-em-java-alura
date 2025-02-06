package br.com.alura.adopet.api.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

// A anotação @Entity indica que a classe Abrigo é uma entidade do banco de dados.
@Entity
// A anotação @Table define o nome da tabela no banco de dados.
@Table(name = "adocoes")
// A classe Adocao é uma classe de modelo que representa uma adoção de um pet.
public class Adocao {

    // A anotação @Id indica que o atributo idPet é a chave primária da tabela abrigos.
    @Id
    // A anotação @GeneratedValue indica que o valor do atributo idPet é gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Criado um atributo idPet do tipo Long responsável por identificar o abrigo.

    private LocalDateTime data; // Criado um atributo data do tipo LocalDateTime responsável por armazenar a data da adoção.

    // A anotação @ManyToOne indica que a entidade Adocao possui vinculo com a entidade Tutor, onde um tutor pode adotar vários pets.
    @ManyToOne(fetch = FetchType.LAZY) // O atributo fetch lazy indica que o tutor só será carregado quando for acessado ou seja, não será carregado automaticamente.
    private Tutor tutor; // Criado um atributo tutor do tipo Tutor responsável por armazenar o tutor que adotou o pet.

    // A anotação @OneToOne indica que a entidade Adocao possui vinculo com a entidade Pet onde um pet pode ser adotado por um tutor.
    @OneToOne(fetch = FetchType.LAZY) // O atributo fetch lazy indica que o pet só será carregado quando for acessado ou seja, não será carregado automaticamente.
    private Pet pet; // Criado um atributo pet do tipo Pet responsável por armazenar o pet adotado.

    private String motivo; // Criado um atributo motivo do tipo String responsável por armazenar o motivo da adoção.

    // A anotação @Enumerated indica que o atributo status é um enum do tipo string.
    @Enumerated(EnumType.STRING)
    private StatusAdocao status; // Criado um atributo status do tipo StatusAdocao responsável por armazenar o status da adoção.

    private String justificativaStatus; // Criado um atributo justificativaStatus do tipo String responsável por armazenar a justificativa do status da adoção.

    // Criado um construtor que recebe um tutor, um pet e um motivo como parâmetros.
    public Adocao(Tutor tutor, Pet pet, String motivo) {
        this.tutor = tutor; // Atribui os valores recebidos como parâmetros aos atributos da adoção.
        this.pet = pet; // Atribui os valores recebidos como parâmetros aos atributos da adoção.
        this.motivo = motivo; // Atribui os valores recebidos como parâmetros aos atributos da adoção.
        this.status = StatusAdocao.AGUARDANDO_AVALIACAO; // Atribui o status AGUARDANDO_AVALIACAO ao atributo status da adoção como padrão.
        this.data = LocalDateTime.now(); // Atribui a data e hora atuais ao atributo data da adoção como padrão.
    }

    public void marcarComoAprovado() {
        this.status = StatusAdocao.APROVADO; // Atribui o status APROVADO ao atributo status da adoção.
    }

    public void marcarComoReprovado(String justificativa) {
        this.status = StatusAdocao.REPROVADO; // Atribui o status REPROVADO ao atributo status da adoção.
        this.justificativaStatus = justificativa; // Atribui a justificativa recebida como parâmetro ao atributo justificativaStatus da adoção.
    }

    // Criado um construtor vazio para que o JPA possa instanciar a classe sem parâmetros.
    public Adocao() {
    }

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
        return Objects.hash(id); // Retorna o hash do idPet da adoção.
    }

    // O método getDados é responsável por retornar a data da adoção.
    public LocalDateTime getData() {
        return data; // Retorna a data da adoção.
    }

    // O método getTutor é responsável por retornar o tutor da adoção.
    public Tutor getTutor() {
        return tutor; // Retorna o tutor da adoção.
    }

    // O método getPet é responsável por retornar o pet da adoção.
    public Pet getPet() {
        return pet; // Retorna o pet da adoção.
    }

    // O método setPet é responsável por atribuir um pet a adoção.
    public void setPet(Pet pet) {
        this.pet = pet; // Atribui um pet a adoção.
    }

    // O método getStatus é responsável por retornar o status da adoção.
    public StatusAdocao getStatus() {
        return status; // Retorna o status da adoção.
    }

    // O método getJustificativaStatus é responsável por retornar a justificativa do status da adoção.
    public String getJustificativaStatus() {
        return justificativaStatus; // Retorna a justificativa do status da adoção.
    }

    // O método getMotivo é responsável por retornar o motivo da adoção.
    public String getMotivo() {
        return motivo; // Retorna o motivo da adoção.
    }

}
