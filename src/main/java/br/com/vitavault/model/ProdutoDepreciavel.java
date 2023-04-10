package br.com.vitavault.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class ProdutoDepreciavel extends Produto {

    private Date dataValidade;

    public ProdutoDepreciavel(UUID id, int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao, Date dataValidade) {
        super(id, codigo, nome, descricao, preco, categoria, situacao);
        this.dataValidade = dataValidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
}
