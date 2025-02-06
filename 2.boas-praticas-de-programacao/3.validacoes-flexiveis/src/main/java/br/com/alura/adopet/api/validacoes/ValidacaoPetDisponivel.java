package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// A anotação @Component indica que a classe é um componente do Spring, ou seja, um objeto que será gerenciado pelo container do Spring.
@Component
// A classe ValidacaoPetDisponivel é responsável por implementar a validação de um pet disponível para adoção.
public class ValidacaoPetDisponivel implements ValidacaoSolicitacaoAdocao {

    // A anotação @Autowired injeta uma instância de PetRepository no atributo petRepository.
    @Autowired
    private PetRepository repository; // O atributo petRepository é do tipo PetRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método validar recebe um objeto do tipo SolicitacaoAdocaoDto como parâmetro e é responsável por validar se um pet está disponível para adoção.
    public void validar(SolicitacaoAdocaoDto dto) {
        Pet pet = repository.getReferenceById(dto.idPet()); // O método findById do repositório é utilizado para buscar um pet pelo id.

        // Se o pet já foi adotado, executa o bloco de código do if.
        if (pet.getAdotado()) {
            throw new ValidacaoException("Pet já foi adotado!"); // A exceção ValidacaoException é lançada com a mensagem "Pet já foi adotado!".
        }
    }
}
