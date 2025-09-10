package br.com.vitorlopes.ecommerce.service.categoria;

import br.com.vitorlopes.ecommerce.model.Categoria;

import java.util.List;

public interface ICategoriaService {

    public List<Categoria> recuperarTodos();
    public Categoria cadastrarCategoria(Categoria categoria);
    public Categoria atualizarCategoria(Categoria categoria);
    public void deletarCategoria(Integer id);
}
