package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

// Teste de unidade -> O teste de unidade é um tipo de teste que não depende de recursos externos, como banco de dados, rede, etc. Ele testa uma unidade de código, que pode ser um método, uma classe, etc.

// A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
@ExtendWith(MockitoExtension.class)
class ValidacaoTutorComLimiteDeAdocoesTest {
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    private ValidacaoTutorComLimiteDeAdocoes validador;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private Tutor tutor;
    @Mock
    private Pet pet;

    // A anotação @Spy indica que a variável abaixo é um spy, ou seja, um objeto real que é monitorado por um mock.
    @Spy
    private List<Adocao> listaDeAdocoes = new ArrayList<>();
    @Spy
    private Adocao adocao;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método deveriaPermitirSolicitacaoDeAdocaoTutorNaoAtingiuLimiteDe5Adocoes é um cenário de teste que verifica se o método validar do validador não lança uma exceção se o tutor não atingiu o limite de 5 adoções.
    void deveriaPermitirSolicitacaoDeAdocaoTutorNaoAtingiuLimiteDe5Adocoes() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        adocao = new Adocao(tutor, pet, "Motivo qualquer"); // Cria uma nova adoção.passando o tutor, o pet e um motivo qualquer.
        adocao.marcarComoAprovado(); // Marca a adoção como aprovada.
        listaDeAdocoes.add(adocao); // Adiciona a adoção na lista de adoções.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        given(adocaoRepository.findAll()).willReturn(listaDeAdocoes); // Dado que o método findAll do adocaoRepository será chamado, então retorne a lista de adoções.
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor); // Dado que o método getReferenceById do tutorRepository será chamado, então retorne o tutor.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        assertDoesNotThrow(() -> validador.validar(dto)); // Verifica se o método validar do validador não lança uma exceção se o tutor não atingiu o limite de 5 adoções, se não lança exceção o teste passa.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método naoDeveriaPermitirSolicitacaoDeAdocaoTutorAtingiuLimiteDe5Adocoes é um cenário de teste que verifica se o método validar do validador lança uma exceção se o tutor atingiu o limite de 5 adoções.
    void naoDeveriaPermitirSolicitacaoDeAdocaoTutorAtingiuLimiteDe5Adocoes() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        adocao = new Adocao(tutor, pet, "Motivo qualquer"); // Cria uma nova adoção.passando o tutor, o pet e um motivo qualquer.
        adocao.marcarComoAprovado(); // Marca a adoção como aprovada.

        // Adiciona a adoção na lista de adoções 5 vezes para simular que o tutor atingiu o limite de 5 adoções.
        for (int i = 0; i < 5; i++) {
            listaDeAdocoes.add(adocao); // Adiciona a adoção na lista de adoções.
        }

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        given(adocaoRepository.findAll()).willReturn(listaDeAdocoes); // Dado que o método findAll do adocaoRepository será chamado, então retorne a lista de adoções.
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor); // Dado que o método getReferenceById do tutorRepository será chamado, então retorne o tutor.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        assertThrows(ValidacaoException.class, () -> validador.validar(dto)); // Verifica se o método validar do validador lança uma exceção se o tutor atingiu o limite de 5 adoções, se sim o teste passa.
    }
}