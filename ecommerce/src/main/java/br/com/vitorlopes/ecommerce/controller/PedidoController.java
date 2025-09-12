package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Pedido;
import br.com.vitorlopes.ecommerce.service.pedido.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class PedidoController {
    /**
     *
     *  /pedidos/id     GET  -- recuperar pedido pelo ID
     *  /pedidos        POST -- incluir um novo pedido no banco
     *  /pedidos/id     PUT  -- alterar um pedido de ID especifico
     *  /pedidos        GET  -- recuperar todos os pedidos
     *  /pedidos/status GET  -- recuperar pedidos por status
     *  /pedidos/data   GET  -- recuperar pedidos de uma determinada data
     *           /pedidos/data?datapedido = xx
     *
     * */
    @Autowired
    private IPedidoService service;

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        try{
            Pedido result = service.criarNovoPedido(pedido);
            if(result != null){
                return ResponseEntity.ok().body(result);
            }
        }catch(Exception e){
            System.out.println("LOG - Erro ao criar pedido" + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> updatePedido(@RequestBody Pedido pedido, @PathVariable Integer id){
        pedido.setNumeroPedido(id);
        try{
            Pedido result = service.alterarPedido(pedido);
            if(result != null){
                return ResponseEntity.ok().body(result);
            }
        }catch(Exception e){
            System.out.println("LOG - Erro ao atualizar pedido" + e.getMessage());
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> getAllPedidos(){
        return ResponseEntity.ok(service.recuperarTodos());
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Integer id){
        Pedido result = service.buscarPeloNumPedido(id);
        if(result != null){
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pedidos/status/{status}")
    public ResponseEntity<List<Pedido>> getPedidosByStatus(@PathVariable Integer status){
        return ResponseEntity.ok(service.buscarPeloStatus(status));
    }

    @GetMapping("/pedidos/data")
    public ResponseEntity<List<Pedido>> getPedidosData(@RequestParam("datapedido") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dataPedido){

        List<Pedido> lista = service.buscarPorData(dataPedido);

        if(lista != null){
            return ResponseEntity.ok().body(lista);
        }

        return ResponseEntity.noContent().build();
    }
}
