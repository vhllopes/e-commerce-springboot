package br.com.vitorlopes.ecommerce.service.usuario;

import br.com.vitorlopes.ecommerce.model.Usuario;
import br.com.vitorlopes.ecommerce.security.ECToken;

public interface IUsuarioService {
    public Usuario cadastrarUsuario(Usuario novoUsuario);
    public Usuario atualizarUsuario(Integer id, Usuario usuario);
    public ECToken fazerLogin(String login, String senha);
}
