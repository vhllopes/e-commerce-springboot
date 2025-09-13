package br.com.vitorlopes.ecommerce.controller;

import br.com.vitorlopes.ecommerce.model.Usuario;
import br.com.vitorlopes.ecommerce.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Usuario res =  service.cadastrarUsuario(usuario);
        if (res != null) {
            return ResponseEntity.ok().body(res);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        try{
            Usuario res =  service.atualizarUsuario(id, usuario);
            if (res != null) {
                return ResponseEntity.ok().body(res);
            }
        }catch (Exception e){
            System.out.println("LOG - Erro ao atualizar usuario" + e.getMessage());
        }
        return ResponseEntity.badRequest().body(usuario);
    }
}
