package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

    public Cliente findByTelefone(String telefone);
}
