package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Criado um DTO para o pet responsável por receber os dados do pet.
public record PetDto(
        // A anotação @NotNull indica que o atributo id não pode ser nulo.
        @NotNull
        Long id, // Criado um atributo id do tipo Long responsável por identificar o pet.
        // Cria a coluna tipo na tabela pets.
        @NotNull
        TipoPet tipo, // Criado um atributo tipo do tipo TipoPet responsável por armazenar o tipo do pet.
        // A anotação @NotBlank indica que o atributo nome não pode ser nulo ou vazio.
        @NotBlank
        String nome, // Criado um atributo nome do tipo String responsável por armazenar o nome do pet.
        // A anotação @NotBlank indica que o atributo raca não pode ser nulo ou vazio.
        @NotBlank
        String raca, // Criado um atributo raca do tipo String responsável por armazenar a raça do pet.
        // A anotação @NotNull indica que o atributo idade não pode ser nulo.
        @NotNull
        Integer idade // Criado um atributo idade do tipo Integer responsável por armazenar a idade do pet.
) {
    // O construtor da classe PetDto é responsável por receber os dados do pet.
    public PetDto(Pet pet) {
        this(pet.getId(), pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade()); // O construtor recebe um objeto do tipo Pet e atribui os valores dos atributos id, tipo, nome, raca e idade do pet aos atributos id, tipo, nome, raca e idade do DTO.
    }
}
