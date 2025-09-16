package br.com.vitorlopes.ecommerce.service.categoria;

import br.com.vitorlopes.ecommerce.dao.CategoriaDAO;
import br.com.vitorlopes.ecommerce.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaServiceImpl implements ICategoriaService {
    @Autowired
    private CategoriaDAO dao;


    @Override
    public List<Categoria> recuperarTodos() {
        return dao.findAllByOrderByNomeAsc();
    }

    @Override
    public Categoria cadastrarCategoria(Categoria categoria) {
        return dao.save(categoria);
    }

    @Override
    public Categoria atualizarCategoria(Categoria categoria) {
        return dao.save(categoria);
    }

    @Override
    public void deletarCategoria(Integer id) {
        dao.deleteById(id);
    }
}
