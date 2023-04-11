package br.com.vitavault.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Estoque {
    private Map<UUID, ItemEstoque> itens;

    public Estoque(Map<UUID, ItemEstoque> itens) {
        this.itens = itens;
    }

    public Map<UUID, ItemEstoque> getItens() {
        return itens;
    }

    public void setItens(Map<UUID, ItemEstoque> itens) {
        this.itens = itens;
    }

    public void removerProduto(Produto produto, Long quantidade) {
        //#PROG-7 -implementar logica de adicionar produto
    }

    public void adicionarProdutoNoEstoque(UUID id, ItemEstoque item) {
        if (itens == null) {
            itens = new HashMap<>();
        }
        itens.put(id, item);
    }

    public void listar() {
        if (!itens.isEmpty())
            listarProdutosEstoque();
        else
            System.out.println("Não existem produtos para serem listados.");
    }

    private void listarProdutosEstoque() {
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

    public ItemEstoque vincularEstoqueAoItem(ItemEstoque itemEstoque) {
        if (this.itens.get(itemEstoque.getId()) == null) {
            itemEstoque.setEstoque(this);
            adicionarProdutoNoEstoque(itemEstoque.getId(), itemEstoque);
            System.out.println("vinculou item ao estoque");
            return this.itens.get(itemEstoque.getId());
        } else {
            System.out.println("Ja existe o item no estoque");
            return itemEstoque;
        }
    }

    public ItemEstoque buscarItem(ItemEstoque itemEstoque) {
        if (this.itens.get(itemEstoque.getId()) == null) {

            System.out.println("O item não existe no estoque");
            return null;
        } else {
            System.out.println("O item existe no estoque");
            return this.itens.get(itemEstoque.getId());
        }
    }
}
