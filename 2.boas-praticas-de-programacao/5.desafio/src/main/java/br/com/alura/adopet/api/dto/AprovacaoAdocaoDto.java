package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

// Criado um DTO para a solicitação de adoção responsável por receber os dados da aprovação de adoção.
public record AprovacaoAdocaoDto(
        // A anotação @NotNull indica que o atributo idPet não pode ser nulo.
        @NotNull
        Long idAdocao // Criado um long chamado idPet responsável por receber o idPet da adoção.
) {
}
