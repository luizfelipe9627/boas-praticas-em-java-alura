package br.com.alura.adopet.api.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizacaoTutorDto(
        // A anotação @Id indica que o atributo é a chave primária.
        @Id
        Long idTutor, // O idTutor é o identificador do tutor.
        // A anotação @NotBlank indica que o atributo não pode ser vazio.
        @NotBlank
        String nome, // O nome é o nome do tutor.
        // A anotação @Pattern indica que o atributo telefone deve seguir o padrão de telefone brasileiro.
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        // A anotação @NotBlank indica que o atributo não pode ser vazio.
        @NotBlank
        String telefone, // O telefone é o telefone do tutor.
        // A anotação @NotBlank indica que o atributo não pode ser vazio.
        @NotBlank
        // A anotação @Email indica que o atributo email deve seguir o padrão de email.
        @Email
        String email // O email é o email do tutor.
) {
}
