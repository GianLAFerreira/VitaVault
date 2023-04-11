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
    // private Estoque estoque;

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
        this(new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), 0L, tipoMovimentacao), funcionario, alcada, dataMovimentacao, tipoMovimentacao, quantidade);
    }

    /*
        Autora: Ariadne Cavilha Jorge
    */
    public void adicionaMovimentacao(EnumTipoMovimentacao tipoMovimentacao, Produto produto, ItemEstoque itemEstoque, Long quantidade) {
        //Estoque estoque = new Estoque();

        if (tipoMovimentacao == EnumTipoMovimentacao.ENTRADA) {
            //  movimentarEntrada(produto, itemEstoque, quantidade, estoque);
        } else {
            // implementar o método movimentarSaida
        }
    }

    public void movimentarEntrada(ItemEstoque itemEstoque, Long quantidade, Estoque estoque) {
        itemEstoque = estoque.vincularEstoqueAoItem(itemEstoque, estoque);

        vincularMovimentacaoAoItem(itemEstoque);
        setarQuantidade(itemEstoque, quantidade);


        System.out.println("Id do item estoque " + itemEstoque.getId());
        System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
    }

    private void vincularMovimentacaoAoItem(ItemEstoque itemEstoque) {
        itemEstoque.getMovimentacao().add(this);
    }

    private void setarQuantidade(ItemEstoque itemEstoque, Long quantidadeAumentar) {
        itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidadeAumentar);
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

//    public void setEstoque(Estoque estoque) {
//        this.estoque = estoque;
//    }

//    public void listarProdutosEstoque() {
//        estoque.listarProdutosEstoque();
//    }
}
