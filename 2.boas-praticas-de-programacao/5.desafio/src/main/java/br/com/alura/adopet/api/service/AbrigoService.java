package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// A anotação @Service indica que a classe é um serviço, ou seja, uma classe que contém a lógica de negócio da aplicação.
@Service
public class AbrigoService {

    // A anotação @Autowired injeta uma instância de AbrigoRepository no atributo repository.
    @Autowired
    private AbrigoRepository abrigoRepository; // O atributo abrigoRepository é do tipo AbrigoRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @Autowired injeta uma instância de AbrigoService no atributo abrigoService.
    @Autowired
    private PetRepository petRepository; // O atributo petRepository é do tipo PetRepository e será utilizado para acessar os métodos de persistência de dados.

    // O método listar é responsável por listar todos os abrigos cadastrados no sistema.
    public List<AbrigoDto> listar() {
        // Retorna a lista de abrigos cadastrados no sistema.
        return abrigoRepository
                .findAll() // O método findAll do repositório é utilizado para buscar todos os abrigos cadastrados no sistema.
                .stream() // O stream é utilizado para percorrer a lista de abrigos.
                .map(AbrigoDto::new) // O map é utilizado para converter os abrigos em AbrigoDto.
                .toList(); // O toList é utilizado para converter o stream em uma lista.
    }

    // O método cadastrar é responsável por cadastrar um abrigo, recebendo um objeto do tipo AbrigoDto como parâmetro.
    public void cadastrar(CadastroAbrigoDto dto) {
        boolean jaCadastrado = abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email()); // O método existsByNomeOrTelefoneOrEmail do repositório é utilizado para verificar se o nome, telefone ou e-mail do abrigo já estão cadastrados.

        // Se o nome, telefone ou e-mail do abrigo já estiverem cadastrados, executa o bloco de código do if, caso contrário executa o bloco de código do else.
        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }

        abrigoRepository.save(new Abrigo()); // O método save do repositório é utilizado para salvar o abrigo no banco de dados.
    }

    // O método listar é responsável por listar os pets de um abrigo, recebendo um idAbrigo ou nome do abrigo como parâmetro.
    public List<PetDto> listar(String idOuNome) {
        Abrigo abrigo = carregarAbrigo(idOuNome); // O método carregarAbrigo é chamado para carregar o abrigo pelo idAbrigo ou nome.

        // Retorna a lista de pets do abrigo.
        return petRepository
                .findByAbrigo(abrigo) // O método findByAbrigo do repositório é utilizado para buscar os pets de um abrigo.
                .stream() // O stream é utilizado para percorrer a lista de pets.
                .map(PetDto::new) // O map é utilizado para converter os pets em PetDto.
                .toList();  // O toList é utilizado para converter o stream em uma lista.
    }

    // O método adotarPet é responsável por adotar um pet, recebendo um idAbrigo ou nome do abrigo e um idAbrigo do pet como parâmetro.
    public Abrigo carregarAbrigo(String idOuNome) {
        Optional<Abrigo> optional; // Cria uma variável optional do tipo Optional<Abrigo>.

        try {
            Long id = Long.parseLong(idOuNome); // O método parseLong é utilizado para converter a string idOuNome em um número do tipo Long.
            optional = abrigoRepository.findById(id); // O método findById do repositório é utilizado para buscar um abrigo pelo idAbrigo.
        } catch (NumberFormatException e) {
            optional = Optional.ofNullable(abrigoRepository.findByNome(idOuNome)); // O método findByNome do repositório é utilizado para buscar um abrigo pelo nome.
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado!")); // O método orElseThrow é utilizado para retornar o abrigo ou lançar uma exceção caso o abrigo não seja encontrado.
    }
}
