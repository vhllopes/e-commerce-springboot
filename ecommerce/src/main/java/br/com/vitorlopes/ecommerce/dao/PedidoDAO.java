package br.com.vitorlopes.ecommerce.dao;

import br.com.vitorlopes.ecommerce.dto.FaturamentoMensal;
import br.com.vitorlopes.ecommerce.model.Cliente;
import br.com.vitorlopes.ecommerce.model.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PedidoDAO extends CrudRepository<Pedido, Integer> {

    public List<Pedido> findAllByStatusPedido(Integer statusPedido);
    public List<Pedido> findAllByDataPedido(Date dataPedido);

    /*criando um query customizada para recuperar o faturamento*/
    @Query("SELECT new br.com.vitorlopes.ecommerce.dto.FaturamentoMensal(month(p.dataPedido), sum(p.valorTotal)) "
            + "FROM Pedido p "
            + "WHERE year(p.dataPedido) = :ano "
            + "GROUP BY month(p.dataPedido) "
            + "ORDER BY month(p.dataPedido)")
    public List<FaturamentoMensal> recuperarFaturamento(@Param("ano") Integer ano);
}
