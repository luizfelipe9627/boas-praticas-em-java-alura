package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


// Criado um DTO para a solicitação de adoção responsável por receber os dados da aprovação de adoção.
public record AbrigoDto(
        // A anotação @NotNull indica que o atributo idAdocao não pode ser nulo.
        @NotNull
        Long idAbrigo, // Criado um atributo idAbrigo do tipo Long responsável por identificar o abrigo.
        // A anotação @NotBlank indica que o atributo nome não pode ser nulo ou vazio.
        @NotBlank
        String nome // Criado um atributo nome do tipo String responsável por armazenar o nome do abrigo.
) {
    // O construtor da classe AbrigoDto é responsável por receber os dados do abrigo.
    public AbrigoDto(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome()); // O construtor recebe um objeto do tipo Abrigo e atribui os valores dos atributos idAbrigo e nome do abrigo aos atributos idAbrigo e nome do DTO.
    }
}
