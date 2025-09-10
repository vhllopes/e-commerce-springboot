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
    public Cliente atualizarCliente(Integer id, Cliente cliente) {

        //Tenta encontrar o cliente existente no banco.
        Cliente clienteDoBanco = cliDao.findById(id).orElse(null); // Retorna null se não encontrar

        //Se não encontrou, retorna nulo para o controller responder com 404 Not Found.
        if (clienteDoBanco == null) {
            return null;
        }

        //ATUALIZA o objeto do banco com os dados da requisição (se não forem nulos).
        if(cliente.getNome() == null){
            cliente.setNome(clienteDoBanco.getNome());
        }
        if(cliente.getTelefone() == null){
            cliente.setTelefone(clienteDoBanco.getTelefone());
        }
        if(cliente.getEmail() == null){
            cliente.setEmail(clienteDoBanco.getEmail());
        }
        if(cliente.getCep() == null){
            cliente.setCep(clienteDoBanco.getCep());
        }
        if(cliente.getBairro() == null){
            cliente.setBairro(clienteDoBanco.getBairro());
        }
        if(cliente.getCidade() == null){
            cliente.setCidade(clienteDoBanco.getCidade());
        }
        if(cliente.getEstado() == null){
            cliente.setEstado(clienteDoBanco.getEstado());
        }
        if(cliente.getLogradouro() == null){
            cliente.setLogradouro(clienteDoBanco.getLogradouro());
        }
        if(cliente.getNumero() == null){
            cliente.setNumero(clienteDoBanco.getNumero());
        }
        if(cliente.getComplemento() == null){
            cliente.setComplemento(clienteDoBanco.getComplemento());
        }

        // Salva o objeto que foi modificado.
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
