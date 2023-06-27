
package br.com.vitavault.DAO;

import br.com.vitavault.controller.ProdutoInterface;
import br.com.vitavault.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements ProdutoInterface {
    private static List<Produto> produtos = new ArrayList();

    @Override
    public void salvarProduto(Produto oProduto) {
        produtos.add(oProduto);
    }

    @Override
    public List<Produto> buscarProdutos() {
        return produtos;
    }
    
}
