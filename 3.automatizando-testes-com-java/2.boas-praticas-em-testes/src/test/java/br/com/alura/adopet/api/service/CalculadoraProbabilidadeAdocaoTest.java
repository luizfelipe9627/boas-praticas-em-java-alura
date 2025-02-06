package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculadoraProbabilidadeAdocaoTest {
    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // Criado um método de teste chamado deveriaRetornarProbabilidadeAltaParaPetComIdadeBaixaEPesoBaixo, que testa a probabilidade de adoção de um pet utilizando um mock de pet com 4 anos e 4kg que o resultado esperado é ALTA.
    void deveriaRetornarProbabilidadeAltaParaPetComIdadeBaixaEPesoBaixo() {

        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // Está instanciando a classe Abrigo e guardando na variável abrigo com os dados do abrigo que vai ser criado.
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));

        // Está instanciando a classe Pet e guardando na variável pet com os dados do pet que vai ser criado.
        Pet pet = new Pet(abrigo, new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ));

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao(); // Está instanciando a classe CalculadoraProbabilidadeAdocao e guardando na variável calculadora

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet); // Está chamando o método calcular que calcula a probabilidade de adoção do pet e guardando na variável probabilidade

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade); // O método assertEquals verifica se o valor esperado é igual ao valor retornado, recebe dois parâmetros, o valor esperado e o valor retornado, se forem iguais o teste passa, se forem diferentes o teste falha.
    }

    // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    @Test
    // Criado um método de teste chamado cenario02 que testa a probabilidade de adoção de um pet utilizando um mock de pet com 15 anos e 4kg e o resultado esperado é MEDIA.
    void deveriaRetornarProbabilidadeMediaParaPetComIdadeAltaEPesoBaixo() {
        // Idade 15 anos e 4kg
        // Resultado deve ser: MEDIA

        // ARRANGE -> ARRANGE é a parte responsável por preparar o cenário do teste.
        // Está instanciando a classe Abrigo e guardando na variável abrigo com os dados do abrigo que vai ser criado.
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));

        // Está instanciando a classe Pet e guardando na variável pet com os dados do pet que vai ser criado.
        Pet pet = new Pet(abrigo, new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                4.0f
        ));

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao(); // Está instanciando a classe CalculadoraProbabilidadeAdocao e guardando na variável calculadora

        // ACT -> ACT é uma ação, que é a parte responsável por chamar o método que será testado.
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet); // Está chamando o método calcular que calcula a probabilidade de adoção do pet e guardando na variável probabilidade

        // ASSERT -> ASSERT é a parte responsável por verificar se o resultado obtido é o esperado.
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade); // O método assertEquals verifica se o valor esperado é igual ao valor retornado, recebe dois parâmetros, o valor esperado e o valor retornado, se forem iguais o teste passa, se forem diferentes o teste falha.
    }


}