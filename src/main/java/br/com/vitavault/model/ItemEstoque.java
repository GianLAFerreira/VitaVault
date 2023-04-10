package br.com.vitavault.model;

import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public EnumTipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(EnumTipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public List<MovimentacaoEstoque> getMovimentacao() {
        return movimentacao;
    }
}
