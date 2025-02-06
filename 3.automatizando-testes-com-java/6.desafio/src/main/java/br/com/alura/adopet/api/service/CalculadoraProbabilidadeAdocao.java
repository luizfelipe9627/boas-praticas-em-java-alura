package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;

// Classe responsável por calcular a probabilidade de adoção de um pet.
public class CalculadoraProbabilidadeAdocao {

    // Método que calcula a probabilidade de adoção de um pet.
    public ProbabilidadeAdocao calcular(Pet pet) {
        int nota = calcularNota(pet); // Está chamando o método calcularNota que calcula a nota do pet e guardando na variável nota

        // Se a nota for maior ou igual a 8, a probabilidade de adoção é ALTA
        if (nota >= 8) {
            return ProbabilidadeAdocao.ALTA; // Retorna ALTA
        }

        // Se a nota for maior ou igual a 5, a probabilidade de adoção é MEDIA
        if (nota >= 5) {
            return ProbabilidadeAdocao.MEDIA; // Retorna MEDIA
        }

        return ProbabilidadeAdocao.BAIXA; // Retorna BAIXA
    }

    // Método que calcula a nota de um pet.
    private int calcularNota(Pet pet) {
        int peso = pet.getPeso().intValue(); // Está pegando o peso do pet e guardando na variável peso
        int idade = pet.getIdade(); // Está pegando a idade do pet e guardando na variável idade
        TipoPet tipo = pet.getTipo(); // Está pegando o tipo do pet e guardando na variável tipo

        int nota = 10; // A nota começa com 10

        // Se o pet for cachorro e o peso for maior que 15, a nota é penalizada em 2
        if (tipo == TipoPet.CACHORRO && peso > 15) {
            nota -= 2; // Retira 2 da nota
        }
        // Se o pet for gato e o peso for maior que 10, a nota é penalizada em 2
        if (tipo == TipoPet.GATO && peso > 10) {
            nota -= 2; // Retira 2 da nota
        }

        // Se a idade for maior ou igual a 15, penaliza a nota em 5
        if (idade >= 15) {
            nota -= 5; // Retira 5 da nota
        }
        // Se não se a idade for maior ou igual a 10, penaliza a nota em 4
        else if (idade >= 10) {
            nota -= 4; // Retira 4 da nota
        }

        return nota; // Retorna a nota
    }

}
