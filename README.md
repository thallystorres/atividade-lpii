classDiagram
class Pessoa {
-int id
-string nome
-string cpf
+Pessoa(int id, string nome, string cpf)
+mostrarDados() void
+setNome(string nome) void
+getId() int
+getNome() string
+getCpf() string
}

    class Cliente {
        -string endereco
        -vector~Pedido~ pedidos
        +Cliente(int id, string nome, string cpf, string endereco)
        +getEndereco() string
        +mostrarDados() void
        +adicionarPedido(Pedido pedido) void
    }

    class Funcionario {
        -string cargo
        -double salario
        +Funcionario(int id, string nome, string cpf, string cargo, double salario)
        +mostrarDados() void
    }

    class Categoria {
        -int id
        -string nome
        +Categoria(int id, string nome)
        +getId() int
        +getNome() string
        +mostrarDados() void
    }

    class Produto {
        -int id
        -string nome
        -double preco
        -Categoria* categoria
        +Produto(int id, string nome, double preco, Categoria* categoria)
        +getId() int
        +getNome() string
        +getPreco() double
        +getCategoria() Categoria*
        +mostrarDados() void
    }

    class Pedido {
        -int id
        -string data
        -vector~Produto*~ produtos
        -Pagamento pagamento
        -StatusPedido status
        +Pedido(int id, string data)
        +getPagamento() Pagamento
        +marcarComoPago() void
        +adicionarProduto(Produto* produto) void
        +calcularTotal() double
        +mostrarDados() void
    }

    class Pagamento {
        -int idPedido
        -string metodo
        -bool efetuado
        +Pagamento(int idPedido)
        +processarPagamento(string metodoPagamento, double valor) void
        +mostrarDados() void
    }
    
    class Cardapio {
        -vector~Categoria~ categorias
        -vector~Produto~ produtos
        -int proximaCategoriaId
        -int proximoProdutoId
        +adicionarCategoria(string nome) void
        +buscarCategoriaPorId(int id) Categoria*
        +listarCategorias() void
        +adicionarProduto(string nome, double preco, int categoriaId) void
        +buscarProdutoPorId(int id) Produto*
        +listarProdutos() void
        +removerProduto(int id) void
    }

    class GerenciadorClientes {
        -vector~Cliente~ clientes
        -int proximoClienteId
        +adicionarCliente(string nome, string cpf, string endereco) void
        +buscarClientePorId(int id) Cliente*
        +listarClientes() void
    }

    class Comanda {
        -vector~Pedido~ todosOsPedidos
        -int proximoPedidoId
        +registrarPedido(Pedido pedido) void
        +getProximoId() int
    }

    Pessoa <|-- Cliente
    Pessoa <|-- Funcionario
    
    GerenciadorClientes "1" *-- "0..*" Cliente : gerencia
    Cliente "1" o-- "0..*" Pedido : faz
    
    Comanda "1" *-- "0..*" Pedido : registra
    
    Pedido "1" *-- "1..*" Produto : contém
    Pedido "1" -- "1" Pagamento : tem
    
    Cardapio "1" *-- "0..*" Produto : gerencia
    Cardapio "1" *-- "0..*" Categoria : gerencia
    Produto "1" -- "1" Categoria : pertence a
