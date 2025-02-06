package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.TipoPet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Criado um DTO para o cadastro de pet responsável por receber os dados do pet.
public record CadastroPetDto(
        // A anotação @NotNull indica que o atributo tipo não pode ser nulo.
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
        Integer idade, // Criado um atributo idade do tipo Integer responsável por armazenar a idade do pet.
        // A anotação @NotBlank indica que o atributo cor não pode ser nulo ou vazio.
        @NotBlank
        String cor, // Criado um atributo cor do tipo String responsável por armazenar a cor do pet.
        // A anotação @NotNull indica que o atributo peso não pode ser nulo.
        @NotNull
        Float peso // Criado um atributo peso do tipo Float responsável por armazenar o peso do pet.
) {
}
