package br.com.vitavault.controller;

import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.impl.EstoqueRepositoryImpl;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Produto;
import br.com.vitavault.view.ConsultaMovimentacaoView;
import java.util.Comparator;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsultaMovimentacaoController {

    private ConsultaMovimentacaoView consultaMovimentacaoView;
    private EstoqueRepository estoqueRepository;
    private List<ItemEstoque> itemEstoque; 
    private static final int TIPO_ORDENACAO_CRESCENTE = 0;   
    private static final int TIPO_ORDENACAO_DECRESCENTE = 1;   

    public ConsultaMovimentacaoController() {
        consultaMovimentacaoView = new ConsultaMovimentacaoView();
        estoqueRepository = new EstoqueRepositoryImpl();
        inicializarBotoes();
        Estoque estoque = estoqueRepository.buscarEstoque();
        this.itemEstoque = estoqueRepository.buscarItensEstoquePeloEstoque(estoque.getId());
    }

    public void inicializarBotoes() {
        consultaMovimentacaoView.adicionarAcaoConsultarMovimentacao((a) -> atualizarConsulta());
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
        atualizarTabelaMovimentacoesEstoque(consultaMovimentacaoView.getTabela());
    }
    
    private void modoOrdenacaoTabela() {
        Comparator<ItemEstoque> comparator;

        int selectedIndex = consultaMovimentacaoView.getModoOrdenacao().getSelectedIndex();
        System.out.println(selectedIndex);
        switch (selectedIndex) {
            case 0: // Comparação por quantidade
                comparator = Comparator.comparingLong(ItemEstoque::getQuantidade);
                break;
            case 1: // Comparação por data
                comparator = Comparator.comparing(ItemEstoque::getNomeProduto);
                break;
            case 2: // Comparação por nome
                comparator = Comparator.comparing(ItemEstoque::getData);
                break;
            default:
                throw new IllegalArgumentException("Índice de ordenação inválido");
        }
        
        if (TIPO_ORDENACAO_CRESCENTE != consultaMovimentacaoView.getOrdenacao().getSelectedIndex()) {
            comparator = comparator.reversed();
        }
        itemEstoque.sort(comparator);
    } 
    
   
    
    private void oi() {
         System.out.println(consultaMovimentacaoView.getModoOrdenacao().getSelectedItem());
    }
    
    public static void main(String[] args) {
       
        new ConsultaMovimentacaoController().oi();
    }
        
    public void exibe() {
        consultaMovimentacaoView.setVisible(true);
    }
}
