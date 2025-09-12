package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.model.Cliente;
import br.com.vitorlopes.ecommerce.model.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PedidoDAO extends CrudRepository<Pedido, Integer> {

    public List<Pedido> findAllByStatusPedido(Integer statusPedido);
    public List<Pedido> findAllByDataPedido(Date dataPedido);
}
