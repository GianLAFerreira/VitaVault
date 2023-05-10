package br.com.vitavault.model;

import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ItemEstoque {

    private UUID id;
    private Produto produto;
    private Estoque estoque;
    private LocalDate data;
    private Long quantidade;
    private EnumTipoMovimentacao tipoMovimentacao;
    private List<MovimentacaoEstoque> movimentacao = new ArrayList<>();

    public ItemEstoque(UUID id, Produto produto, LocalDate data, Long quantidade, EnumTipoMovimentacao tipoMovimentacao) {
        this.id = id;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public ItemEstoque() {
    }

    public UUID getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public List<MovimentacaoEstoque> getMovimentacao() {
        return movimentacao;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEstoque that = (ItemEstoque) o;
        return Objects.equals(id, that.id) && Objects.equals(produto, that.produto) && Objects.equals(estoque, that.estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produto, estoque);
    }
}
