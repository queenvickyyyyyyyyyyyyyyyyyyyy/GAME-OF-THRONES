public abstract class Peca{
    private String nome;
    private String simbolo;
    protected int posicaoX;
    protected int posicaoY;
    private Jogador jogadorDono;

    public Peca(String nome, String simbolo, int posicaoX, int posicaoY, Jogador jogadorDono){
        this.nome = nome;
        this.simbolo = simbolo;
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.jogadorDono = jogadorDono;
    }

    public String getNome(){
        return nome;
    }

    public String getSimbolo(){
        return simbolo;
    }

    public int getPosicaoX(){
        return posicaoX;
    }

    public int getPosicaoY(){
        return posicaoY;
    }
    
    public Jogador getJogadorDono(){
        return jogadorDono;
    }

    public abstract boolean podeMoverPara(int novoX, int novoY);

    public void moverPara(int novoX, int novoY){
        this.posicaoX = novoX;
        this.posicaoY = novoY;
    }

}
