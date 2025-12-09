public abstract class Pagamento {
    protected int idPedido;
    protected boolean efetuado;

    public Pagamento(int idPedido) {
        this.idPedido = idPedido;
        this.efetuado = false;
    }

    public abstract void processarPagamento(double valor);

    public boolean isEfetuado() {
        return efetuado;
    }

    @Override
    public String toString() {
        return "Detalhes do pagamento (Pedido " + this.idPedido + "): " + (efetuado ? "Status: Efetuado" :
                "Status: " + "Pagamento não realizados");
    }
}
