package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoDAO extends JpaRepository<Produto, Integer> {
    public List<Produto> findByNomeContaining(String keyWord);
    public Page<Produto> findByOrderByNomeAsc(Pageable pageable);
    public List<Produto> findByCategoriasContaining(Categoria categoria);
}
