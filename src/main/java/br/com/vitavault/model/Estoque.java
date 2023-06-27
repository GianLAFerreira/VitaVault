package br.com.vitavault.model;

import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.exceptions.EstoqueException;
import br.com.vitavault.view.EstoquePrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Estoque {

    private Set<ItemEstoque> itens;
    private UUID id;

    public Estoque(Set<ItemEstoque> itens) {
        this.itens = itens;
    }

    public Estoque(Set<ItemEstoque> itens, UUID id) {
        this.itens = itens;
        this.id = id;
    }

    public void removerProduto(ItemEstoque itemEstoque) throws EstoqueException {
        boolean itemRemovido = itens.remove(itemEstoque);

        if (!itemRemovido) {
            throw new EstoqueException("O item do produto " + itemEstoque.getProduto().getNome() + " não foi encontrado no estoque.");
        }
    }

    public void adicionarProdutoNoEstoque(ItemEstoque item) {
        if (itens == null) {
            itens = new HashSet<>();
        }
        itens.add(item);
    }

    public void listar() {
        if (!itens.isEmpty())
            listarProdutosEstoque();
        else
            System.out.println("Não existem produtos para serem listados.");
    }

    private void listarProdutosEstoque() {
        for (ItemEstoque item : itens) {
            EstoquePrinter.imprimirProduto(item);
        }
    }

    public ItemEstoque vincularEstoqueAoItem(ItemEstoque itemEstoque) {
        if (!this.itens.contains(itemEstoque)) {
            itemEstoque.setEstoque(this);
            adicionarProdutoNoEstoque(itemEstoque);
            System.out.println("vinculou item ao estoque");
            return itemEstoque;
        } else {
            System.out.println("Ja existe o item no estoque");
            return itemEstoque;
        }
    }

    public ItemEstoque buscarItem(ItemEstoque itemEstoque) throws EstoqueException {
        for (ItemEstoque item : itens) {
            if (item.getId().equals(itemEstoque.getProduto().getId())) {
                System.out.println("O item existe no estoque");
                return item;
            } else {
                throw new EstoqueException("O item: " + itemEstoque.getProduto().getNome() + " não existe no estoque");
            }
        }
        return null;
    }

    public Set<ItemEstoque> getItens() {
        return itens;
    }

    public List<ItemEstoque> getItensList() {
        List<ItemEstoque> itemEstoques = new ArrayList<>();
        for (ItemEstoque item : itens) {
            itemEstoques.add(item);
        }

        return itemEstoques;
    }

    public List<MovimentacaoEstoque> getMovimentacoes() {
        List<MovimentacaoEstoque> movimentacaoEstoqueList = new ArrayList<>();
        for (ItemEstoque item : itens) {
            for (MovimentacaoEstoque movimentacaoEstoque : item.getMovimentacao()) {
                movimentacaoEstoqueList.add(movimentacaoEstoque);
            }
        }

        return movimentacaoEstoqueList;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
