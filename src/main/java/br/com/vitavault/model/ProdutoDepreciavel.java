package br.com.vitavault.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ProdutoDepreciavel extends Produto {

    private LocalDate dataValidade;

    public ProdutoDepreciavel(UUID id, int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao, LocalDate dataValidade) {
        super(id, codigo, nome, descricao, preco, categoria, situacao);
        this.dataValidade = dataValidade;
    }

    public ProdutoDepreciavel(int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao, LocalDate dataValidade) {
        super(codigo, nome, descricao, preco, categoria, situacao);
        this.dataValidade = dataValidade;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
