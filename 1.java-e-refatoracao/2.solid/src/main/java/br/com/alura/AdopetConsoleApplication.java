package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        ClientHttpConfiguration clientHttpConfiguration = new ClientHttpConfiguration(); // Instancia a classe ClientHttpConfiguration.
        AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration); // Instancia a classe AbrigoService que recebe a instância de ClientHttpConfiguration.
        PetService petService = new PetService(clientHttpConfiguration); // Instancia a classe PetService que recebe a instância de ClientHttpConfiguration.

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        // Tenta executar o bloco de código, caso ocorra uma exceção, o bloco catch é executado.
        try {
            int opcaoEscolhida = 0; // Inicializa a variável com 0 fazendo com que o programa entre no loop.

            // Enquanto a opção escolhida for diferente de 5, o programa continua rodando.
            while (opcaoEscolhida != 5) {
                System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
                System.out.println("1 -> Listar abrigos cadastrados");
                System.out.println("2 -> Cadastrar novo abrigo");
                System.out.println("3 -> Listar pets do abrigo");
                System.out.println("4 -> Importar pets do abrigo");
                System.out.println("5 -> Sair");

                String textoDigitado = new Scanner(System.in).nextLine(); // Lê a opção digitada pelo usuário.
                opcaoEscolhida = Integer.parseInt(textoDigitado); // Converte a opção para inteiro.

                // Se a opção escolhida for 1, chama o método listarAbrigo responsável por listar os abrigos.
                if (opcaoEscolhida == 1) {
                    abrigoService.listarAbrigo(); // Chama o método listarAbrigo presente na classe AbrigoService.
                }
                // Se a opção escolhida for 2, chama o método cadastrarAbrigo responsável por cadastrar um novo abrigo.
                else if (opcaoEscolhida == 2) {
                    abrigoService.cadastrarAbrigo(); // Chama o método cadastrarAbrigo presente na classe AbrigoService.
                }
                // Se a opção escolhida for 3, chama o método listarPetsDoAbrigo responsável por listar os pets de um abrigo.
                else if (opcaoEscolhida == 3) {
                    petService.listarPetsDoAbrigo(); // Chama o método listarPetsDoAbrigo presente na classe PetService.
                }
                // Se a opção escolhida for 4, chama o método importarPetsDoAbrigo responsável por importar pets de um arquivo CSV.
                else if (opcaoEscolhida == 4) {
                    petService.importarPetsDoAbrigo(); // Chama o método importarPetsDoAbrigo presente na classe PetService.
                }
                // Se a opção escolhida for 5, o programa é finalizado.
                else if (opcaoEscolhida == 5) {
                    break;
                }
                // Se a opção escolhida for diferente de 1, 2, 3, 4 ou 5, exibe uma mensagem de erro.
                else {
                    System.out.println("NÚMERO INVÁLIDO!");
                    opcaoEscolhida = 0;
                }
            }

            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
