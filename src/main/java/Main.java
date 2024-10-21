import Entities.Pedido;
import Entities.ProdutoComposto;
import Entities.ProdutoSimples;
import Services.CalcularDesconto;
import Services.CalcularImposto;
import Services.ProcessadorPedido;
import Services.VerificarDisponibilidade;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Pedido.Builder pedidoBuilder = new Pedido.Builder();
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir nova linha

            switch (opcao) {
                case 1:
                    adicionarItemAoPedido(pedidoBuilder);
                    break;
                case 2:
                    Pedido pedido = pedidoBuilder.build();
                    System.out.println("\n--- Pedido Atual ---");
                    pedido.exibirPedido();
                    break;
                case 3:
                    Pedido pedidoFinal = pedidoBuilder.build();
                    processarPedido(pedidoFinal);
                    continuar = false;  // Finaliza o programa após processar o pedido
                    break;
                case 0:
                    continuar = false;  // Finaliza o programa
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

        System.out.println("Encerrando o sistema...");
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gerenciamento de Pedidos ---");
        System.out.println("1. Adicionar Item ao Pedido");
        System.out.println("2. Exibir Pedido Atual");
        System.out.println("3. Processar Pedido");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarItemAoPedido(Pedido.Builder pedidoBuilder) {
        System.out.println("\n--- Menu da Cafeteria ---");
        System.out.println("1. Café - R$ 5.00");
        System.out.println("2. Pão de Queijo - R$ 3.50");
        System.out.println("3. Suco - R$ 4.00");
        System.out.println("4. Combo Café + Pão de Queijo - R$ 8.00");
        System.out.print("Escolha o item para adicionar ao pedido: ");
        int opcaoItem = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha

        switch (opcaoItem) {
            case 1:
                ProdutoSimples cafe = new ProdutoSimples("Café", 5.0);
                pedidoBuilder.adicionarItem(cafe);
                System.out.println("Café adicionado ao pedido.");
                break;
            case 2:
                ProdutoSimples paoQueijo = new ProdutoSimples("Pão de Queijo", 3.5);
                pedidoBuilder.adicionarItem(paoQueijo);
                System.out.println("Pão de Queijo adicionado ao pedido.");
                break;
            case 3:
                ProdutoSimples suco = new ProdutoSimples("Suco", 4.0);
                pedidoBuilder.adicionarItem(suco);
                System.out.println("Suco adicionado ao pedido.");
                break;
            case 4:
                ProdutoComposto combo = new ProdutoComposto("Combo Café + Pão de Queijo");
                combo.adicionarItem(new ProdutoSimples("Café", 5.0));
                combo.adicionarItem(new ProdutoSimples("Pão de Queijo", 3.5));
                pedidoBuilder.adicionarItem(combo);
                System.out.println("Combo Café + Pão de Queijo adicionado ao pedido.");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void processarPedido(Pedido pedido) {
        System.out.println("\n--- Processando Pedido ---");

        // Criar os processadores de pedido
        ProcessadorPedido verificarDisponibilidade = new VerificarDisponibilidade();
        ProcessadorPedido calcularDesconto = new CalcularDesconto();
        ProcessadorPedido calcularImposto = new CalcularImposto();

        // Definir a cadeia de responsabilidade
        verificarDisponibilidade.definirProximo(calcularDesconto);
        calcularDesconto.definirProximo(calcularImposto);

        // Processar o pedido
        verificarDisponibilidade.processar(pedido);

        // Exibir o pedido final
        System.out.println("\n--- Pedido Finalizado ---");
        pedido.exibirPedido();
        System.out.println("Obrigado por fazer seu pedido!");
    }
}
