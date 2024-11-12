package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class) // A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
class ValidacaoPetDisponivelTest {
    @InjectMocks
    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    private ValidacaoPetDisponivel validacaoPetDisponivel; // Está criando uma variável chamada validacaoPetDisponivel do tipo ValidacaoPetDisponivel

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private PetRepository petRepository;
    @Mock
    private Pet pet;
    @Mock
    private SolicitacaoAdocaoDto dto;

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // Criado um método de teste chamado deveriaPermitirSolicitacaoDeAdocaoPet, que testa a solicitação de adoção de um pet que está disponível.
    void deveriaPermitirSolicitacaoDeAdocaoPet() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // Está instanciando a classe SolicitacaoAdocaoDto e guardando na variável dtop com os dados da solicitação de adoção que vai ser criada.
        //        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
        //                7L,
        //                1L,
        //                "Teste"
        //
        //        );

        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock petRepository para retornar o mock pet quando o método getReferenceById for chamado com o id do pet.
        BDDMockito.given(pet.getAdotado()).willReturn(false); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock pet para retornar false dizendo que o pet não foi adotado.


        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        //validacaoPetDisponivel.validar(dto); // Está chamando o método validar que valida a solicitação de adoção do pet, passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertDoesNotThrow(() -> validacaoPetDisponivel.validar(dto)); // O método assertDoesNotThrow verifica se o método passado como parâmetro não lança uma exceção, se não lançar o teste passa, se lançar o teste falha.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // Criado um método de teste chamado deveriaPermitirSolicitacaoDeAdocaoPet, que testa a solicitação de adoção de um pet que está disponível.
    void naoDeveriaPermitirSolicitacaoDeAdocaoPet() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // Está instanciando a classe SolicitacaoAdocaoDto e guardando na variável dtop com os dados da solicitação de adoção que vai ser criada.
        //        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(
        //                7L,
        //                1L,
        //                "Teste"
        //
        //        );

        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock petRepository para retornar o mock pet quando o método getReferenceById for chamado com o id do pet.
        BDDMockito.given(pet.getAdotado()).willReturn(true); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock pet para retornar true dizendo que o pet já foi adotado quando o método getAdotado for chamado.


        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        //validacaoPetDisponivel.validar(dto); // Está chamando o método validar que valida a solicitação de adoção do pet, passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertThrows(ValidacaoException.class, () -> validacaoPetDisponivel.validar(dto)); // O método assertThrows verifica se o método passado como parâmetro lança uma exceção do tipo passado como parâmetro, se lançar o teste passa, se não lançar o teste falha.
    }
}