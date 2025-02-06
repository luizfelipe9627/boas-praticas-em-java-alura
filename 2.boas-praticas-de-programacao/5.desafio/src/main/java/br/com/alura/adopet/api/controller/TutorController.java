package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import br.com.alura.adopet.api.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// A Anotação @RestController indica que a classe é um controlador REST.
@RestController
// A Anotação @RequestMapping mapeia a classe para a rota /tutores.
@RequestMapping("/tutores")
public class TutorController {

    // A anotação @Autowired injeta uma instância de TutorRepository no atributo repository.
    @Autowired
    private TutorService tutorService; // O atributo tutorService é do tipo TutorService e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @GetMapping é utilizada para mapear requisições do tipo GET.
    @PostMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // A anotação @RequestBody é utilizada para indicar que o objeto tutor será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto tutor de acordo com as regras definidas nas anotações de validação.
    // O método cadastrarTutor é responsável por cadastrar um tutor, recebendo um objeto do tipo CadastroTutorDto como parâmetro.
    public ResponseEntity<String> cadastrarTutor(@RequestBody @Valid CadastroTutorDto dto) {
        try {
            tutorService.cadastrar(dto); // O método cadastrar do serviço é utilizado para cadastrar o tutor no banco de dados.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().build(); // A resposta da requisição é uma mensagem de erro.
        }
    }

    // A anotação @PutMapping é utilizada para mapear requisições do tipo PUT.
    @PutMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // A anotação @RequestBody é utilizada para indicar que o objeto tutor será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto tutor de acordo com as regras definidas nas anotações de validação.
    // O método atualizarTutor é responsável por atualizar um tutor, recebendo um objeto do tipo Tutor como parâmetro.
    public ResponseEntity<String> atualizarTutor(@RequestBody @Valid AtualizacaoTutorDto dto) {
        try {
            tutorService.atualizar(dto); // O método atualizar do serviço é utilizado para atualizar o tutor no banco de dados.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidacaoException e) {
            return ResponseEntity.badRequest().build(); // A resposta da requisição é uma mensagem de erro.
        }
    }

}
