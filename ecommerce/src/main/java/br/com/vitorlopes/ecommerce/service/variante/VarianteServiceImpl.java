package br.com.vitorlopes.ecommerce.service.variante;

import br.com.vitorlopes.ecommerce.dao.VarianteDAO;
import br.com.vitorlopes.ecommerce.model.Produto;
import br.com.vitorlopes.ecommerce.model.Variante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VarianteServiceImpl implements IVarianteService{

    @Autowired
    private VarianteDAO dao;

    @Override
    public Variante buscarPorId(Integer id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public List<Variante> buscarPorProduto(Produto produto) {
        return dao.findByProduto(produto);
    }

    @Override
    public Variante adicionarVariante(Variante variante) {
        return dao.save(variante);
    }

    @Override
    public Variante atualizarVariante(Integer id, Variante variante) {
        //Tenta encontrar a variante existente no banco.
        Variante varianteBanco = dao.findById(id).orElse(null); // Retorna null se não encontrar

        //Se não encontrou, retorna nulo para o controller responder com 404 Not Found.
        if (varianteBanco == null) {
            return null;
        }

        //ATUALIZA o objeto do banco com os dados da requisição (se não forem nulos).
        if(variante.getNome() == null){
            variante.setNome(varianteBanco.getNome());
        }
        if(variante.getNome() == null){
            variante.setNome(varianteBanco.getNome());
        }
        if(variante.getDescricao() == null){
            variante.setDescricao(varianteBanco.getDescricao());
        }
        if(variante.getLinkFoto() == null){
            variante.setLinkFoto(varianteBanco.getLinkFoto());
        }

        // Salva o objeto que foi modificado
        return dao.save(variante);
    }
}
