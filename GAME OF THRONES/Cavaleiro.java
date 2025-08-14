public class Cavaleiro extends Peca {
    public Cavaleiro(Jogador dono, int y, int x) {
        super("Cavaleiro", "C", x, y, dono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - posicaoX);
        int deltaY = Math.abs(novoY - posicaoY);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2);
    }
}
