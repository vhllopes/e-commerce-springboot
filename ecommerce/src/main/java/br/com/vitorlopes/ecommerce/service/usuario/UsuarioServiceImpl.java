package br.com.vitorlopes.ecommerce.service.usuario;

import br.com.vitorlopes.ecommerce.dao.UsuarioDAO;
import br.com.vitorlopes.ecommerce.model.Usuario;
import br.com.vitorlopes.ecommerce.security.ECToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioDAO dao;

    @Override
    public Usuario cadastrarUsuario(Usuario novoUsuario) {
        //criptografa a senha de um novo usuario
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String novaSenha = encoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(novaSenha);
        //salva novo usuario no banco
        return dao.save(novoUsuario);
    }

    /**
     * Atualiza os dados de um usuário existente no banco de dados.
     * Apenas os campos não nulos (nome, login, senha) do objeto 'dadosParaAtualizar'
     * serão usados para sobrescrever os dados existentes.
     *
     * @param id O ID do usuário a ser atualizado.
     * @param dadosParaAtualizar Um objeto Usuario contendo os novos dados.
     * @return O objeto Usuario atualizado e salvo no banco, ou null se o usuário não for encontrado.
     */
    @Override
    public Usuario atualizarUsuario(Integer id, Usuario dadosParaAtualizar) {
        // Passo 1: Buscar o usuário original e completo do banco de dados usando o ID.
        // Usei Optional para tratar de forma segura o caso de o usuário não existir.
        Optional<Usuario> optionalUsuario = dao.findById(id);

        // Verificar se o usuário foi realmente encontrado.
        if (optionalUsuario.isPresent()) {
            // Se encontramos, pegamos o objeto original que veio do banco.
            Usuario usuarioDoBanco = optionalUsuario.get();

            // Só atualizamos um campo se um novo valor para ele foi enviado (não é nulo nem vazio).

            // Atualiza o NOME se um novo nome foi fornecido.
            if (dadosParaAtualizar.getNome() != null && !dadosParaAtualizar.getNome().trim().isEmpty()) {
                usuarioDoBanco.setNome(dadosParaAtualizar.getNome());
            }

            // Atualiza o LOGIN se um novo login foi fornecido.
            if (dadosParaAtualizar.getLogin() != null && !dadosParaAtualizar.getLogin().trim().isEmpty()) {
                usuarioDoBanco.setLogin(dadosParaAtualizar.getLogin());
            }

            // Atualiza a SENHA se uma nova senha foi fornecida.
            if (dadosParaAtualizar.getSenha() != null && !dadosParaAtualizar.getSenha().isEmpty()) {
                // A lógica de encriptação só acontece aqui, se necessário.
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String novaSenhaCriptografada = encoder.encode(dadosParaAtualizar.getSenha());
                usuarioDoBanco.setSenha(novaSenhaCriptografada);
            }

            // Salva o objeto 'usuarioDoBanco', que agora contém os dados mesclados.
            return dao.save(usuarioDoBanco);

        } else {
            // Se o Optional estiver vazio, significa que não há usuário com esse ID.
            return null;
        }
    }

    @Override
    public ECToken fazerLogin(String login, String senha) {
        return null;
    }
}
