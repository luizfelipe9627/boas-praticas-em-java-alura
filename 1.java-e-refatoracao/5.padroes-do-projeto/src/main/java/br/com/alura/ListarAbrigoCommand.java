package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;

import java.io.IOException;

// A classe ListarPetsDoAbrigoCommand implementa a interface Command que possui o método execute responsável por executar o comando.
public class ListarAbrigoCommand implements Command {

    // A anotação @Override é usada para indicar que a anotação do tipo método substitui um método declarado em uma superclasse.
    @Override
    // O método execute é responsável por executar o comando de listar abrigo.
    public void execute() {
        // O bloco try é usado para testar um bloco de código quanto a erros, o bloco catch é usado para manipular os erros.
        try {
            ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration(); // Instancia a classe ClientHttpConfiguration.
            AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration); // Instancia a classe AbrigoService que recebe a instância de ClientHttpConfiguration.

            abrigoService.listarAbrigo(); // Chama o método listarAbrigo presente na classe AbrigoService.
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage()); // Exibe a mensagem de erro.
        }
    }

}
