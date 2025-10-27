public class Pagamento {
    final private int idPedido;
    private String metodo;
    private boolean efetuado;

    public Pagamento(int idPedido) {
        this.idPedido = idPedido;
    }

    public void processarPagamento(String metodoPagamento, double valor) {
        this.metodo = metodo;
        this.efetuado = true;
        System.out.println("Pagamento de R$" + valor + "processado em " + metodo + ".");
    }

    @Override
    public String toString() {
        return "Detalhes do pagamento (Pedido " + this.idPedido + "): " + (efetuado ? "Método: " + this.metodo + " | "
                + "Status: Efetuado" : "Status: Pagamento não realizados");
    }
}
