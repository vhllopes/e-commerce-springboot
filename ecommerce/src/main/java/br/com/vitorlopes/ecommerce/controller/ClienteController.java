package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Cliente;
import br.com.vitorlopes.ecommerce.service.cliente.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    /**
     *  /clientes        GET  -- recuperar todos os clientes
     *  /clientes/id     GET  -- recuperar pelo ID
     *  /clientes        POST -- incluir um novo cliente no banco
     *  /clientes/id     PUT  -- alterar o cliente de ID especifico
     *  /clientes/busca  GET  -- buscar pelo telefone
     *          /clientes/busca?telefone = xxxxxxxx
     *
     * */

    @Autowired
    private IClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return ResponseEntity.ok(clienteService.recuperarTodos());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getClientesById(@PathVariable("id") Integer id){
        Cliente result = clienteService.recuperarClientePorId(id);
        if(result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("clientes/busca")
    public ResponseEntity<Cliente> searchByPhone(@RequestParam(name = "telefone") String telefone){
        Cliente result = clienteService.recuperarClientePorTelefone(telefone);
        if(result != null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/clientes")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
        try {
            Cliente result = clienteService.cadastrarCliente(cliente);
            if(result != null){
                return ResponseEntity.status(201).body(result);
            }
        } catch (Exception ex) {
            System.out.println("LOG - Erro ao cadastrar cliente: " + ex.getMessage());
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable("id") Integer id){
        cliente.setId(id);
        try{
            Cliente result = clienteService.atualizarCliente(id, cliente);
            if(result != null){
                return ResponseEntity.ok(result);
            }
        }catch (Exception ex){
            System.out.println("LOG - Erro ao atualizar cliente: " + ex.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

}
