package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.service.categoria.ICategoriaService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriaController {
    /**
     *  /categorias        GET  -- recuperar todas as categorias em ordem alfabetica
     *  /categorias     POST  -- cria uma categoria nova
     *  /categorias        PUT -- atualiza uma categoria
     *  /categorias/id     DELETE  -- deleta a categoria do id especificado
     *
     * */
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> getAllCategorias(){
        return ResponseEntity.ok(categoriaService.recuperarTodos());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria){
        try {
            Categoria result = categoriaService.cadastrarCategoria(categoria);
            if(result !=  null){
                return ResponseEntity.status(201).body(result);
            }
        }catch(Exception e){
            System.out.println("LOG - Erro ao cadastrar categoria: " + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria, @PathVariable int id){
        categoria.setId(id);
        try{
            Categoria result = categoriaService.atualizarCategoria(categoria);
            if(result !=  null){
                return ResponseEntity.ok(result);
            }

        }catch(Exception e){
            System.out.println("LOG - Erro ao atualizar categoria: " + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<Categoria> deleteCategoria(@PathVariable int id){
        try{
            categoriaService.deletarCategoria(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("LOG - Erro ao deletar categoria: " + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}
