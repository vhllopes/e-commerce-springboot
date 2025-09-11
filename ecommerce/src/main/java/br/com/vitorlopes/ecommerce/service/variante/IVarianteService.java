package br.com.vitorlopes.ecommerce.service.variante;

import br.com.vitorlopes.ecommerce.model.Produto;
import br.com.vitorlopes.ecommerce.model.Variante;

import java.util.List;

public interface IVarianteService {
    public Variante buscarPorId(Integer id);
    public List<Variante> buscarPorProduto(Produto produto);
    public Variante adicionarVariante(Variante variante);
    public Variante atualizarVariante(Integer id, Variante variante);
}
