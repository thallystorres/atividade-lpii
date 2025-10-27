import java.util.ArrayList;

public class Pedido {
    final private int id;
    final private String data;
    final private ArrayList<Produto> produtos = new ArrayList<>();
    final private Pagamento pagamento;
    StatusPedido status = StatusPedido.AGUARDANDO_PAGAMENTO;

    public Pedido(int id, String data) {
        this.id = id;
        this.data = data;
        this.pagamento = new Pagamento(id);
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void marcarComoPago() {
        this.status = StatusPedido.PAGO;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public double calcularTotal() {
        double total = 0.0;
        for(Produto produto: produtos) {
            total += produto.getPreco();
        }
        return total;
    }

    public void mostrarDados() {
        System.out.println("--- Pedido ID: " + this.id + " | Data: " + "---");
        System.out.println("Produtos:");
        for(Produto produto: produtos) {
            System.out.println(" - " + produto.getNome() + "(R$ " + produto.getPreco() + ")");
        }
        System.out.println("Total: R$ " + this.calcularTotal());
        System.out.println("Status: ");
        switch(status) {
            case AGUARDANDO_PAGAMENTO -> System.out.println("Aguardando pagamento");
            case PAGO -> System.out.println("Pago");
            case CANCELADO -> System.out.println("Cancelado");
        }
    }
}
