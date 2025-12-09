public class PagamentoCartao extends Pagamento {
    private int parcelas;

    public PagamentoCartao(int idPedido, int parcelas) {
        super(idPedido);
        this.parcelas = (parcelas > 0) ? parcelas : 1;
    }

    @Override
    public void processarPagamento(double valor) {
        double valorParcela = valor / this.parcelas;

        System.out.println("Conectando com a operadora de cartão...");
        System.out.println("Processando...");

        if(this.parcelas == 1) {
            System.out.printf("Pagamento à vista de R$ %.2f aprovado no Crédito!%n", valor);
        } else {
            System.out.printf("Pagamento de R$ %.2f aprovado em %dx de R$ %.2f sem juros.%n", valor, this.parcelas,
                    valorParcela);
        }

        this.efetuado = true;
    }
}