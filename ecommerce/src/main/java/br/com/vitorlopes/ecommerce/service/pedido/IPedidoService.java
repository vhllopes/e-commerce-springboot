package br.com.vitorlopes.ecommerce.service.pedido;

import br.com.vitorlopes.ecommerce.model.ItemPedido;
import br.com.vitorlopes.ecommerce.model.Pedido;

import java.util.Date;
import java.util.List;

public interface IPedidoService {
    public Pedido criarNovoPedido(Pedido pedido);
    public Pedido alterarPedido(Pedido pedido);
    public List<Pedido> recuperarTodos();
    public Pedido buscarPeloNumPedido(Integer numPedido);
    public List<Pedido> buscarPeloStatus(Integer status);
    public List<Pedido> buscarPorData(Date dataPedido);
}
