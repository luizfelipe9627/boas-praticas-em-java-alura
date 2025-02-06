package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Criado um DTO para a solicitação de adoção responsável por receber os dados da reprovação de adoção.
public record ReprovacaoAdocaoDto(
        // A anotação @NotNull indica que o atributo idPet não pode ser nulo.
        @NotNull
        Long id, // Criado um long chamado idPet responsável por receber o idPet da adoção.
        // A anotação @NotBlank indica que o atributo justificativa não pode ser nulo ou vazio.
        @NotBlank
        String justificativa// Criado uma string chamada justificativa responsável por receber a justificativa da reprovação da adoção.
) {
}
