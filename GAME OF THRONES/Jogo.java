import java.util.Scanner;

public class Jogo {
    private Peca[][] tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Scanner scanner;
    private jogador jogadorDaVez;

    public Jogo() {
        tabuleiro = new Peca[8][8];
        scanner = new Scanner(System.in);
    }

    private void inicializarJogo() {
        System.out.print("Jogador 1 insira seu nome: ");
        jogador1 = new Jogador(scanner.nextLine());

        System.out.print("Jogador 2 insira seu nome: ");
        jogador2 = new Jogador(scanner.nextLine());

        this.jogadorDaVez = jogador1;
        // Posicionar peças do Jogador 1 (linhas 0 e 1)
        posicionarPecasIniciais(jogador1, 0, 1);

        // Posicionar peças do Jogador 2 (linhas 7 e 6)
        posicionarPecasIniciais(jogador2, 7, 6);
    }

    private void posicionarPecasIniciais(Jogador jogador, int linha1, int linha2) {
        for (int i = 0; i < 3; i++) {
            Peca guerreiro = new Guerreiro(i, linha1, jogador);
            jogador.adicionarPeca(guerreiro);
            tabuleiro[i][linha1] = guerreiro;

            Peca arqueiro = new Arqueiro(i + 3, linha1, jogador);
            jogador.adicionarPeca(arqueiro);
            tabuleiro[i + 3][linha1] = arqueiro;

            Peca cavaleiro = new Cavaleiro(i + 5, linha2, jogador);
            jogador.adicionarPeca(cavaleiro);
            tabuleiro[i + 5][linha2] = cavaleiro;
        }
    }

    public void jogar() {
        Jogador jogadorAtual = jogador1;
        Jogador adversario = jogador2;

        while (jogador1.temPecas() && jogador2.temPecas()) {
            exibirTabuleiro();
            jogadorAtual.exibirPecas();

            System.out.println(jogadorAtual.getNome() + ", escolha uma peça pelo índice:");
            int indice = scanner.nextInt();

            if (indice < 0 || indice >= jogadorAtual.getPecas().size()) {
                System.out.println("Índice inválido!");
                continue;
            }

            Peca peca = jogadorDaVez.getPecas().get(indice);

            System.out.print("Informe nova posição (x y): ");
            int novoX = scanner.nextInt();
            int novoY = scanner.nextInt();

            if (!estaDentroDoTabuleiro(novoX, novoY)) {
                System.out.println("Fora do tabuleiro!");
                continue;
            }

            if (!peca.podeMoverPara(novoX, novoY)) {
                System.out.println("Movimento inválido para esta peça!");
                continue;
            }

            Peca alvo = tabuleiro[novoX][novoY];
            if (alvo != null) {
                if (alvo.getJogadorDono() == jogadorAtual) {
                    System.out.println("Você não pode atacar suas próprias peças!");
                    continue;
                } else {
                    // Ataque!
                    System.out.println("Peça " + alvo.getNome() + " de " + adversario.getNome() + " foi eliminada!");
                    adversario.removerPeca(alvo);
                }
            }

            // Atualizar tabuleiro
            tabuleiro[peca.getPosicaoX()][peca.getPosicaoY()] = null;
            peca.moverPara(novoX, novoY);
            tabuleiro[novoX][novoY] = peca;

            // Verifica vitória
            if (!adversario.temPecas()) {
                System.out.println(jogadorAtual.getNome() + " venceu!");
                break;
            }

            // Alternar turno
            Jogador temp = jogadorAtual;
            jogadorAtual = adversario;
            adversario = temp;
        }

        scanner.close();
    }

    private boolean estaDentroDoTabuleiro(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private void exibirTabuleiro() {
        System.out.println("\n   0 1 2 3 4 5 6 7");
        System.out.println("  -----------------");
        for (int y = 0; y < 8; y++) {
            System.out.print(y + " |");
            for (int x = 0; x < 8; x++) {
                if (tabuleiro[x][y] == null) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + tabuleiro[x][y]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
