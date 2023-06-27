
package br.com.vitavault.controller;

import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.swing.HomeCadastroPro;
import br.com.vitavault.swing.HomeConsulta;
import br.com.vitavault.swing.HomeMovimentacao;
import br.com.vitavault.swing.HomePage_1;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaHomeController {
    private HomePage_1 oTelaHome;
    private TelaLoginController oTelaLoginController;
    
    public TelaHomeController(HomePage_1 oTelaHome, TelaLoginController oTelaLoginController) {
        this.oTelaHome = oTelaHome;
        this.oTelaLoginController = oTelaLoginController;
        inicializarBotoes();
    }
    
    private void inicializarBotoes() {
        oTelaHome.adicionaAcaoBotaoMovimentaEstoque((a) -> exibeTelaConsultaEstoque());
        oTelaHome.adicionarAcaoBotaoConsultaEstoque((a) -> exibeTelaMovimentaoEstoque());
        oTelaHome.adicionaAcaoBotaoCadastroProduto((a) -> exibeTelaCadastroProduto());
        oTelaHome.adicionaAcaoBotaoAtualizarMovimentacao((a) -> atualizarTabelaMovimentacoesEstoque());
        oTelaHome.adicionaAcaoBotaoSair((a) -> sairTelaHome());
    }
    
    private void exibeTelaConsultaEstoque() {
        new HomeConsulta(oTelaLoginController.getEstoque()).exibe();
    }
    
    private void exibeTelaMovimentaoEstoque() {
        new HomeMovimentacao(oTelaLoginController.getFuncionarioLogado(), oTelaLoginController.getEstoque()).exibe();
    }
     
    private void exibeTelaCadastroProduto() {
        new HomeCadastroPro().exibe();
    }
    
    private void sairTelaHome() {
        oTelaHome.dispose();
        oTelaLoginController.exibe();
    }
    
    private void atualizarTabelaMovimentacoesEstoque() {
        JTable tabela = oTelaHome.getTabela();
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0); // Limpar as linhas existentes

        List<MovimentacaoEstoque> produtos = oTelaLoginController.getEstoque().getMovimentacoes();

        for (MovimentacaoEstoque movimentacao : produtos) {
            Object[] rowData = {
                    movimentacao.getItem().getProduto(),
                    movimentacao.getDataMovimentacao(),
                    movimentacao.getTipoMovimentacao(),
                    movimentacao.getQuantidade()
            };

            tableModel.addRow(rowData);
        }
    }
    
    public void exibirTela() {
        oTelaHome.exibe();
    }
    
}
