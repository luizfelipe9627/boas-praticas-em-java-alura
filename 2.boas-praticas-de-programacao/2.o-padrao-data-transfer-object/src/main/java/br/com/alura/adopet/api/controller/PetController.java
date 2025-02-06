package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
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

    // A anotação @Autowired injeta uma instância de PetRepository no atributo repository.
    @Autowired
    private PetRepository repository; // O atributo repository é do tipo PetRepository e será utilizado para acessar os métodos de persistência relacionados aos pets.

    // A anotação @GetMapping é responsável por mapear a rota /pets para o método listarTodos do tipo GET.
    @GetMapping
    // O método listarTodos é responsável por listar todos os pets disponíveis.
    public ResponseEntity<List<Pet>> listarTodosDisponiveis() {
        List<Pet> pets = repository.findAll(); // O método findAll do repositório é chamado para buscar todos os pets.
        List<Pet> disponiveis = new ArrayList<>(); // É criada uma lista de pets disponíveis.
        // Para cada pet na lista de pets, é verificado se o pet está disponível.
        for (Pet pet : pets) {
            // Se o pet não estiver adotado, o bloco de código é executado.
            if (pet.getAdotado() == false) {
                disponiveis.add(pet); // O pet é adicionado à lista de pets disponíveis.
            }
        }
        return ResponseEntity.ok(disponiveis); // A resposta da requisição é a lista de pets disponíveis.
    }

}
