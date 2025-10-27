import java.util.ArrayList;
public class GerenciadorDeClientes {
    final private ArrayList<Cliente> clientes = new ArrayList<>();
    private int proximoClienteId = 1;

    public void adicionarCliente(String nome, String cpf, String endereco){
        clientes.add(new Cliente(proximoClienteId++, nome, cpf, endereco));
    }

    public Cliente buscarClientePorId(int id){
        for(Cliente cliente : clientes){
            if(cliente.getId() == id){
                return cliente;
            }
        }
        throw new RuntimeException("Cliente não encontrado.");
    }
    public void listarClientes(){
        Utils.listarItens("LISTA DE CLIENTES", clientes);
    }
}
