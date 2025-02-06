package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.ReprovacaoAdocaoDto;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.service.AdocaoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// A Anotação @RestController indica que a classe é um controlador REST.
@RestController
// A Anotação @RequestMapping mapeia a classe para a rota /adocoes.
@RequestMapping("/adocoes")
public class AdocaoController {

    // A anotação @Autowired injeta uma instância de AdocaoService no atributo adocaoService.
    @Autowired
    private AdocaoService adocaoService; // O atributo adocaoService é do tipo AdocaoService e será utilizado para acessar os métodos de negócio relacionados às adoções.

    // A anotação @PostMapping é responsável por mapear a rota /adocoes para o método solicitar do tipo POST.
    @PostMapping
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método solicitar é responsável por solicitar uma adoção, recebendo um objeto do tipo Adocao como parâmetro.
    // A anotação @RequestBody indica que o objeto será recebido no corpo da requisição.
    // A anotação @Valid indica que o objeto será validado.
    public ResponseEntity<String> solicitar(@RequestBody @Valid SolicitacaoAdocaoDto dto) {
        // O bloco try-catch é utilizado para tratar exceções, sendo executado o bloco de código do try caso não ocorra exceção e o bloco de código do catch caso ocorra exceção.
        try {
            this.adocaoService.solicitar(dto); // O método solicitar do serviço é chamado, passando o objeto adocao como parâmetro.
            return ResponseEntity.ok("Adoção solicitada com sucesso!"); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // A resposta da requisição é uma mensagem de erro.
        }
    }

    // A anotação @PutMapping é responsável por mapear a rota /adocoes/aprovar para o método aprovar do tipo PUT.
    @PutMapping("/aprovar")
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método aprovar é responsável por aprovar uma adoção, recebendo um objeto do tipo Adocao como parâmetro.
    // A anotação @RequestBody indica que o objeto será recebido no corpo da requisição.
    // A anotação @Valid indica que o objeto será validado.
    public ResponseEntity<String> aprovar(@RequestBody @Valid AprovacaoAdocaoDto dto) {
        // O bloco try-catch é utilizado para tratar exceções, sendo executado o bloco de código do try caso não ocorra exceção e o bloco de código do catch caso ocorra exceção.
        try {
            this.adocaoService.aprovar(dto); // O método aprovar do serviço é chamado, passando o objeto adocao como parâmetro.
            return ResponseEntity.ok("Adoção aprovada com sucesso!"); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // A resposta da requisição é uma mensagem de erro.
        }
    }

    // A anotação @PutMapping é responsável por mapear a rota /adocoes/reprovar para o método reprovar do tipo PUT.
    @PutMapping("/reprovar")
    // A anotação @Transactional é usado para casos em que é necessário garantir que todas as operações dentro de um método sejam executadas com sucesso, caso contrário, todas as operações são revertidas.
    @Transactional
    // O método reprovar é responsável por reprovar uma adoção, recebendo um objeto do tipo Adocao como parâmetro.
    // A anotação @RequestBody indica que o objeto será recebido no corpo da requisição.
    // A anotação @Valid indica que o objeto será validado.
    public ResponseEntity<String> reprovar(@RequestBody @Valid ReprovacaoAdocaoDto dto) {
        try {
            this.adocaoService.reprovar(dto); // O método reprovar do serviço é chamado, passando o objeto adocao como parâmetro.
            return ResponseEntity.ok("Adoção reprovada com sucesso!"); // A resposta da requisição é uma mensagem de sucesso.
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // A resposta da requisição é uma mensagem de erro.
        }
    }

}
