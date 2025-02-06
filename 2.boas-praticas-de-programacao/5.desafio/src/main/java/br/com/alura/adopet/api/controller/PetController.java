package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// A Anotação @RestController indica que a classe é um controlador REST.
@RestController
// A Anotação @RequestMapping mapeia a classe para a rota /pets.
@RequestMapping("/pets")
public class PetController {

    // A anotação @Autowired injeta uma instância de PetService no atributo petService.
    @Autowired
    private PetService petService; // O atributo petService é do tipo PetService e será utilizado para acessar os métodos de negócio relacionados aos pets.

    // A anotação @GetMapping é responsável por mapear a rota /pets para o método listarTodos do tipo GET.
    @GetMapping
    // O método listarPets é responsável por listar todos os pets disponíveis.
    public ResponseEntity<List<PetDto>> listarPets() {
        try {
            List<PetDto> pets = petService.listar(); // O método listar do serviço é chamado responsável por listar os pets.
            return ResponseEntity.ok(pets); // O método ok do ResponseEntity é utilizado para retornar a lista de pets.
        } catch (ValidationException e) {
            return ResponseEntity.notFound().build(); // A resposta da requisição é uma mensagem de erro.
        }
    }

}
