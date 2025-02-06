package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

// Criado um DTO para o cadastro de abrigo responsável por receber os dados do abrigo.
public record CadastroAbrigoDto(
        // A anotação @NotBlank indica que o atributo nome não pode ser nulo ou vazio.
        @NotBlank
        String nome, // Criado um atributo nome do tipo String responsável por armazenar o nome do abrigo.
        // A anotação @NotBlank indica que o atributo telefone não pode ser nulo ou vazio.
        @NotBlank
        // A anotação @Pattern indica que o atributo telefone deve seguir o padrão de telefone brasileiro.
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone, // Criado um atributo telefone do tipo String responsável por armazenar o telefone do abrigo.
        // A anotação @NotBlank indica que o atributo email não pode ser nulo ou vazio.
        @NotBlank
        String email // Criado um atributo email do tipo String responsável por armazenar o email do abrigo.
) {
    // O construtor da classe CadastroAbrigoDto é responsável por receber os dados do abrigo.
    public CadastroAbrigoDto(Abrigo abrigo) {
        this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail()); // O construtor recebe um objeto do tipo Abrigo e atribui os valores dos atributos nome, telefone e email do abrigo aos atributos nome, telefone e email do DTO.
    }
}
