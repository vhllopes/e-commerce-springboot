package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.model.Produto;
import br.com.vitorlopes.ecommerce.model.Variante;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VarianteDAO extends CrudRepository<Variante, Integer> {
    public List<Variante> findByProduto(Produto produto);
}
