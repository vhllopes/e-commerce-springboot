package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
    public Usuario findByLogin(String login);
}
