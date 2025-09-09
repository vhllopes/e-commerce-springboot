package br.com.vitorlopes.ecommerce.service.cliente;

import br.com.vitorlopes.ecommerce.dao.ClienteDAO;
import br.com.vitorlopes.ecommerce.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ClienteServiceImpl implements IClienteService{

    @Autowired
    private ClienteDAO cliDao;


    @Override
    public Cliente cadastrarCliente(Cliente novo) {
        return cliDao.save(novo);
    }

    @Override
    public Cliente atualizarCliente(Cliente cliente) {
        return cliDao.save(cliente);
    }

    @Override
    public Cliente recuperarClientePorId(Integer id) {
        return cliDao.findById(id).orElse(null);
    }

    @Override
    public Cliente recuperarClientePorTelefone(String telefone) {
        return cliDao.findByTelefone(telefone);
    }

    @Override
    public List<Cliente> recuperarTodos() {
        return (List<Cliente>) cliDao.findAll();
    }
}
