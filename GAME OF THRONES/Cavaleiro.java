public class Cavaleiro extends Peca {
    public Cavaleiro(int x, int y, Jogador dono) {
        super("Cavaleiro", "🐴", x, y, dono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int dx = Math.abs(novoX - posicaoX);
        int dy = Math.abs(novoY - posicaoY);

        // Movimento em L: 2x1 ou 1x2
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
