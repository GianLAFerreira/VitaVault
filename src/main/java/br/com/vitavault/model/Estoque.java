package main.java.br.com.vitavault.model;

import java.util.HashMap;
import java.util.List;
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

    public List<Produto> listar() {
        //#PROG-8 -implementar logica de adicionar produto
        return null;
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
