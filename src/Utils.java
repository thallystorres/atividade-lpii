import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Utils {

    public static void exibirUtilsPrincipal() {
        System.out.println("\n========================================");
        System.out.println("   SISTEMA DE GESTÃO DE RESTAURANTE   ");
        System.out.println("========================================");
        System.out.println("1. Gerenciar Cardápio");
        System.out.println("2. Gerenciar Clientes");
        System.out.println("3. Gerenciar um Pedido");
        System.out.println("0. Sair do Sistema");
        System.out.println("----------------------------------------");
        System.out.println("Escolha uma opção: ");
    }

    public static void exibirSubUtilsCardapio() {
        System.out.println("\n--- Gerenciar Cardápio ---");
        System.out.println("1. Adicionar Categoria");
        System.out.println("2. Adicionar Produto");
        System.out.println("3. Listar Categorias");
        System.out.println("4. Listar Todos os Produtos");
        System.out.println("5. Remover Produto");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }


    public static void exibirSubUtilsClientes() {
        System.out.println("\n--- Gerenciar Clientes ---");
        System.out.println("1. Cadastrar Novo Cliente");
        System.out.println("2. Listar Todos os Clientes");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.print("Escolha uma opção: ");
    }

    public static void logicaUtilsClientes(GerenciadorDeClientes clientesManager, Scanner scanner) {
        int opcao;
        do {
            exibirSubUtilsClientes();
            if(! scanner.hasNextInt()) {
                System.out.println("Erro: entrada inválida.");
                scanner.nextLine();
                opcao = - 1;
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch(opcao) {
                case 1: {
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o CPF do cliente: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Digite o endereço do cliente: ");
                    String endereco = scanner.nextLine();

                    clientesManager.adicionarCliente(nome, cpf, endereco);
                    break;
                }
                case 2:
                    clientesManager.listarClientes();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while(opcao != 0);
    }

    public static void logicaRealizarPedido(Comanda pedidosManager, Cardapio cardapio,
                                            GerenciadorDeClientes clientesManager, Scanner scanner) {
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
        String dataFormatada = hoje.format(formato);
        System.out.println("\n--- REALIZAR NOVO PEDIDO---");
        clientesManager.listarClientes();

        System.out.print("Digite o ID do cliente que está fazendo o pedido: ");
        int clienteId;
        if(! scanner.hasNextInt()) {
            System.out.println("Erro: entrada inválida. Abortando pedido");
            scanner.nextLine();
            return;
        }
        clienteId = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente;
        try {
            cliente = clientesManager.buscarClientePorId(clienteId);
        } catch(RuntimeException e) {
            System.err.println("ERRO: " + e.getMessage() + ". Abortando criação de pedido.");
            return;
        }

        Pedido novoPedido = new Pedido(pedidosManager.getProximoPedidoId(), dataFormatada);

        int produtoId = 0;
        do {
            cardapio.listarProdutos();
            System.out.print("\nDigite o ID do produto para adicionar (ou 0 para finalizar): ");

            if (!scanner.hasNextInt()){
                System.out.println("Entrada inválida. Tente novamente.");
                scanner.nextLine();
                continue;
            }
            produtoId = scanner.nextInt();
            scanner.nextLine();
            if(produtoId != 0)
                try{
                    Produto produto = cardapio.buscarProdutoPorId(produtoId);
                    novoPedido.adicionarProduto(produto);
                    System.out.println("'" + produto.getNome() + "' adicionado ao pedido.");
                } catch(RuntimeException e) {
                    System.err.println("ERRO: " + e.getMessage() + ". Tente novamente.");
                }
        } while (produtoId != 0);
        if (novoPedido.calcularTotal() > 0) {
            System.out.println("\n--- Resumo do Pedido ---");
            novoPedido.mostrarDados();

            System.out.println("\n--- Processar Pagamento ---");
            System.out.println("1. Cartão de Crédito");
            System.out.println("2. Pix");
            System.out.println("3. Dinheiro");
            System.out.println("0. Cancelar pagamento");
            System.out.print("Escolha o método de pagamento: ");


            int metodoOpcao;
            if(! scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Pagamento recusado.");
                return;
            }
            metodoOpcao = scanner.nextInt();
            scanner.nextLine();

            String metodoEscolhido = "";
            boolean pagamentoRecusado = false;

            switch(metodoOpcao) {
                case 1 -> metodoEscolhido = "Cartão de Crédito";
                case 2 -> metodoEscolhido = "Pix";
                case 3 -> metodoEscolhido = "Dinheiro";
                case 0 -> {
                    System.out.println("Pagamento cancelado pelo usuário.");
                    pagamentoRecusado = true;
                }
                default -> {
                    System.out.println("Opção inválida. Pagamento recusado.");
                    pagamentoRecusado = true;
                }
            }

            if(! pagamentoRecusado) {
                Pagamento pagamentoDoPedido = novoPedido.getPagamento();
                pagamentoDoPedido.processarPagamento(metodoEscolhido, novoPedido.calcularTotal());

                novoPedido.marcarComoPago();

                System.out.println("\n--- Pedido Finalizado ---");
                novoPedido.mostrarDados();
                cliente.adicionarPedido(novoPedido);
                pedidosManager.registrarPedido(novoPedido);
            } else {
                System.out.println("O pedido continua aguardando pagamento.");
            }
        } else {
            System.out.println("\nNenhum produto foi adicionado. Pedido cancelado.");
        }
    }

    public static void logicaUtilsCardapio(Cardapio cardapio, Scanner scanner){
        int opcao;
        do {
            exibirSubUtilsCardapio();

            if(!scanner.hasNextInt()){
                System.out.println("Erro: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                opcao = -1;
                continue;
            }
            opcao = scanner.nextInt();
            scanner.nextLine();
            try {
                switch(opcao){
                    case 1 -> {
                        System.out.print("Digite o nome da nova categoria: ");
                        String nomeCat = scanner.nextLine();
                        cardapio.adicionarCategoria(nomeCat);
                    }
                    case 2 -> {
                        System.out.print("Digite o nome do novo produto: ");
                        String nomeProd = scanner.nextLine();

                        System.out.print("Digite o preço do produto: ");
                        if(!scanner.hasNextDouble()){
                            System.out.println("Erro: preço inválido.");
                            scanner.nextLine();
                            break;
                        }
                        double preco = scanner.nextDouble();
                        scanner.nextLine();

                        if (preco < 0) {
                            System.out.println("Erro: preço não pode ser negativo.");
                            break; // volta para o menu ou loop
                        }
                        cardapio.listarCategoria();
                        System.out.print("Digite o ID da categoria do produto: ");
                        if (!scanner.hasNextInt()){
                            System.out.println("Erro: ID inválido.");
                            scanner.nextLine();
                            break;
                        }
                        int catId = scanner.nextInt();
                        scanner.nextLine();

                        cardapio.adicionarProduto(nomeProd, preco, catId);
                    }
                    case 3 -> cardapio.listarCategoria();
                    case 4 -> cardapio.listarProdutos();

                    case 5 -> {
                        cardapio.listarProdutos();
                        System.out.print("Digite o ID do produto a ser removido: ");
                        if (!scanner.hasNextInt()){
                            System.out.println("Erro: entrada inválida.");
                            scanner.nextLine();
                            break;
                        }
                        int prodId = scanner.nextInt();
                        scanner.nextLine();

                        cardapio.removeProduto(prodId);
                    }
                    case 0 -> System.out.println("Voltando ao Menu Principal...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch(RuntimeException e) {
                System.err.println("ERRO: " + e.getMessage());
            }
        } while (opcao != 0);
    }
    public static <T extends Mostravel> void listarItens(String titulo, List<T> itens) {
        System.out.println("\n--- " + titulo + " ---");
        if(itens.isEmpty()) {
            System.out.println("Nenhum item cadastrado.");
            return;
        }
        for(T item: itens) {
            item.mostrarDados();
        }
        System.out.println("------------------------------");
    }
}
