public class Produto implements Mostravel, Validavel {
    final private int id;
    final private String nome;
    final private double preco;
    final private Categoria categoria;

    public Produto(int id, String nome, double preco, Categoria categoria) throws ValidacaoException {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;

        this.validar();
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
        System.out.println("ID: " + this.id + " | Produto: " + this.nome + " | Categoria: " + ((this.categoria != null) ? this.categoria.getNome() : "N/A") + " | Preço: R$" + this.preco);
    }

    @Override
    public void validar() throws ValidacaoException {
        if(this.preco < 0) {
            throw new ValidacaoException("O preço do produto não pode ser negativo.");
        }
        if(this.nome == null || this.nome.trim().isEmpty()) {
            throw new ValidacaoException("O nome do produto não pode ser vazio.");
        }
    }
}
