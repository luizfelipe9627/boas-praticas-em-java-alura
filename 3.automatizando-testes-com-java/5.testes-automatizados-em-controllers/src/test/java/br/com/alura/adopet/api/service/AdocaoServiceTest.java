package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class) // A anotação @ExtendWith é usada para estender o JUnit com funcionalidades adicionais, no caso está estendendo o JUnit com a funcionalidade do Mockito.
class AdocaoServiceTest {

    // A anotação @InjectMocks indica que a variável abaixo é um mock e será injetada em outra variável ou seja, será utilizada em outra variável.
    @InjectMocks
    AdocaoService adocaoService;

    // A anotação @Mock indica que a variável abaixo é um mock, ou seja, um objeto falso que simula o comportamento de um objeto real.
    @Mock
    private AdocaoRepository adocaoRepository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Mock
    private Adocao adocao;
    @Mock
    private Abrigo abrigo;
    @Mock
    private ValidacaoSolicitacaoAdocao validacaoSolicitacaoAdocao1;
    @Mock
    private ValidacaoSolicitacaoAdocao validacaoSolicitacaoAdocao2;

    // Não precisamos mockar a SolicitacaoAdocaoDto, pois vamos controlar qual valor ela vai ter.
    private SolicitacaoAdocaoDto dto;

    // A anotação @Captor indica que a variável abaixo é um captor, ou seja, é um objeto que captura o argumento passado para um método.
    @Captor
    private ArgumentCaptor<Adocao> adocaoArgumentCaptor; // O ArgumentCaptor é usado para capturar o argumento passado para um método e guardá-lo em uma variável.

    // A anotação @Spy indica que a variável abaixo é um spy, ou seja, ainda é um mock mas ele permite que você controle o comportamento de métodos específicos.
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste que verifica se a solicitação de adoção foi realizada com sucesso.
    void deveriaSalvarAdocaoAoSolicitar() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Teste"); // Está instanciando a classe SolicitacaoAdocaoDto e guardando na variável dto com os dados da solicitação de adoção que vai ser criada.

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock petRepository para retornar o mock pet quando o método getReferenceById for chamado com o id do pet.
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock tutorRepository para retornar o mock tutor quando o método getReferenceById for chamado com o id do tutor.
        given(pet.getAbrigo()).willReturn(abrigo); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock pet para retornar o mock abrigo.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        adocaoService.solicitar(dto); // Está chamando o método solicitar que solicita a adoção do pet, passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(adocaoRepository).should() // O método then do Mockito é usado para verificar se um método foi chamado, no caso está verificando se o método save do adocaoRepository foi chamado.
                .save(adocaoArgumentCaptor.capture()); // Verifica se o método save foi chamado e captura o argumento do tipo Adocao passado para o método save.

        Adocao adocaoSalva = adocaoArgumentCaptor.getValue(); // Pega o valor capturado pelo captor e guarda na variável adocaoSalva.

        Assertions.assertEquals(pet, adocaoSalva.getPet()); // O método assertEquals verifica se os dois valores passados como parâmetro são iguais, se forem o teste passa, se não o teste falha.
        Assertions.assertEquals(tutor, adocaoSalva.getTutor()); // O método assertEquals verifica se os dois valores passados como parâmetro são iguais, se forem o teste passa, se não o teste falha.
        Assertions.assertEquals(dto.motivo(), adocaoSalva.getMotivo()); // O método assertEquals verifica se os dois valores passados como parâmetro são iguais, se forem o teste passa, se não o teste falha.
    }


    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // O método abaixo é um teste que verifica se a aprovação de adoção foi realizada com sucesso.
    void deveriaChamarValidadoresDeAdocaoAoSolicitar() {
        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        this.dto = new SolicitacaoAdocaoDto(10L, 20L, "Teste"); // Está instanciando a classe SolicitacaoAdocaoDto e guardando na variável dto com os dados da solicitação de adoção que vai ser criada.

        given(petRepository.getReferenceById(dto.idPet())).willReturn(pet); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock petRepository para retornar o mock pet quando o método getReferenceById for chamado com o id do pet.
        given(tutorRepository.getReferenceById(dto.idTutor())).willReturn(tutor); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock tutorRepository para retornar o mock tutor quando o método getReferenceById for chamado com o id do tutor.
        given(pet.getAbrigo()).willReturn(abrigo); // O método given do BDDMockito é usado para configurar o comportamento do mock, no caso está configurando o mock pet para retornar o mock abrigo.

        validacoes.add(validacaoSolicitacaoAdocao1); // Adiciona o mock validacaoSolicitacaoAdocao1 na lista de validações.
        validacoes.add(validacaoSolicitacaoAdocao2); // Adiciona o mock validacaoSolicitacaoAdocao2 na lista de validações.

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        adocaoService.solicitar(dto); // Está chamando o método solicitar que solicita a adoção do pet, passando o dto como parâmetro.

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        then(validacaoSolicitacaoAdocao1).should().validar(dto); // O método then do Mockito é usado para verificar se um método foi chamado, e no caso está verificando se o método validar do validacaoSolicitacaoAdocao1 foi chamado passando o dto como parâmetro.
        then(validacaoSolicitacaoAdocao2).should().validar(dto); // O método then do Mockito é usado para verificar se um método foi chamado, e no caso está verificando se o método validar do validacaoSolicitacaoAdocao2 foi chamado passando o dto como parâmetro.
    }
}