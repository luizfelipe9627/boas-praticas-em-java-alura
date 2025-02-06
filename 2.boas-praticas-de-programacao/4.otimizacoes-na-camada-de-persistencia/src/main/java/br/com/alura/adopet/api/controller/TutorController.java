package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    // A anotação @Autowired injeta uma instância de TutorRepository no atributo repository.
    @Autowired
    private TutorRepository repository; // O atributo repository é do tipo TutorRepository e será utilizado para acessar os métodos de persistência de dados.

    // A anotação @GetMapping é utilizada para mapear requisições do tipo GET.
    @PostMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método cadastrar é responsável por cadastrar um tutor, recebendo um objeto do tipo Tutor como parâmetro.
    // A anotação @RequestBody é utilizada para indicar que o objeto tutor será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto tutor de acordo com as regras definidas nas anotações de validação.
    public ResponseEntity<String> cadastrar(@RequestBody @Valid Tutor tutor) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(tutor.getTelefone()); // O método existsByTelefone do repositório é utilizado para verificar se o telefone do tutor já está cadastrado.
        boolean emailJaCadastrado = repository.existsByEmail(tutor.getEmail()); // O método existsByEmail do repositório é utilizado para verificar se o e-mail do tutor já está cadastrado.

        // Se o telefone ou e-mail do tutor já estiverem cadastrados, executa o bloco de código do if, caso contrário executa o bloco de código do else.
        if (telefoneJaCadastrado || emailJaCadastrado) {
            return ResponseEntity.badRequest().body("Dados já cadastrados para outro tutor!"); // A resposta da requisição é uma mensagem de erro.
        } else {
            repository.save(tutor); // O método save do repositório é utilizado para salvar o tutor no banco de dados.
            return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
        }
    }

    // A anotação @PutMapping é utilizada para mapear requisições do tipo PUT.
    @PutMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método atualizar é responsável por atualizar um tutor, recebendo um objeto do tipo Tutor como parâmetro.
    // A anotação @RequestBody é utilizada para indicar que o objeto tutor será recebido no corpo da requisição.
    // A anotação @Valid é utilizada para validar o objeto tutor de acordo com as regras definidas nas anotações de validação.
    public ResponseEntity<String> atualizar(@RequestBody @Valid Tutor tutor) {
        repository.save(tutor); // O método save do repositório é utilizado para atualizar o tutor no banco de dados.
        return ResponseEntity.ok().build(); // A resposta da requisição é uma mensagem de sucesso.
    }

}
