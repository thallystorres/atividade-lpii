import java.util.ArrayList;
public class Comanda {
    final private ArrayList<Pedido> pedidos = new ArrayList<>();
    private int proximoPedidoId = 1;

    public void registrarPedido(Pedido pedido){
        System.out.println("\nPedido registrado com sucesso no sistema!");
        pedidos.add(pedido);
    }

    public int getProximoPedidoId(){
        return this.proximoPedidoId++;
    }
}
