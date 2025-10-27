import java.util.ArrayList;

public class Cliente extends Pessoa {
    final private String endereco;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    public Cliente(int id, String nome, String cpf, String endereco) {
        super(id, nome, cpf);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.print(" | Endereço: " + this.endereco + "\n");
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
}
