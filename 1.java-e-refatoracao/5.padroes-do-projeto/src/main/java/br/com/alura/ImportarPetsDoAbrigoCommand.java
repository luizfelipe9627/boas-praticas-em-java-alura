package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;

// A classe ListarPetsDoAbrigoCommand implementa a interface Command que possui o método execute responsável por executar o comando.
public class ImportarPetsDoAbrigoCommand implements Command {

    // A anotação @Override é usada para indicar que a anotação do tipo método substitui um método declarado em uma superclasse.
    @Override
    // O método execute é responsável por executar o comando de importar pets do abrigo.
    public void execute() {
        // O bloco try é usado para testar um bloco de código quanto a erros, o bloco catch é usado para manipular os erros.
        try {
            ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration(); // Instancia a classe ClientHttpConfiguration.
            PetService petService = new PetService(clientHttpConfiguration); // Instancia a classe PetService que recebe a instância de ClientHttpConfiguration.

            petService.importarPetsDoAbrigo(); // Chama o método importarPetsDoAbrigo presente na classe PetService.
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage()); // Exibe a mensagem de erro.
        }
    }
}
