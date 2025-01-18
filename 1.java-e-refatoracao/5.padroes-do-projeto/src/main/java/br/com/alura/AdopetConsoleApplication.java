package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecute execute = new CommandExecute(); // Instancia a classe CommandExecute.

        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        // Tenta executar o bloco de código, caso ocorra uma exceção, o bloco catch é executado.
        try {
            int opcaoEscolhida = 0; // Inicializa a variável com 0 fazendo com que o programa entre no loop.

            // Enquanto a opção escolhida for diferente de 5, o programa continua rodando.
            while (opcaoEscolhida != 5) {
                exibirMenu(); // Chama o método exibirMenu.

                String textoDigitado = new Scanner(System.in).nextLine(); // Lê a opção digitada pelo usuário.
                opcaoEscolhida = Integer.parseInt(textoDigitado); // Converte a opção para inteiro.

                // O switch é usado para selecionar um dos vários blocos de código a serem executudos conforme a opção escolhida.
                switch (opcaoEscolhida) {
                    case 1 -> execute.executeCommand(new ListarAbrigoCommand()); // Chama o método executeCommand presente na classe CommandExecute passando como parâmetro uma instância de ListarAbrigoCommand.
                    case 2 -> execute.executeCommand(new CadastrarAbrigoCommand()); // Chama o método executeCommand presente na classe CommandExecute passando como parâmetro uma instância de CadastrarAbrigoCommand.
                    case 3 -> execute.executeCommand(new ListarPetsDoAbrigoCommand()); // Chama o método executeCommand presente na classe CommandExecute passando como parâmetro uma instância de ListarPetsDoAbrigoCommand.
                    case 4 -> execute.executeCommand(new ImportarPetsDoAbrigoCommand()); // Chama o método executeCommand presente na classe CommandExecute passando como parâmetro uma instância de ImportarPetsDoAbrigoCommand.
                    case 5 -> System.out.println("Finalizando o programa..."); // Exibe a mensagem de finalização do programa.
                    // Se a opção escolhida for diferente de 1, 2, 3, 4 ou 5, executará o bloco default.
                    default -> {
                        System.out.println("NÚMERO INVÁLIDO!"); // Exibe a mensagem de erro.
                        opcaoEscolhida = 0; // Atribui 0 à variável opcaoEscolhida para que o programa continue rodando.
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // O método exibirMenu é responsável por exibir o menu de opções do programa.
    private static void exibirMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }

}
