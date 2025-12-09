import java.util.ArrayList;

public class GerenciadorDeClientes {
    final private ArrayList<Cliente> clientes = new ArrayList<>();
    private int proximoClienteId = 1;

    public void adicionarCliente(String nome, String cpf, String endereco) throws ValidacaoException {
        if(cpfJaCadastrado(cpf)) {
            throw new ValidacaoException("Já existe um cliente cadastrado com o CPF " + cpf);
        }
        Cliente novoCLiente = new Cliente(proximoClienteId++, nome, cpf, endereco);
        clientes.add(novoCLiente);
    }

    public Cliente buscarClientePorId(int id) {
        for(Cliente cliente: clientes) {
            if(cliente.getId() == id) {
                return cliente;
            }
        }
        throw new RuntimeException("Cliente não encontrado.");
    }

    public void listarClientes() {
        Utils.listarItens("LISTA DE CLIENTES", clientes);
    }

    private boolean cpfJaCadastrado(String cpf) {
        for(Cliente cliente: clientes) {
            if(cliente.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
