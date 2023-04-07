package main.java.br.com.vitavault.domain;

import main.java.br.com.vitavault.model.Estoque;
import main.java.br.com.vitavault.model.Funcionario;
import main.java.br.com.vitavault.model.ItemEstoque;
import main.java.br.com.vitavault.model.Papel;
import main.java.br.com.vitavault.model.Produto;

import java.time.LocalDate;
import java.util.UUID;

public class MovimentacaoEstoque {
    private UUID id;
    private ItemEstoque item;
    private Funcionario funcionario;
    private Papel alcada;
    private LocalDate dataMovimentacao;
    private EnumTipoMovimentacao tipoMovimentacao;
    private Long quantidade;

    public MovimentacaoEstoque(UUID id, ItemEstoque item, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this.id = id;
        this.item = item;
        this.funcionario = funcionario;
        this.alcada = alcada;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    public void movimentarEntrada(Estoque estoque, Produto produto, ItemEstoque itemEstoque) {
        estoque.adicionarProduto(produto.getId(), itemEstoque);
        System.out.println("Movimentando entrada");
    }
}
