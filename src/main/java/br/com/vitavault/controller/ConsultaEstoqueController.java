package br.com.vitavault.controller;

import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.impl.EstoqueRepositoryImpl;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.view.ConsultaEstoqueView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ConsultaEstoqueController {

    private ConsultaEstoqueView consultaEstoqueView;
    private EstoqueRepository estoqueRepository;
    private static final int TIPO_ORDENACAO_CRESCENTE = 0;
    private static final int TIPO_ORDENACAO_DECRESCENTE = 1;
    private static final int INDICE_ORDENACAO_QUANTIDADE = 0;
    private static final int INDICE_ORDENACAO_NOME = 1;
    private static final int INDICE_ORDENACAO_DATA = 2;
    private List<ItemEstoque> itemEstoque = new ArrayList<>();

    public ConsultaEstoqueController() {
        consultaEstoqueView = new ConsultaEstoqueView();
        estoqueRepository = new EstoqueRepositoryImpl();
        inicializarBotoes();
    }

    public void inicializarBotoes() {
        consultaEstoqueView.adicionarAcaoConsultarMovimentacao((a) -> atualizarConsulta());
    }

    private void atualizarTabelaMovimentacoesEstoque(JTable tabela) {
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0);

        for (ItemEstoque movimentacao : itemEstoque) {
            Object[] rowData = {
                    movimentacao.getProduto(),
                    movimentacao.getQuantidade(),
                    movimentacao.getData()
            };

            tableModel.addRow(rowData);
        }
    }

    private void atualizarConsulta() {
        modoOrdenacaoTabela();
        atualizarTabelaMovimentacoesEstoque(consultaEstoqueView.getTabela());
    }

    private void modoOrdenacaoTabela() {
        itemEstoque = estoqueRepository.buscarItensEstoquePeloEstoque(estoqueRepository.buscarEstoque().getId());
        Comparator<ItemEstoque> comparator;

        int selectedIndex = consultaEstoqueView.getModoOrdenacao().getSelectedIndex();
        switch (selectedIndex) {
            case INDICE_ORDENACAO_QUANTIDADE:
                comparator = Comparator.comparingLong(ItemEstoque::getQuantidade);
                break;
            case INDICE_ORDENACAO_NOME:
                comparator = Comparator.comparing(ItemEstoque::getNomeProduto);
                break;
            case INDICE_ORDENACAO_DATA:
                comparator = Comparator.comparing(ItemEstoque::getData);
                break;
            default:
                throw new IllegalArgumentException("Índice de ordenação inválido");
        }

        if (TIPO_ORDENACAO_CRESCENTE != consultaEstoqueView.getOrdenacao().getSelectedIndex()) {
            comparator = comparator.reversed();
        }
        itemEstoque.sort(comparator);
    }

    public void exibe() {
        consultaEstoqueView.setVisible(true);
    }
}
