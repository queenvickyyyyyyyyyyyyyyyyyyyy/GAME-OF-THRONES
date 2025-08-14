import java.util.Scanner;

public class Jogo {
    private Peca[][] tabuleiro;
    private Jogador jogador1;
    private Jogador jogador2;
    private Scanner scanner;
    private Jogador jogadorDaVez;

    public Jogo() {
        tabuleiro = new Peca[8][8];
        scanner = new Scanner(System.in);
    }

    public void iniciarJogo() {
        System.out.print("Jogador 1, insira seu nome: ");
        jogador1 = new Jogador(scanner.nextLine());

        System.out.print("Jogador 2, insira seu nome: ");
        jogador2 = new Jogador(scanner.nextLine());

        this.jogadorDaVez = jogador1;
        
        posicionarPecasIniciais(jogador1, 0, 1);

        posicionarPecasIniciais(jogador2, 7, 6);
    }

    private void posicionarPecasIniciais(Jogador jogador, int linha1, int linha2) {
        
        Peca guerreiro1 = new Guerreiro(jogador, 1, linha1);
        jogador.adicionarPeca(guerreiro1);
        tabuleiro[1][linha1] = guerreiro1;

        Peca arqueiro1 = new Arqueiro(jogador, 3, linha1);
        jogador.adicionarPeca(arqueiro1);
        tabuleiro[3][linha1] = arqueiro1;

        Peca cavaleiro1 = new Cavaleiro(jogador, 5, linha1);
        jogador.adicionarPeca(cavaleiro1);
        tabuleiro[5][linha1] = cavaleiro1;

    }

    public void jogar() {
        Jogador adversario;

        while (jogador1.temPecas() && jogador2.temPecas()) {
            exibirTabuleiro();
            jogadorDaVez.exibirPecas();
            
            // Determina o adversário
            adversario = (jogadorDaVez == jogador1) ? jogador2 : jogador1;

            System.out.println(jogadorDaVez.getNome() + ", escolha uma peça pelo número:");
            
            int indice = -1;
            
            indice = scanner.nextInt();
            scanner.nextLine(); 

            if (indice < 0 || indice >= jogadorDaVez.getPecas().size()) {
                System.out.println("Número inválido!");
                continue;
            }

            Peca peca = jogadorDaVez.getPecas().get(indice);

            System.out.print("Informe a nova posição (x y): ");
            int novoX = -1, novoY = -1;
            
            novoX = scanner.nextInt();
            novoY = scanner.nextInt();
            scanner.nextLine();
            

            if (!estaDentroDoTabuleiro(novoX, novoY)) {
                System.out.println("Fora do tabuleiro!");
                continue;
            }

            if (tabuleiro[novoX][novoY] != null && tabuleiro[novoX][novoY].getJogadorDono() == jogadorDaVez) {
                System.out.println("Você não pode mover para uma posição ocupada por sua própria peça!");
                continue;
            }
            
            if (!peca.podeMoverPara(novoX, novoY)) {
                System.out.println("Movimento inválido para esta peça!");
                continue;
            }

            Peca alvo = tabuleiro[novoX][novoY];
            if (alvo != null) {
                System.out.println("Peça " + alvo.getNome() + " de " + adversario.getNome() + " foi eliminada!");
                adversario.removerPeca(alvo);
            }
            
            int xAntigo = peca.getPosicaoX();
            int yAntigo = peca.getPosicaoY();
            
            peca.moverPara(novoX, novoY);
            
            tabuleiro[xAntigo][yAntigo] = null;
            
            tabuleiro[novoX][novoY] = peca;
            
            if (!adversario.temPecas()) {
                exibirTabuleiro();
                System.out.println(jogadorDaVez.getNome() + " venceu!");
                break;
            }
            
            jogadorDaVez = adversario;
        }

        scanner.close();
    }

    private boolean estaDentroDoTabuleiro(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private void exibirTabuleiro() {
        System.out.println("\n   0  1  2  3  4  5  6  7");
        System.out.println("  -------------------------");
        for (int y = 0; y < 8; y++) {
            System.out.print(y + " |");
            for (int x = 0; x < 8; x++) {
                Peca peca = tabuleiro[x][y];
                if (peca != null) {
                    System.out.print(" " + peca.getSimbolo() + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
