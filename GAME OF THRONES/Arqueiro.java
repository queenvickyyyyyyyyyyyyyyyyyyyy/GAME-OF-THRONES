public class Arqueiro extends Peca{
    public Arqueiro(int x, int y, Jogador dono){
        super("Arqueiro", "🏹", x, y, dono);
    }

    @Override
public boolean podeMoverPara(int novoX, int novoY) {
    // Calcula a distância absoluta nos eixos X e Y
    int deltaX = Math.abs(novoX - this.posicaoX);
    int deltaY = Math.abs(novoY - this.posicaoY);

  // Movimento diagonal de até 2 casas
        return deltaX == deltaY && deltaX <= 2;
}
    
}
