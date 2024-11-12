package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

// A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class TutorServiceTest {
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    private TutorService tutorService;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private CadastroTutorDto cadastroTutorDto;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private AtualizacaoTutorDto atualizacaoTutorDto;
    @Mock
    private Tutor tutor;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se está sendo cadastrado um tutor.
    void deveriaCadastrarTutor() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        this.cadastroTutorDto = new CadastroTutorDto("Tutor xpe", "11990023902", "tutorxpe@email.com"); // Está instanciando a classe CadastroTutorDto e guardando na variável dto com os dados do tutor que vai ser cadastrado.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        tutorService.cadastrar(cadastroTutorDto); // Está chamando o método cadastrar() do serviço passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(tutorRepository).should().save(new Tutor(cadastroTutorDto)); // Verifica se o método save foi chamado no tutorRepository criando um novo tutor com os dados do dto, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por verificar se não está sendo cadastrado um tutor já cadastrado.
    void NaoDeveriaCadastrarTutorJaCadastrado() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        given(tutorRepository.existsByTelefoneOrEmail(cadastroTutorDto.telefone(), cadastroTutorDto.email())).willReturn(true); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock tutorRepository para retornar true quando o método existsByTelefoneOrEmail for chamado com o telefone e email do tutor.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        assertThrows(ValidacaoException.class, () -> tutorService.cadastrar(cadastroTutorDto)); // Verifica se o método cadastrar() do serviço lança uma exceção do tipo ValidacaoException, se sim, o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste responsável por atualizar um tutor.
    void deveriaAtualizarTutor() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        given(tutorRepository.getReferenceById(atualizacaoTutorDto.id())).willReturn(tutor); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock tutorRepository para retornar o mock tutor quando o método getReferenceById for chamado com o id do tutor.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        tutorService.atualizar(atualizacaoTutorDto); // Está chamando o método atualizar() do serviço passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(tutor).should().atualizarDados(atualizacaoTutorDto); // Verifica se o método atualizarDados foi chamado no tutor com o dto, se sim, o teste passa.
    }
}