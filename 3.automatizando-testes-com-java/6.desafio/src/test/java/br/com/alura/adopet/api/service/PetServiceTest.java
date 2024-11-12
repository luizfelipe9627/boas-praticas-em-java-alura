package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.BDDMockito.then;

// A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class PetServiceTest {
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    private PetService petService;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private Abrigo abrigo;
    @Mock
    private CadastroPetDto dto;
    @Mock
    private PetRepository petRepository;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo listado todos os pets disponíveis.
    void deveriaBuscarPetsDisponiveis() {
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        petService.buscarPetsDisponiveis(); // Está chamando o método buscarPetsDisponiveis() do serviço.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // O método then() verifica se o método findAll() foi chamado no repositório.
        then(petRepository).should().findAllByAdotadoFalse(); // Verifica se o método findAllByAdotadoFalse foi chamado no petRepository se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo cadastrado um pet.
    void deveriaCadastrarPet() {
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        petService.cadastrarPet(abrigo, dto); // Está chamando o método cadastrarPet() do serviço passando o abrigo e o dto.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(petRepository).should().save(new Pet(dto, abrigo)); // Verifica se o método save foi chamado no petRepository passando um novo Pet com o dto e o abrigo se sim, o teste passa.
    }
}