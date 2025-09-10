package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Categoria;
import br.com.vitorlopes.ecommerce.model.Produto;
import br.com.vitorlopes.ecommerce.service.produto.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {
    /**
     *  /produtos        GET  -- recuperar todos os produtos
     *  /produtos/id     GET  -- recuperar pelo ID
     *  /produtos        POST -- incluir um novo produto no banco
     *  /produtos/id     PUT  -- alterar o produto de ID especifico
     *  /produtos/busca  GET  -- buscar por palavra chave
     *          /produtos/busca?key = xxxxxxxx
     *
     * */

    @Autowired
    private IProdutoService produtoService;

    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> getAllProdutos(){
        return ResponseEntity.ok(produtoService.buscarTodosProdutos());
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Integer id){
        if(produtoService.buscarPorId(id) == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @GetMapping("/produtos/search")
    public ResponseEntity<List<Produto>> searchByKeyWord(@RequestParam(name="key" ) String keyWord){
        List<Produto> lista = produtoService.buscarPorPalavraChave(keyWord);
        if(lista.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/produtos")
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto){
        try{
            Produto result = produtoService.cadastroProduto(produto);
            if(result != null){
                return ResponseEntity.status(201).body(result);
            }
        }catch(Exception e){
            System.out.println("Erro ao criar produto" + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto, @PathVariable Integer id){
        produto.setId(id);
        try{
            Produto result = produtoService.alterarProduto(id, produto);
            if(result != null){
                return ResponseEntity.ok(result);
            }
        }catch(Exception e){
            System.out.println("Erro ao criar produto" + e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/produtos/categoria/{id}")
    public ResponseEntity<List<Produto>> getProdutosByCategoria(@PathVariable Integer id){
        Categoria categ = new Categoria();
        categ.setId(id);
        return ResponseEntity.ok(produtoService.buscarPorCategoria(categ));
    }

}
