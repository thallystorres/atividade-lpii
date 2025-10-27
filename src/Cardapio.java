import jdk.jshell.execution.Util;

import java.util.ArrayList;

public class Cardapio {
    final private ArrayList<Categoria> categorias = new ArrayList<>();
    final private ArrayList<Produto> produtos = new ArrayList<>();
    private int proxixmaCategoriaId = 1;
    private int proximoProdutoId = 1;

    public void adicionarCategoria(String nome) {
        categorias.add(new Categoria(this.proxixmaCategoriaId++, nome));
    }

    public Categoria buscarCategoriaPorId(int id) {
        for(Categoria categoria: categorias) {
            if(categoria.getId() == id) {
                return categoria;
            }
        }
        throw new RuntimeException("Categoria não encontrada");
    }

    public void listarCategoria() {
        Utils.listarItens("Categorias disponíveis", categorias);
    }

    public void adicionarProduto(String nome, double preco, int categoriaId) {
        try {
            Categoria categoriaDoProduto = this.buscarCategoriaPorId(categoriaId);
            produtos.add(new Produto(this.proximoProdutoId++, nome, preco, categoriaDoProduto));
        } catch(RuntimeException e) {
            System.err.println("Não foi possível adicionar o produto: " + e);
        }
    }

    public Produto buscarProdutoPorId(int id) {
        for(Produto produto: produtos) {
            if(produto.getId() == id) {
                return produto;
            }
        }
        throw new RuntimeException("Produto não encontrado.");
    }

    public void listarProdutos() {
        Utils.listarItens("CARDÁPIO COMPLETO",produtos);
    }

    public void removeProduto(int id){
        for(Produto produto : produtos){
            if(produto.getId() == id){
                produtos.remove(produto);
                System.out.println("Produto removido com sucesso.");
                return;
            }
        }
        throw new RuntimeException("Produto a ser removido não encontrado.");
    }
}
