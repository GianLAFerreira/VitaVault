
package br.com.vitavault.controller;

import br.com.vitavault.model.Produto;
import java.util.List;

public interface ProdutoInterface {
    void salvarProduto(Produto oProduto);
    List<Produto> buscarProdutos();
}
