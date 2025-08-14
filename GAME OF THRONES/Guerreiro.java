public class Guerreiro extends Peca {
     public Guerreiro(Jogador dono, int y, int x) {
        super("Guerreiro", "G", x, y, dono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);
        return (deltaX == 0 && deltaY <= 3) || (deltaY == 0 && deltaX <= 3);
    }
}
