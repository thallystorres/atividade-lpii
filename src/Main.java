import jdk.jshell.execution.Util;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cardapio cardapio = new Cardapio();
        GerenciadorDeClientes clientesManager = new GerenciadorDeClientes();
        Comanda pedidosManager = new Comanda();

        int opcao;
        do {
            Utils.exibirUtilsPrincipal();

            if(!scanner.hasNextInt()){
                System.out.println("Erro: entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> Utils.logicaUtilsCardapio(cardapio, scanner);
                case 2 -> Utils.logicaUtilsClientes(clientesManager, scanner);
                case 3 -> Utils.logicaRealizarPedido(pedidosManager, cardapio, clientesManager,scanner);
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        } while (opcao != 0);
    }
}
