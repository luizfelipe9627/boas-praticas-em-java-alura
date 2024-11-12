package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

// Teste de unidade -> O teste de unidade é um tipo de teste que não depende de recursos externos, como banco de dados, rede, etc. Ele testa uma unidade de código, que pode ser um método, uma classe, etc.

// A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
@ExtendWith(MockitoExtension.class)
class ValidacaoPetComAdocaoEmAndamentoTest {
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    private ValidacaoPetComAdocaoEmAndamento validacaoPetComAdocaoEmAndamento;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private SolicitacaoAdocaoDto dto;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método deveriaPermitirSolicitacaoDeAdocaoDePetSemAdocaoEmAndamento é um método de teste que verifica se é possível solicitar a adoção de um pet que não possui adoção em andamento.
    void deveriaPermitirSolicitacaoDeAdocaoDePetComPedidoInexistente() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock adocaoRepository para retornar false quando o método existsByPetAndStatus for chamado com o pet e o status AGUARDANDO_AVALIACAO.
        given(adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO
        )).willReturn(false); // Retorna false dizendo que o pet não possui adoção em andamento.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        Assertions.assertDoesNotThrow(() -> validacaoPetComAdocaoEmAndamento.validar(dto)); // O método assertDoesNotThrow verifica se o método passado como parâmetro não lança uma exceção, se não lançar o teste passa, se lançar o teste falha.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método naoDeveriaPermitirSolicitacaoDeAdocaoDePetComPedidoEmAndamento é um método de teste que verifica se é possível solicitar a adoção de um pet que possui adoção em andamento.
    void naoDeveriaPermitirSolicitacaoDeAdocaoDePetComPedidoEmAndamento() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock adocaoRepository para retornar false quando o método existsByPetAndStatus for chamado com o pet e o status AGUARDANDO_AVALIACAO.
        given(adocaoRepository.existsByPetIdAndStatus(
                dto.idPet(),
                StatusAdocao.AGUARDANDO_AVALIACAO
        )).willReturn(true); // Retorna true dizendo que o pet possui adoção em andamento.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetComAdocaoEmAndamento.validar(dto)); // O método assertThrows verifica se o método passado como parâmetro lança uma exceção, se lançar o teste passa, se não lançar o teste falha.
    }
}