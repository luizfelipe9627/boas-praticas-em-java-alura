package br.com.alura;

/*
    * Padrão de projeto Command.

    - O padrão Command é um padrão de projeto comportamental que tem a intenção de encapsular uma solicitação como um objeto, desta forma permitindo parametrizar clientes com diferentes solicitações, enfileirar solicitações e fazer o registro de solicitações, além de suportar operações que podem ser desfeitas.
    - Por exemplo quando temos várias operações que podem ser executadas, desfeitas e refeitas, o padrão Command é uma boa escolha.
*/
public interface Command {

    void execute(); // Método execute que será implementado pelas classes que implementam a interface Command.
}
