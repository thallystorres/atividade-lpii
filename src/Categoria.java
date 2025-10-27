public class Categoria implements Mostravel {
    final private int id;
    final private String nome;

    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void mostrarDados() {
        System.out.println("Categoria ID: " + this.id + " | Nome: " + this.nome);
    }
}
