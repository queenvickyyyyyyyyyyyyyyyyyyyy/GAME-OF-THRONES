import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Peca> pecas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pecas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Peca> getPecas() {
        return pecas;
    }

    public void adicionarPeca(Peca peca) {
        pecas.add(peca);
    }

    public void removerPeca(Peca peca) {
        pecas.remove(peca);
    }

    public boolean temPecas() {
        return !pecas.isEmpty();
    }

    public void exibirPecas() {
        System.out.println("Peças de " + nome + ":");
        for (int i = 0; i < pecas.size(); i++) {
            Peca p = pecas.get(i);
            System.out.printf("[%d] %s (%s) na posição (%d, %d)%n", 
                i, p.getNome(), p.getSimbolo(), p.getPosicaoX(), p.getPosicaoY());
        }
    }
}
