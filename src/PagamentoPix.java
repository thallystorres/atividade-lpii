public class PagamentoPix extends Pagamento {
    public PagamentoPix(int idPedido) {
        super(idPedido);
    }

    @Override
    public void processarPagamento(double valor) {
        System.out.println("É só ler o código da maquininha...");
        System.out.println("Pagamento de R$" + valor + " recebido via PIX!");
        this.efetuado = true;
    }
}
