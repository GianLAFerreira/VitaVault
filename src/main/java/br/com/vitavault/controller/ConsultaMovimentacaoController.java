package br.com.vitavault.controller;

import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.impl.EstoqueRepositoryImpl;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.view.ConsultaMovimentacaoView;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsultaMovimentacaoController {

    private ConsultaMovimentacaoView consultaMovimentacaoView;
    private EstoqueRepository estoqueRepository;

    public ConsultaMovimentacaoController() {
        consultaMovimentacaoView = new ConsultaMovimentacaoView();
        estoqueRepository = new EstoqueRepositoryImpl();
        inicializarBotoes();
    }

    public void inicializarBotoes() {
        consultaMovimentacaoView.adicionarAcaoConsultarMovimentacao((a) -> atualizarTabelaMovimentacoesEstoque(consultaMovimentacaoView.getTabela()));
    }

    private void atualizarTabelaMovimentacoesEstoque(JTable tabela) {
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0); // Limpar as linhas existentes
        
        Estoque estoque = estoqueRepository.buscarEstoque();
       
       List<ItemEstoque> itemEstoque = estoqueRepository.buscarItensEstoquePeloEstoque(estoque.getId());
        

        for (ItemEstoque movimentacao : itemEstoque) {
            Object[] rowData = {
                    movimentacao.getProduto(),
                    movimentacao.getQuantidade(),
                    movimentacao.getData()
            };

            tableModel.addRow(rowData);
        }

        System.out.println("Fingindo q deu certo");
    }

    public void exibe() {
        consultaMovimentacaoView.setVisible(true);
    }
}
