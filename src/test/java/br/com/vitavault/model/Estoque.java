package br.com.vitavault.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Estoque {
    private Map<UUID, ItemEstoque> itens;

    public void adicionarProduto(UUID id, ItemEstoque item) {
        if (itens == null) {
            itens = new HashMap<>();
        }
        itens.put(id, item);
        System.out.println("Id do produto no estoque " + id);
    }

    public void removerProduto(Produto produto, Long quantidade) {
        //#PROG-7 -implementar logica de adicionar produto
    }

    public void listarProdutosEstoque() {
        if (!itens.isEmpty())
            listagemProdutos();
        else
            System.out.println("Não existem produtos para serem listados.");
    }

    private void listagemProdutos() {
        for (Map.Entry<UUID, ItemEstoque> item : itens.entrySet()) {
            String itemListagem = String.format("%nID do Produto: %s %nNome do Produto: %s %nDescrição do produto: %s %nQuantidade em Estoque: %d %nPreço Unitário: %f",
                    item.getKey(),
                    item.getValue().getProduto().getNome(),
                    item.getValue().getProduto().getDescricao(),
                    item.getValue().getQuantidade(),
                    item.getValue().getProduto().getPreco());

            System.out.println(itemListagem);
        }
    }

    public Map<UUID, ItemEstoque> getItens() {
        return itens;
    }

    public void setItens(Map<UUID, ItemEstoque> itens) {
        this.itens = itens;
    }

    public void vincularEstoqueAoItem(ItemEstoque itemEstoque, Estoque estoque) {
        itemEstoque.setEstoque(estoque);
    }
}
