package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

// A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AbrigoServiceTest {
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    private AbrigoService abrigoService;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private AbrigoRepository abrigoRepository;
    @Mock
    private Abrigo abrigo;
    @Mock
    private CadastroAbrigoDto dto;
    @Mock
    private PetRepository petRepository;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo listado todos os abrigos.
    void deveriaListarTodosOsAbrigos() {
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        abrigoService.listar(); // Está chamando o método listar() do serviço.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // O método then() verifica se o método findAll() foi chamado no repositório.
        then(abrigoRepository).should().findAll(); // Verifica se o método findAll foi chamado no abrigoRepository se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo cadastrado um abrigo.
    void deveriaCadastrarAbrigo() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        this.dto = new CadastroAbrigoDto("Abrigo xpe", "11990023902", "abrigoxpe@email.com"); // Está instanciando a classe CadastroAbrigoDto e guardando na variável dto com os dados do abrigo que vai ser cadastrado.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        abrigoService.cadastrar(dto); // Está chamando o método cadastrar do serviço fazendo o cadastro do abrigo.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // O método then() verifica se o método save() foi chamado no repositório.
        then(abrigoRepository).should().save(new Abrigo(dto)); // Verifica se o método save foi chamado no abrigoRepository criando um novo abrigo com os dados do dto, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se não está sendo cadastrado um abrigo já cadastrado.
    void NaoDeveriaCadastrarAbrigoJaCadastrado() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(true); // Está dizendo que quando o método existsByNomeOrTelefoneOrEmail() for chamado no abrigoRepository com os dados do abrigo, ele vai retornar true.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        assertThrows(ValidacaoException.class, () -> abrigoService.cadastrar(dto)); // Verifica se o método cadatrar() do serviço lança uma exceção do tipo ValidacaoException, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo listado os pets do abrigo através do nome.
    void deveriaListarPetsDoAbrigoAtravesDoNome() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String nome = "AuAu"; // Está guardando o nome do pet que vai ser listado.
        given(abrigoRepository.findByNome(nome)).willReturn((abrigo)); // Está dizendo que quando o método findByNome() for chamado no abrigoRepository com o nome do pet, ele vai retornar o abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        abrigoService.listar(nome); // Está chamando o método listar do serviço passando o nome do abrigo.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // O método then() verifica se o método findByAbrigo() foi chamado no repositório.
        then(petRepository).should().findByAbrigo(abrigo); // Verifica se o método findByAbrigo foi chamado no petRepository passando o abrigo, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo listado os pets do abrigo através do id.
    void deveriaListarPetsDoAbrigoAtravesDoId() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        Long id = 1L; // Está guardando o id do pet que vai ser listado.
        given(abrigoRepository.findById(id)).willReturn(Optional.of(abrigo)); // Está dizendo que quando o método findByNome() for chamado no abrigoRepository com o id do pet, ele vai retornar o abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        abrigoService.listar(String.valueOf(id)); // Está chamando o método listar do serviço passando o id do abrigo.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // O método then() verifica se o método findByAbrigo() foi chamado no repositório.
        then(petRepository).should().findByAbrigo(abrigo); // Verifica se o método findByAbrigo foi chamado no petRepository passando o abrigo, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo carragado o abrigo através do nome.
    void deveriaCarregarAbrigoAtravesDoNome() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        String nome = "Abrigo xpe"; // Está guardando o nome do abrigo que vai ser carregado.
        given(abrigoRepository.findByNome(nome)).willReturn(abrigo); // Está dizendo que quando o método findByNome() for chamado no abrigoRepository com o nome do abrigo, ele vai retornar o abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        // O método then() verifica se o método findByNome() foi chamado no repositório.
        abrigoService.carregarAbrigo(nome); // Está chamando o método carregarAbrigo() do serviço passando o nome do abrigo.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(abrigoRepository).should().findByNome(nome); // Verifica se o método findByNome foi chamado no abrigoRepository passando o nome do abrigo, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo carragado o abrigo através do id.
    void deveriaCarregarAbrigoAtravesDoId() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        Long id = 1L; // Está guardando o id do abrigo que vai ser carregado.
        given(abrigoRepository.findById(id)).willReturn(Optional.of(abrigo)); // Está dizendo que quando o método findByNome() for chamado no abrigoRepository com o id do abrigo, ele vai retornar o abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        abrigoService.carregarAbrigo(String.valueOf(id)); // Está chamando o método carregarAbrigo() do serviço passando o id do abrigo.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(abrigoRepository).should().findById(id); // Verifica se o método findByNome foi chamado no abrigoRepository passando o id do abrigo, se sim, o teste passa.
    }

}