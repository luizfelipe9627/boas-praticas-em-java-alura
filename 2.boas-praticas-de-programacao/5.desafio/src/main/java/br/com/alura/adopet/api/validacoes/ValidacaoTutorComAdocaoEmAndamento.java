package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// A anotação @Component indica que a classe é um componente do Spring, ou seja, um objeto que será gerenciado pelo container do Spring.
@Component
// A classe ValidacaoPetComAdocaoEmAndamento é responsável por implementar a validação de um pet com adoção em andamento.
public class ValidacaoTutorComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    // A anotação @Autowired injeta uma instância de AdocaoRepository no atributo repository.
    @Autowired
    private AdocaoRepository adocaoRepository; // O atributo repository é do tipo AdocaoRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método validar recebe um objeto do tipo SolicitacaoAdocaoDto como parâmetro e é responsável por validar se um pet possui uma adoção em andamento.
    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petTemAdoacaoEmAndamento = adocaoRepository.existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO); // A variável petTemAdoacaoEmAndamento recebe o resultado da chamada do método existsByPetIdAndStatus do repositório de Adocao passando o idPet do pet e o status AGUARDANDO_AVALIACAO como parâmetros.

        // Se a variável petTemAdoacaoEmAndamento for verdadeira, então é lançada uma exceção.
        if (petTemAdoacaoEmAndamento) {
            throw new ValidacaoException("Tutor já possui uma solicitação de adoção em andamento!"); // A exceção ValidacaoException é lançada com a mensagem "Tutor já possui uma solicitação de adoção em andamento!".
        }
    }

}
