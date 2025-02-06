package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDto;
import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
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

    // A anotação @Autowired injeta uma instância de AbrigoService no atributo service.
    @Autowired
    private AbrigoService abrigoService; // O atributo abrigoService é do tipo AbrigoService e será utilizado para acessar os métodos de negócio relacionados aos abrigos.

    // A anotação @Autowired injeta uma instância de AbrigoRepository no atributo repository.
    @Autowired
    private PetService petService; // O atributo petService é do tipo PetService e será utilizado para acessar os métodos de negócio relacionados aos pets.

    // A anotação @GetMapping é responsável por mapear a rota /abrigos para o método listar do tipo GET.
    @GetMapping
    // O método listarAbrigos é responsável por listar todos os abrigos cadastrados no sistema.
    public ResponseEntity<List<AbrigoDto>> listarAbrigos() {
       try {
           List<AbrigoDto> abrigos = this.abrigoService.listar(); // O método listar do serviço é chamado responsável por listar os abrigos.
           return ResponseEntity.ok(abrigos); // A resposta da requisição é a lista de abrigos.
       } catch (ValidationException e) {
           return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
       }
    }

    // A anotação @PostMapping é responsável por mapear a rota /abrigos para o método cadastrar do tipo POST.
    @PostMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // A anotação @RequestBody é utilizada para indicar que o objeto abrigo será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto abrigo de acordo com as regras definidas nas anotações de validação.
    // O método cadastrarAbrigo é responsável por cadastrar um abrigo, recebendo um objeto do tipo Abrigo como parâmetro.
    public ResponseEntity<String> cadastrarAbrigo(@RequestBody @Valid CadastroAbrigoDto dto) {
        try {
            this.abrigoService.cadastrar(dto); // O método cadastrar do serviço é chamado, passando o objeto abrigo como parâmetro.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // A resposta da requisição é uma mensagem de erro.
        }
    }

    // A anotação @GetMapping é responsável por mapear a rota /abrigos/{idOuNome}/pets para o método listarPets do tipo GET.
    @GetMapping("/{idOuNome}/pets")
    // A anotação @PathVariable é utilizada para indicar que o idOuNome será recebido como um parâmetro da URL.
    // O método listarPets é responsável por listar os pets de um abrigo, recebendo um idAbrigo ou nome do abrigo como parâmetro.
    public ResponseEntity<List<PetDto>> listarPets(@PathVariable String idOuNome) {
        try {
            List<PetDto> petsAbrigo = this.abrigoService.listar(idOuNome); // O método listar do serviço é chamado, passando o idOuNome como parâmetro.
            return ResponseEntity.ok(petsAbrigo); // A resposta da requisição é a lista de pets do abrigo.
        } catch (ValidationException e) {
            return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
        }
    }

    // A anotação @PostMapping é responsável por mapear a rota /abrigos/{idOuNome}/pets para o método cadastrarPet do tipo POST.
    @PostMapping("/{idOuNome}/pets")
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // A anotação @RequestBody é utilizada para indicar que o objeto pet será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto pet de acordo com as regras definidas nas anotações de validação.
    // A anotação @PathVariable é utilizada para indicar que o idOuNome será recebido como um parâmetro da URL.
    // O método cadastrarPet é responsável por cadastrar um pet em um abrigo, recebendo um idAbrigo ou nome do abrigo e um objeto do tipo Pet como parâmetro.
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastroPetDto dto) {
        try {
            Abrigo abrigo = abrigoService.carregarAbrigo(idOuNome); // O método carregarAbrigo do serviço é chamado, passando o idOuNome como parâmetro.
            this.petService.cadastrar(abrigo, dto); // O método cadastrarPet do serviço é chamado, passando o idOuNome e o objeto pet como parâmetro.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidationException e) {
            return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
        }
    }

}
