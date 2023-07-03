package br.com.vitavault.domain;

import br.com.vitavault.dao.ItemEstoqueRepository;
import br.com.vitavault.dao.MovimentacaoEstoqueRepository;
import br.com.vitavault.dao.impl.ItemEstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.MovimentacaoEstoqueRepositoryImpl;
import br.com.vitavault.exceptions.EstoqueException;
import br.com.vitavault.exceptions.MovimentacaoEstoqueException;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MovimentacaoEstoque {
    private UUID id;
    private ItemEstoque item;
    private Funcionario funcionario;
    private LocalDate dataMovimentacao;
    private EnumTipoMovimentacao tipoMovimentacao;
    private Long quantidade;
    private final Estoque estoque;
    private ItemEstoqueRepository itemEstoqueRepository;
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    private MovimentacaoEstoque(UUID id, ItemEstoque item, Funcionario funcionario, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade, Estoque estoque) {
        this.estoque = estoque;
        this.id = id;
        this.item = item;
        this.funcionario = funcionario;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    public MovimentacaoEstoque(UUID id, Produto produto, Funcionario funcionario, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade, Estoque estoque) {
        this(id, new ItemEstoque(produto.getId(), produto, estoque, dataMovimentacao, quantidade, tipoMovimentacao), funcionario, dataMovimentacao, tipoMovimentacao, quantidade, estoque);
        itemEstoqueRepository = new ItemEstoqueRepositoryImpl();
        movimentacaoEstoqueRepository = new MovimentacaoEstoqueRepositoryImpl();
    }

    public MovimentacaoEstoque(Produto produto, Funcionario funcionario, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade, Estoque estoque) {
        this(null, new ItemEstoque(produto, estoque, dataMovimentacao, 0L, tipoMovimentacao), funcionario, dataMovimentacao, tipoMovimentacao, quantidade, estoque);
        itemEstoqueRepository = new ItemEstoqueRepositoryImpl();
        movimentacaoEstoqueRepository = new MovimentacaoEstoqueRepositoryImpl();
    }

    public void movimentarEstoque(ItemEstoque itemEstoque, Long quantidade, EnumTipoMovimentacao tipoMovimentacao) throws MovimentacaoEstoqueException, EstoqueException {
        if (tipoMovimentacao.equals(EnumTipoMovimentacao.ENTRADA)) {
            movimentarEntrada(itemEstoque, quantidade);
        } else {
            movimentarSaida(itemEstoque, quantidade);
        }
    }

    private void movimentarEntrada(ItemEstoque itemEstoque, Long quantidade) {
        itemEstoque = estoque.vincularEstoqueAoItem(itemEstoque);

        vincularMovimentacaoAoItem(itemEstoque);
        aumentarQuantidadeItemEstoque(itemEstoque, quantidade);
    }

    private void movimentarSaida(ItemEstoque itemEstoque, Long quantidade) throws MovimentacaoEstoqueException, EstoqueException {
        Long saldoAnterior = calcularSaldoAnterior(itemEstoque, this.getDataMovimentacao());

        vincularMovimentacaoAoItem(itemEstoque);

        if (saldoAnterior >= quantidade) {
            diminuirQuantidadeItemEstoque(itemEstoque, quantidade);
        } else {
            throw new MovimentacaoEstoqueException("Saldo insuficiente");
        }
    }

    private Long calcularSaldoAnterior(ItemEstoque itemEstoque, LocalDate dataMovimentacao) {
        Long entradas = 0L;
        Long saidas = 0L;

        List<MovimentacaoEstoque> movimentacaoEstoqueList = movimentacaoEstoqueRepository.buscarMovimentacoes(itemEstoque.getId());

        for (MovimentacaoEstoque movimentacao : movimentacaoEstoqueList) {
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
        itemEstoqueRepository.alterarSaldo(itemEstoque.getId(), itemEstoque.getQuantidade() + quantidadeAumentar);
    }

    private void diminuirQuantidadeItemEstoque(ItemEstoque itemEstoque, Long quantidadeAumentar) throws MovimentacaoEstoqueException, EstoqueException {
        if (itemEstoque.getQuantidade().compareTo(quantidadeAumentar) >= 0) {
            itemEstoqueRepository.alterarSaldo(itemEstoque.getId(), itemEstoque.getQuantidade() - quantidadeAumentar);
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setItem(ItemEstoque item) {
        this.item = item;
    }
}
