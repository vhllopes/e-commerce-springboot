package br.com.vitorlopes.ecommerce.service.pedido;

import br.com.vitorlopes.ecommerce.dao.PedidoDAO;
import br.com.vitorlopes.ecommerce.model.ItemPedido;
import br.com.vitorlopes.ecommerce.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PedidoServiceImpl implements IPedidoService{
    @Autowired
    private PedidoDAO dao;

    @Override
    public Pedido criarNovoPedido(Pedido pedido) {
        //Associa cada item ao pedido correspondente
        for(ItemPedido item : pedido.getItens()){
            item.setPedido(pedido);
        }

        return dao.save(pedido);
    }

    @Override
    public Pedido alterarPedido(Pedido pedido) {
        for (ItemPedido item : pedido.getItens()){
            item.setPedido(pedido);
        }
        return dao.save(pedido);
    }

    @Override
    public List<Pedido> recuperarTodos() {
        return (List<Pedido>) dao.findAll();
    }

    @Override
    public Pedido buscarPeloNumPedido(Integer numPedido) {
        return dao.findById(numPedido).orElse(null);
    }

    @Override
    public List<Pedido> buscarPeloStatus(Integer status) {
        return dao.findAllByStatusPedido(status);
    }

    @Override
    public List<Pedido> buscarPorData(Date dataPedido) {
        return dao.findAllByDataPedido(dataPedido);
    }
}
