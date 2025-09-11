package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Produto;
import br.com.vitorlopes.ecommerce.model.Variante;
import br.com.vitorlopes.ecommerce.service.variante.IVarianteService;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VarianteController {
    @Autowired
    private IVarianteService service;

    @PostMapping("/variantes")
    public ResponseEntity<Variante> createVariante(@RequestBody Variante variante){
        try{
            Variante result = service.adicionarVariante(variante);
            if(result != null){
                return ResponseEntity.ok().body(result);
            }
        }catch(Exception e){
            System.out.println("LOG - Erro ao criar variante" + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/variantes/{id}")
    public ResponseEntity<Variante> updateVariante(@RequestBody Variante variante, @PathVariable Integer id){
        variante.setId(id);
        try{
            Variante result = service.atualizarVariante(id, variante);
            if(result != null){
                return ResponseEntity.ok().body(result);
            }
        }catch(Exception e){
            System.out.println("LOG - Erro ao atualizar variante" + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/variantes/{id}")
    public ResponseEntity<Variante> getVarianteById(@PathVariable Integer id){
        Variante result = service.buscarPorId(id);
        if(result != null){
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/variantes")
    public ResponseEntity<List<Variante>> getVariantesByProduto(@RequestParam(name="idproduto") Integer idProduto){
        Produto p = new Produto();
        p.setId(idProduto);
        return ResponseEntity.ok(service.buscarPorProduto(p));
    }
}
