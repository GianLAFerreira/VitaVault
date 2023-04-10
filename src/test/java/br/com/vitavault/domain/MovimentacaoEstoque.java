package br.com.vitavault.domain;

import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Papel;
import br.com.vitavault.model.Produto;

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
    private Estoque estoque;

    /*
        Autora: Ariadne Cavilha Jorge
    */
    public MovimentacaoEstoque(ItemEstoque item, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this.id = UUID.randomUUID();
        this.item = item;
        this.funcionario = funcionario;
        this.alcada = alcada;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    public MovimentacaoEstoque(Produto produto, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this(new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), quantidade, tipoMovimentacao), funcionario, alcada, dataMovimentacao, tipoMovimentacao, quantidade);
    }

    /*
        Autora: Ariadne Cavilha Jorge
    */
    public void adicionaMovimentacao(EnumTipoMovimentacao tipoMovimentacao, Produto produto, ItemEstoque itemEstoque, Long quantidade) {
        Estoque estoque = new Estoque();

        if (tipoMovimentacao == EnumTipoMovimentacao.ENTRADA) {
            movimentarEntrada(produto, itemEstoque, quantidade, estoque);
        } else {
            // implementar o método movimentarSaida
        }
    }

    private void movimentarEntrada(Produto produto, ItemEstoque itemEstoque, Long quantidade, Estoque estoque) {
        estoque.vincularEstoqueAoItem(itemEstoque, estoque);
        setEstoque(estoque);
        //setarQuantidade(itemEstoque.getQuantidade(), quantidade, itemEstoque);
        itemEstoque.setQuantidade(quantidade);
        estoque.adicionarProduto(produto.getId(), itemEstoque);
        System.out.println("Id do produto no item estoque " + produto.getId());
        System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
    }

    private void setarQuantidade(Long quantidadeEstoque, Long quantidadeAumentar, ItemEstoque itemEstoque) {
        itemEstoque.setQuantidade(quantidadeEstoque + quantidadeAumentar);
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public ItemEstoque getItem() {
        return item;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public void listarProdutosEstoque() {
        estoque.listarProdutosEstoque();
    }
}
