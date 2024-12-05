import java.util.Scanner;

public class Armazem3D {
    private static final String[][][] armazem = new String[3][4][5];

    public static void inicializarArmazem() {
        for (int andar = 0; andar < 3; andar++) {
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    armazem[andar][corredor][secao] = "vazio,0";
                }
            }
        }
    }

    public static void exibirArmazem() {
        for (int andar = 0; andar < 3; andar++) {
            System.out.println("Andar " + (andar + 1) + ":");
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    System.out.print(armazem[andar][corredor][secao] + " | ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void atualizarPosicao(int andar, int corredor, int secao, String produto, int quantidade, boolean adicionar) {
        String[] dados = armazem[andar][corredor][secao].split(",");
        String produtoAtual = dados[0];
        int quantidadeAtual = Integer.parseInt(dados[1]);

        if (!adicionar) {
            quantidade = -quantidade;
        }

        if (produtoAtual.equals("vazio") || produtoAtual.equals(produto)) {
            quantidadeAtual = Math.max(0, quantidadeAtual + quantidade);
            armazem[andar][corredor][secao] = (quantidadeAtual > 0 ? produto : "vazio") + "," + quantidadeAtual;
        } else {
            System.out.println("Erro: posição já ocupada por outro produto.");
        }
    }

    public static void listarAbaixoMinimo(int minimo) {
        System.out.println("Produtos abaixo de " + minimo + ":");
        for (int andar = 0; andar < 3; andar++) {
            for (int corredor = 0; corredor < 4; corredor++) {
                for (int secao = 0; secao < 5; secao++) {
                    String[] dados = armazem[andar][corredor][secao].split(",");
                    int quantidade = Integer.parseInt(dados[1]);
                    if (quantidade > 0 && quantidade < minimo) {
                        System.out.println("Andar " + (andar + 1) + ", Corredor " + (corredor + 1) + ", Seção " + (secao + 1) +
                                " -> " + armazem[andar][corredor][secao]);
                    }
                }
            }
        }
    }

    public static void moverProdutos(int origemAndar, int origemCorredor, int origemSecao,
                                     int destinoAndar, int destinoCorredor, int destinoSecao, int quantidade) {
        String[] origem = armazem[origemAndar][origemCorredor][origemSecao].split(",");
        String produto = origem[0];
        int quantidadeOrigem = Integer.parseInt(origem[1]);

        if (quantidadeOrigem >= quantidade) {
            atualizarPosicao(origemAndar, origemCorredor, origemSecao, produto, quantidade, false);
            atualizarPosicao(destinoAndar, destinoCorredor, destinoSecao, produto, quantidade, true);
        } else {
            System.out.println("Erro: quantidade insuficiente na origem.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarArmazem();

        while (true) {
            System.out.println("\n1. Exibir armazém");
            System.out.println("2. Adicionar produto");
            System.out.println("3. Remover produto");
            System.out.println("4. Mover produto");
            System.out.println("5. Listar abaixo do mínimo");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            if (opcao == 6) break;

            switch (opcao) {
                case 1:
                    exibirArmazem();
                    break;
                case 2:
                    System.out.print("Andar: "); int andar = scanner.nextInt() - 1;
                    System.out.print("Corredor: "); int corredor = scanner.nextInt() - 1;
                    System.out.print("Seção: "); int secao = scanner.nextInt() - 1;
                    System.out.print("Produto: "); String produto = scanner.next();
                    System.out.print("Quantidade: "); int quantidade = scanner.nextInt();
                    atualizarPosicao(andar, corredor, secao, produto, quantidade, true);
                    break;
                case 3:
                    System.out.print("Andar: "); andar = scanner.nextInt() - 1;
                    System.out.print("Corredor: "); corredor = scanner.nextInt() - 1;
                    System.out.print("Seção: "); secao = scanner.nextInt() - 1;
                    System.out.print("Quantidade: "); quantidade = scanner.nextInt();
                    atualizarPosicao(andar, corredor, secao, "", quantidade, false);
                    break;
                case 4:
                    System.out.print("Andar origem: "); int origemAndar = scanner.nextInt() - 1;
                    System.out.print("Corredor origem: "); int origemCorredor = scanner.nextInt() - 1;
                    System.out.print("Seção origem: "); int origemSecao = scanner.nextInt() - 1;
                    System.out.print("Andar destino: "); int destinoAndar = scanner.nextInt() - 1;
                    System.out.print("Corredor destino: "); int destinoCorredor = scanner.nextInt() - 1;
                    System.out.print("Seção destino: "); int destinoSecao = scanner.nextInt() - 1;
                    System.out.print("Quantidade: "); quantidade = scanner.nextInt();
                    moverProdutos(origemAndar, origemCorredor, origemSecao, destinoAndar, destinoCorredor, destinoSecao, quantidade);
                    break;
                case 5:
                    System.out.print("Quantidade mínima: "); int minimo = scanner.nextInt();
                    listarAbaixoMinimo(minimo);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }
}