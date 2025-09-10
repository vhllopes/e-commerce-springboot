package br.com.vitorlopes.ecommerce.service.produto;

import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.model.Produto;

import java.util.List;

public interface IProdutoService {

    public Produto cadastroProduto(Produto produto);
    public Produto alterarProduto(Integer id, Produto produto);
    public List<Produto> buscarTodosProdutos();
    public List<Produto> buscarPorPalavraChave(String keyWord);
    public Produto buscarPorId(Integer id);
    public List<Produto> buscarPorCategoria(Categoria categoria);
}
