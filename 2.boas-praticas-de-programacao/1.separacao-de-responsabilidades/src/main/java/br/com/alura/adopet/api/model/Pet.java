package br.com.alura.adopet.api.model;

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
    // Cria a coluna id na tabela pets.
    @Column(name = "id")
    private Long id; // Criado um atributo id do tipo Long responsável por identificar o pet.

    // A anotação @Enumerated indica que o atributo tipo é um enum do tipo string.
    @Enumerated(EnumType.STRING)
    // Cria a coluna tipo na tabela pets.
    @NotNull
    // A anotação @Column indica que o atributo tipo está mapeado para a coluna tipo da tabela pets.
    @Column(name = "tipo")
    private TipoPet tipo; // Criado um atributo tipo do tipo TipoPet responsável por armazenar o tipo do pet.

    // A anotação @NotBlank indica que o atributo nome não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Column indica que o atributo nome está mapeado para a coluna nome da tabela pets.
    @Column(name = "nome")
    private String nome; // Criado um atributo nome do tipo String responsável por armazenar o nome do pet.

    // A anotação @NotBlank indica que o atributo raca não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Column indica que o atributo raca está mapeado para a coluna raca da tabela pets.
    @Column(name = "raca")
    private String raca; // Criado um atributo raca do tipo String responsável por armazenar a raça do pet.

    // A anotação @NotNull indica que o atributo idade não pode ser nulo.
    @NotNull
    // A anotação @Column indica que o atributo idade está mapeado para a coluna idade da tabela pets.
    @Column(name = "idade")
    private Integer idade; // Criado um atributo idade do tipo Integer responsável por armazenar a idade do pet.

    // A anotação @NotBlank indica que o atributo cor não pode ser nulo ou vazio.
    @NotBlank
    // A anotação @Column indica que o atributo cor está mapeado para a coluna cor da tabela pets.
    @Column(name = "cor")
    private String cor; // Criado um atributo cor do tipo String responsável por armazenar a cor do pet.

    // A anotação @NotNull indica que o atributo peso não pode ser nulo.
    @NotNull
    // A anotação @Column indica que o atributo peso está mapeado para a coluna peso da tabela pets.
    @Column(name = "peso")
    private Float peso; // Criado um atributo peso do tipo Float responsável por armazenar o peso do pet.

    // A anotação @Column indica que o atributo adotado está mapeado para a coluna adotado da tabela pets.
    @Column(name = "adotado")
    private Boolean adotado; // Criado um atributo adotado do tipo Boolean responsável por armazenar o status de adoção do pet.

    // A anotação @ManyToOne indica que a entidade Pet possui vinculo com a entidade Abrigo.
    @ManyToOne
    // A anotação @JsonBackReference indica que a entidade Abrigo é o lado inverso da relação.
    @JsonBackReference("abrigo_pets")
    // A anotação @JoinColumn indica que o atributo abrigo está mapeado para a coluna abrigo_id da tabela pets.
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo; // Criado um atributo abrigo do tipo Abrigo responsável por armazenar o abrigo do pet.

    // A anotação @OneToOne indica que a entidade Pet possui vinculo com a entidade Adocao.
    @OneToOne(mappedBy = "pet")
    // A anotação @JsonBackReference indica que a entidade Pet é o lado inverso da relação.
    @JsonBackReference("adocao_pets")
    private Adocao adocao; // Criado um atributo adocao do tipo Adocao responsável por armazenar a adoção do pet.

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

    // O método getNome é responsável por retornar o nome do pet.
    public String getNome() {
        return nome; // Retorna o nome do pet.
    }

    // O método getRaca é responsável por retornar a raça do pet.
    public Boolean getAdotado() {
        return adotado; // Retorna o status de adoção do pet.
    }

    // O método setAdotado é responsável por definir o status de adoção do pet.
    public void setAdotado(Boolean adotado) {
        this.adotado = adotado; // Define o status de adoção do pet.
    }

    // O método getRaca é responsável por retornar a raça do pet.
    public Abrigo getAbrigo() {
        return abrigo; // Retorna o abrigo do pet.
    }

    // O método setAbrigo é responsável por definir o abrigo do pet.
    public void setAbrigo(Abrigo abrigo) {
        this.abrigo = abrigo; // Define o abrigo do pet.
    }

}
