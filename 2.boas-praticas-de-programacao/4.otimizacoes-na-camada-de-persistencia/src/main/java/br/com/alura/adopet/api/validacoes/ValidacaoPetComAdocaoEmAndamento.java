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

    // A anotação @Autowired injeta uma instância de PetRepository no atributo petRepository.
    @Autowired
    private PetRepository petRepository; // O atributo petRepository é do tipo PetRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método validar recebe um objeto do tipo SolicitacaoAdocaoDto como parâmetro e é responsável por validar se um pet possui uma adoção em andamento.
    public void validar(SolicitacaoAdocaoDto dto) {
        List<Adocao> adocoes = adocaoRepository.findAll(); // O método findAll do repositório é utilizado para buscar todas as adoções registradas no sistema.
        Pet pet = petRepository.getReferenceById(dto.idPet()); // O método findById do repositório é utilizado para buscar um pet pelo id.

        // Executa um loop for para percorrer a lista de adoções.
        for (Adocao a : adocoes) {
            // Se o pet da adoção for igual ao pet de uma adoção já registrada e o status da adoção for AGUARDANDO_AVALIACAO, executa o bloco de código do if.
            if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Pet já possui uma solicitação de adoção em andamento!"); // A exceção ValidacaoException é lançada com a mensagem "Pet já possui uma solicitação de adoção em andamento!".
            }
        }
    }
}
