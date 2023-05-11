package br.com.vitavault.domain;

import br.com.vitavault.exceptions.EstoqueException;
import br.com.vitavault.exceptions.MovimentacaoEstoqueException;
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
    private final Estoque estoque;

    private MovimentacaoEstoque(ItemEstoque item, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade, Estoque estoque) {
        this.estoque = estoque;
        this.id = UUID.randomUUID();
        this.item = item;
        this.funcionario = funcionario;
        this.alcada = alcada;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    public MovimentacaoEstoque(Produto produto, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade, Estoque estoque) {
        this(new ItemEstoque(produto.getId(), produto, dataMovimentacao, 0L, tipoMovimentacao), funcionario, alcada, dataMovimentacao, tipoMovimentacao, quantidade, estoque);
    }

    public void movimentarEstoque(ItemEstoque itemEstoque, Long quantidade, EnumTipoMovimentacao tipoMovimentacao) throws MovimentacaoEstoqueException, EstoqueException {
        if (tipoMovimentacao.equals(EnumTipoMovimentacao.ENTRADA)) {
            movimentarEntrada(itemEstoque, quantidade);
            System.out.println("Id do item estoque " + itemEstoque.getId());
            System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
        } else {
            movimentarSaida(itemEstoque, quantidade);
            System.out.println("Id do item estoque " + itemEstoque.getId());
            System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
        }
    }

    private void movimentarEntrada(ItemEstoque itemEstoque, Long quantidade) {
        itemEstoque = estoque.vincularEstoqueAoItem(itemEstoque);

        vincularMovimentacaoAoItem(itemEstoque);
        aumentarQuantidadeItemEstoque(itemEstoque, quantidade);
    }

    private void movimentarSaida(ItemEstoque itemEstoque, Long quantidade) throws MovimentacaoEstoqueException, EstoqueException {
        itemEstoque = estoque.buscarItem(itemEstoque);


        Long saldoAnterior = calcularSaldoAnterior(itemEstoque, this.getDataMovimentacao());

        vincularMovimentacaoAoItem(itemEstoque);

        if (saldoAnterior >= quantidade) {
            diminuirQuantidadeItemEstoque(itemEstoque, quantidade);
            System.out.println("Id do item estoque " + itemEstoque.getId());
            System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
        } else {
            throw new MovimentacaoEstoqueException("Saldo insuficiente");
        }
    }

    private Long calcularSaldoAnterior(ItemEstoque itemEstoque, LocalDate dataMovimentacao) {
        Long entradas = 0L;
        Long saidas = 0L;

        for (MovimentacaoEstoque movimentacao : itemEstoque.getMovimentacao()) {
            if (movimentacao.getDataMovimentacao().isBefore(dataMovimentacao) || movimentacao.getDataMovimentacao().isEqual(dataMovimentacao)) {
                if (movimentacao.getTipoMovimentacao() == EnumTipoMovimentacao.ENTRADA) {
                    entradas += movimentacao.getQuantidade();
                } else if (movimentacao.getTipoMovimentacao() == EnumTipoMovimentacao.SAIDA) {
                    saidas += movimentacao.getQuantidade();
                }
            }
        }

        return entradas - saidas;
    }

    private void vincularMovimentacaoAoItem(ItemEstoque itemEstoque) {
        itemEstoque.getMovimentacao().add(this);
    }

    private void aumentarQuantidadeItemEstoque(ItemEstoque itemEstoque, Long quantidadeAumentar) {
        itemEstoque.setQuantidade(itemEstoque.getQuantidade() + quantidadeAumentar);
    }

    private void diminuirQuantidadeItemEstoque(ItemEstoque itemEstoque, Long quantidadeAumentar) throws MovimentacaoEstoqueException, EstoqueException {
        if (itemEstoque.getQuantidade().compareTo(quantidadeAumentar) >= 0) {
            itemEstoque.setQuantidade(itemEstoque.getQuantidade() - quantidadeAumentar);
        } else {
            throw new MovimentacaoEstoqueException("Saldo insuficiente");
        }
    }

    public ItemEstoque getItem() {
        return item;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public EnumTipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }
}
