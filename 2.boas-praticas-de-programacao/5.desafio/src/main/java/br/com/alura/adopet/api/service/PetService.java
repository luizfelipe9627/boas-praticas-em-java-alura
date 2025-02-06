package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

// A anotação @Service indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
@Service
public class PetService {

    // A anotação @Autowired injeta uma instância de AbrigoRepository no atributo repository.
    @Autowired
    private PetRepository petRepository; // O atributo petRepository é do tipo PetRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método listarTodos é responsável por listar todos os pets disponíveis.
    public List<PetDto> listar() {
        // Retorna a lista de pets disponíveis.
        return petRepository
                .findAllByAdotadoFalse() // O método findAllByAdotadoFalse é chamado para buscar todos os pets não adotados.
                .stream() // O stream é utilizado para percorrer a lista de pets.
                .map(PetDto::new) // O map é utilizado para converter os pets em PetDto.
                .toList(); // O toList é utilizado para converter o stream em uma lista.
    }

    // O método cadastrar é responsável por cadastrar um pet em um abrigo, recebendo um abrigo e um DTO de cadastro de pet.
    public void cadastrar(Abrigo abrigo, CadastroPetDto dto) {
        petRepository.save(new Pet(abrigo, dto)); // O método save do repositório é chamado para salvar o pet no banco de dados.
    }

}
