package br.com.vitavault.view;

import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Produto;

public class EstoquePrinter {
    public static void imprimirProduto(ItemEstoque item) {
        Produto produto = item.getProduto();

        System.out.println("Nome do produto: " + produto.getNome());
        System.out.println("Descrição: " + produto.getDescricao());
        System.out.println("Quantidade: " + item.getQuantidade());
        System.out.println("Preço: " + produto.getPreco());
        System.out.println("---------------------------");
    }
}
