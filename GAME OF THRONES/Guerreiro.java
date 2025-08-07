public class Guerreiro extends Peca {
     public Guerreiro(int x, int y, Jogador dono) {
        super("Guerreiro", "⚔️", x, y, dono);
    }

 public boolean podeMoverPara(int novoX, int novoY) {
        // Primeiro, vamos calcular a distância em X e Y
        int deltaX = Math.abs(novoX - this.posicaoX);
        int deltaY = Math.abs(novoY - this.posicaoY);
        
        // O Guerreiro não pode se mover na diagonal.
        // Isso significa que ou deltaX ou deltaY tem que ser zero.
        boolean eMovimentoValido = (deltaX > 0 && deltaY == 0) || (deltaX == 0 && deltaY > 0);
        
        // E a distância total deve ser no máximo 3.
        boolean eDistanciaValida = (deltaX + deltaY) <= 3;
        
        // Retorna true apenas se as duas condições forem verdadeiras
        return eMovimentoValido && eDistanciaValida;
    }
}
