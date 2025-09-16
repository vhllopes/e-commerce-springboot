package br.com.vitorlopes.ecommerce.service.produto;

import br.com.vitorlopes.ecommerce.dao.ProdutoDAO;
import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;

import java.util.List;

@Component
public class ProdutoServiceImpl implements IProdutoService {
    @Autowired
    private ProdutoDAO dao;

    private static final int PAGE_SIZE = 5;

    @Override
    public Produto cadastroProduto(Produto produto) {
        return dao.save(produto);
    }

    @Override
    public Produto alterarProduto(Integer id, Produto produto) {
        //Tenta encontrar o produto existente no banco.
        Produto produtoDoBanco = dao.findById(id).orElse(null); // Retorna null se não encontrar

        //Se não encontrou, retorna nulo para o controller responder com 404 Not Found.
        if (produtoDoBanco == null) {
            return null;
        }

        //ATUALIZA o objeto do banco com os dados da requisição (se não forem nulos).
        if(produto.getNome() == null){
            produto.setNome(produtoDoBanco.getNome());
        }
        if(produto.getDescricao() == null){
            produto.setDescricao(produtoDoBanco.getDescricao());
        }
        if(produto.getPreco() == null){
            produto.setPreco(produtoDoBanco.getPreco());
        }
        if(produto.getDestaque() == null){
            produto.setDestaque(produtoDoBanco.getDestaque());
        }
        if(produto.getDisponibilidade() == null){
            produto.setDisponibilidade(produtoDoBanco.getDisponibilidade());
        }

        // Salva o objeto que foi modificado
        return dao.save(produto);
    }

    @Override
    public Page<Produto> buscarTodosProdutos(int numPagina) {
        Pageable pageable = PageRequest.of(numPagina-1, PAGE_SIZE);
        return dao.findByOrderByNomeAsc(pageable);
    }

    @Override
    public List<Produto> buscarPorPalavraChave(String keyWord) {
        return dao.findByNomeContaining(keyWord);
    }

    @Override
    public Produto buscarPorId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Produto> buscarPorCategoria(Categoria categoria) {
        return dao.findByCategoriasContaining(categoria);
    }
}