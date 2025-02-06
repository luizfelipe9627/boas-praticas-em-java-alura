package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// A anotação @RestController indica que a classe é um controlador REST.
@RestController
// A anotação @RequestMapping mapeia a classe para a rota /abrigos.
@RequestMapping("/abrigos")
// A classe AbrigoController é responsável por implementar os métodos de controle relacionados aos abrigos.
public class AbrigoController {

    // A anotação @Autowired injeta uma instância de AbrigoRepository no atributo repository.
    @Autowired
    private AbrigoRepository repository; // O atributo repository é do tipo AbrigoRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @GetMapping é responsável por mapear a rota /abrigos para o método listar do tipo GET.
    @GetMapping
    // O método listar é responsável por listar todos os abrigos cadastrados no sistema.
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(repository.findAll());  // O método findAll do repositório é utilizado para buscar todos os abrigos cadastrados no sistema.
    }

    // A anotação @PostMapping é responsável por mapear a rota /abrigos para o método cadastrar do tipo POST.
    @PostMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método cadastrar é responsável por cadastrar um abrigo, recebendo um objeto do tipo Abrigo como parâmetro.
    // A anotação @RequestBody é utilizada para indicar que o objeto abrigo será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto abrigo de acordo com as regras definidas nas anotações de validação.
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Abrigo abrigo) {
        boolean nomeJaCadastrado = repository.existsByNome(abrigo.getNome()); // O método existsByNome do repositório é utilizado para verificar se o nome do abrigo já está cadastrado.
        boolean telefoneJaCadastrado = repository.existsByTelefone(abrigo.getTelefone()); // O método existsByTelefone do repositório é utilizado para verificar se o telefone do abrigo já está cadastrado.
        boolean emailJaCadastrado = repository.existsByEmail(abrigo.getEmail()); // O método existsByEmail do repositório é utilizado para verificar se o e-mail do abrigo já está cadastrado.

        // Se o nome, telefone ou e-mail do abrigo já estiverem cadastrados, executa o bloco de código do if, caso contrário executa o bloco de código do else.
        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro abrigo!"); // A resposta da requisição é uma mensagem de erro.
        } else {
            repository.save(abrigo); // O método save do repositório é utilizado para salvar o abrigo no banco de dados.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        }
    }

    // A anotação @GetMapping é responsável por mapear a rota /abrigos/{idOuNome}/pets para o método listarPets do tipo GET.
    @GetMapping("/{idOuNome}/pets")
    // O método listarPets é responsável por listar os pets de um abrigo, recebendo um id ou nome do abrigo como parâmetro.
    // A anotação @PathVariable é utilizada para indicar que o idOuNome será recebido como um parâmetro da URL.
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        // O bloco de código do try é executado, caso não ocorra nenhuma exceção, caso contrário, o bloco de código do catch é executado.
        try {
            Long id = Long.parseLong(idOuNome); // O método parseLong é utilizado para converter a string idOuNome em um número do tipo Long.
            List<Pet> pets = repository.getReferenceById(id).getPets(); // O método getReferenceById do repositório é utilizado para buscar os pets de um abrigo pelo id.
            return ResponseEntity.ok(pets); // A resposta da requisição é a lista de pets do abrigo.
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = repository.findByNome(idOuNome).getPets(); // O método findByNome do repositório é utilizado para buscar os pets de um abrigo pelo nome.
                return ResponseEntity.ok(pets); // A resposta da requisição é a lista de pets do abrigo.
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
            }
        }
    }

    // A anotação @PostMapping é responsável por mapear a rota /abrigos/{idOuNome}/pets para o método cadastrarPet do tipo POST.
    @PostMapping("/{idOuNome}/pets")
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método cadastrarPet é responsável por cadastrar um pet em um abrigo, recebendo um id ou nome do abrigo e um objeto do tipo Pet como parâmetro.
    // A anotação @RequestBody é utilizada para indicar que o objeto pet será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto pet de acordo com as regras definidas nas anotações de validação.
    // A anotação @PathVariable é utilizada para indicar que o idOuNome será recebido como um parâmetro da URL.
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid Pet pet) {
        try {
            Long id = Long.parseLong(idOuNome); // O método parseLong é utilizado para converter a string idOuNome em um número do tipo Long.
            Abrigo abrigo = repository.getReferenceById(id); // O método getReferenceById do repositório é utilizado para buscar um abrigo pelo id.
            pet.setAbrigo(abrigo); // O método setAbrigo é utilizado para definir o abrigo do pet.
            pet.setAdotado(false); // O método setAdotado é utilizado para definir o status de adoção do pet.
            abrigo.getPets().add(pet); // O método add é utilizado para adicionar o pet na lista de pets do abrigo.
            repository.save(abrigo); // O método save do repositório é utilizado para salvar o abrigo no banco de dados.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome); // O método findByNome do repositório é utilizado para buscar um abrigo pelo nome.
                pet.setAbrigo(abrigo); // O método setAbrigo é utilizado para definir o abrigo do pet.
                pet.setAdotado(false); // O método setAdotado é utilizado para definir o status de adoção do pet.
                abrigo.getPets().add(pet); // O método add é utilizado para adicionar o pet na lista de pets do abrigo.
                repository.save(abrigo); // O método save do repositório é utilizado para salvar o abrigo no banco de dados.
                return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
            } catch (EntityNotFoundException enfe) {
                return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
            }
        }
    }

}
