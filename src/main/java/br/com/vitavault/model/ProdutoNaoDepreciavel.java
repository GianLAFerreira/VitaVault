package br.com.vitavault.model;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoNaoDepreciavel extends Produto {
    public ProdutoNaoDepreciavel(UUID id, int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao) {
        super(id, codigo, nome, descricao, preco, categoria, situacao);
    }

    public ProdutoNaoDepreciavel(int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao) {
        super(codigo, nome, descricao, preco, categoria, situacao);
    }
}
