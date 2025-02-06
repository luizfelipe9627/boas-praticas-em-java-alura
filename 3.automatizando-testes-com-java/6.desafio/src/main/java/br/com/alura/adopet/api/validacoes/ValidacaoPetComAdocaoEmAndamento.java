package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

// A anotação @Component indica que a classe é um componente do Spring, ou seja, um objeto que será gerenciado pelo container do Spring.
@Component
// A classe ValidacaoPetComAdocaoEmAndamento é responsável por implementar a validação de um pet com adoção em andamento.
public class ValidacaoPetComAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {

    // A anotação @Autowired injeta uma instância de AdocaoRepository no atributo repository.
    @Autowired
    private AdocaoRepository adocaoRepository; // O atributo repository é do tipo AdocaoRepository e será utilizado para acessar os métodos de persistência de dados.

    // Construtor que recebe um objeto do tipo AdocaoRepository como parâmetro e atribui ao atributo adocaoRepository.
    public ValidacaoPetComAdocaoEmAndamento(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository; // O atributo adocaoRepository é inicializado com o valor do parâmetro recebido.
    }

    // O método validar recebe um objeto do tipo SolicitacaoAdocaoDto como parâmetro e é responsável por validar se um pet possui uma adoção em andamento.
    public void validar(SolicitacaoAdocaoDto dto) {

        boolean petTemAdocaoEmAndamento = adocaoRepository
                .existsByPetIdAndStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO); // A variável petTemAdocaoEmAndamento recebe o resultado da chamada do método existsByPetIdAndStatus do repositório de Adocao passando o idPet do pet e o status AGUARDANDO_AVALIACAO como parâmetros.

        // Se a variável petTemAdocaoEmAndamento for verdadeira, então é lançada uma exceção.
        if (petTemAdocaoEmAndamento) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
