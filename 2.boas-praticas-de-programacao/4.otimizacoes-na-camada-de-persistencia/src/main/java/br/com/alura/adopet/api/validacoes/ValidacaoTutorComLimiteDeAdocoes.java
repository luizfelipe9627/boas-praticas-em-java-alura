package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// A anotação @Component indica que a classe é um componente do Spring, ou seja, um objeto que será gerenciado pelo container do Spring.
@Component
// A classe ValidacaoPetComAdocaoEmAndamento é responsável por implementar a validação de um pet com adoção em andamento.
public class ValidacaoTutorComLimiteDeAdocoes implements ValidacaoSolicitacaoAdocao {

    // A anotação @Autowired injeta uma instância de AdocaoRepository no atributo repository.
    @Autowired
    private AdocaoRepository adocaoRepository; // O atributo repository é do tipo AdocaoRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de PetRepository no atributo petRepository.
    @Autowired
    private TutorRepository tutorRepository; // O atributo tutorRepository é do tipo TutorRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método validar recebe um objeto do tipo SolicitacaoAdocaoDto como parâmetro e é responsável por validar se um pet possui uma adoção em andamento.
    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll(); // O método findAll do repositório é utilizado para buscar todas as adoções registradas no sistema.
        Tutor tutor = tutorRepository.getReferenceById(dto.idTutor()); // O método findById do repositório é utilizado para buscar um tutor pelo id.

        // Executa um loop for para percorrer a lista de adoções.
        for (Adocao a : adocoes) {
            int contador = 0; // A variável contador é inicializada com o valor 0.

            // Se o tutor da adoção for igual ao tutor de uma adoção já registrada e o status da adoção for APROVADO, executa o bloco de código do if.
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.APROVADO) {
                contador = contador + 1; // O contador é incrementado em 1.
            }
            // Se o contador for igual a 5, executa o bloco de código do if.
            if (contador == 5) {
                throw new ValidacaoException("Tutor já possui 5 adoções aprovadas!"); // A exceção ValidacaoException é lançada com a mensagem "Tutor já possui 5 adoções aprovadas!".
            }
        }
    }
}
