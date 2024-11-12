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

    @Test // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    // Criado um método de teste chamado cenario01, que testa a probabilidade de adoção de um pet utilizando um mock de pet com 4 anos e 4kg.
    void cenario01() {
        // Idade 4 anos e 4kg
        // Resultado deve ser: ALTA

        // Está instanciando a classe Abrigo e guardando na variável abrigo com os dados do abrigo que vai ser criado.
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));

        // Está instanciando a classe Pet e guardando na variável pet com os dados do pet que vai ser criado.
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao(); // Está instanciando a classe CalculadoraProbabilidadeAdocao e guardando na variável calculadora
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet); // Está chamando o método calcular que calcula a probabilidade de adoção do pet e guardando na variável probabilidade

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade); // O método assertEquals verifica se o valor esperado é igual ao valor retornado, recebe dois parâmetros, o valor esperado e o valor retornado, se forem iguais o teste passa, se forem diferentes o teste falha.
    }

    @Test // A anotação @Test indica que o método abaixo é um teste e deve ser executado pelo JUnit
    // Criado um método de teste chamado cenario02 que testa a probabilidade de adoção de um pet utilizando um mock de pet com 15 anos e 4kg que irá falhar pois a probabilidade esperada é MEDIA e está retornando BAIXA, ou seja o código tem um bug.
    void cenario02() {
        // Idade 15 anos e 4kg
        // Resultado deve ser: MEDIA

        // Está instanciando a classe Abrigo e guardando na variável abrigo com os dados do abrigo que vai ser criado.
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo feliz",
                "94999999999",
                "abrigofeliz@email.com.br"
        ));

        // Está instanciando a classe Pet e guardando na variável pet com os dados do pet que vai ser criado.
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao(); // Está instanciando a classe CalculadoraProbabilidadeAdocao e guardando na variável calculadora
        ProbabilidadeAdocao probabilidade = calculadora.calcular(pet); // Está chamando o método calcular que calcula a probabilidade de adoção do pet e guardando na variável probabilidade

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade); // O método assertEquals verifica se o valor esperado é igual ao valor retornado, recebe dois parâmetros, o valor esperado e o valor retornado, se forem iguais o teste passa, se forem diferentes o teste falha.
    }


}