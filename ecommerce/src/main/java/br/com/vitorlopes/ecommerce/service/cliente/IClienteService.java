package br.com.vitorlopes.ecommerce.service.cliente;

import br.com.vitorlopes.ecommerce.model.Cliente;

import java.util.List;

public interface IClienteService {

    public Cliente cadastrarCliente(Cliente novo);
    public Cliente atualizarCliente(Cliente cliente);
    public Cliente recuperarClientePorId(Integer id);
    public Cliente recuperarClientePorTelefone(String telefone);
    public List<Cliente> recuperarTodos();
}
