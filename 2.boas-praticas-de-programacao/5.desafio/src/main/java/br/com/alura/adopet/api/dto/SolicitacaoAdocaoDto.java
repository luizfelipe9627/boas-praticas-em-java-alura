package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Criado um DTO para a solicitação de adoção responsável por receber os dados da solicitação de adoção.
public record SolicitacaoAdocaoDto(
        // A anotação @NotNull indica que o atributo pet não pode ser nulo.
        @NotNull
        Long idPet, // Criado um long chamado idPet responsável por receber o idAbrigo do pet.
        // A anotação @NotNull indica que o atributo tutor não pode ser nulo.
        @NotNull
        Long idTutor, // Criado um long chamado idTutor responsável por receber o idAbrigo do tutor.
        // A anotação @NotBlank indica que o atributo motivo não pode ser nulo ou vazio.
        @NotBlank
        String motivo // Criado uma string chamada motivo responsável por receber o motivo da solicitação de adoção.
) {
}
