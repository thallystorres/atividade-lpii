public class Produto implements Mostravel{
    final private int id;
    final private String nome;
    final private double preco;
    final private Categoria categoria;

    public Produto(int id, String nome, double preco, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public void mostrarDados() {
        System.out.println("ID: " + this.id + " | Produto: " + this.nome + " | Categoria: " + ((this.categoria != null) ?
                this.categoria.getNome() : "N/A") + " | Preço: R$" + this.preco);
    }
}
