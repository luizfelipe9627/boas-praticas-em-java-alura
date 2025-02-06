package br.com.alura.adopet.api.exception;

// A classe ValidacaoException é uma subclasse de RuntimeException e é utilizada para representar exceções de validação.
public class ValidacaoException extends RuntimeException {
    // O construtor da classe é
    public ValidacaoException(String message) {
        super(message); // O construtor da superclasse é chamado com a mensagem recebida.
    }
}
