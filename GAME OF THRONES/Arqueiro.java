public class Arqueiro extends Peca{
    public Arqueiro(Jogador dono, int y, int x){
        super("Arqueiro", "A", x, y, dono);
    }

    @Override
    public boolean podeMoverPara(int novoX, int novoY) {
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);
        return deltaX == deltaY && deltaX <= 2;
    }
}
