package br.com.vitavault.controller;

import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.dao.impl.FuncionarioRepositoryImpl;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.view.CadastroProdutoView;
import br.com.vitavault.view.ConsultaMovimentacaoView;
import br.com.vitavault.view.HomePageView;
import br.com.vitavault.view.MovimentacaoView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TelaHomeController {
    private HomePageView homePageView;
    private TelaLoginController telaLoginController;
    private FuncionarioRepository funcionarioRepository;
    private Funcionario funcionario;

    public TelaHomeController(HomePageView homePageView, TelaLoginController telaLoginController) {
        this.homePageView = homePageView;
        this.telaLoginController = telaLoginController;
        this.funcionarioRepository = new FuncionarioRepositoryImpl();
        inicializarBotoes();
    }

    private void inicializarBotoes() {
        homePageView.adicionaAcaoBotaoMovimentaEstoque((a) -> exibeTelaConsultaEstoque());
        homePageView.adicionarAcaoBotaoConsultaEstoque((a) -> exibeTelaMovimentaoEstoque());
        homePageView.adicionaAcaoBotaoCadastroProduto((a) -> exibeTelaCadastroProduto());
        homePageView.adicionaAcaoBotaoAtualizarMovimentacao((a) -> atualizarTabelaMovimentacoesEstoque());
        homePageView.adicionaAcaoBotaoSair((a) -> sairTelaHome());
    }

    private void exibeTelaConsultaEstoque() {
        new ConsultaMovimentacaoView(telaLoginController.getEstoque()).exibe();
    }

    private void exibeTelaMovimentaoEstoque() {
        new MovimentacaoView(funcionario, telaLoginController.getEstoque()).exibe();
        // --ajustar depois para buscar o funcionario logado
    }

    private void exibeTelaCadastroProduto() {
        new CadastroProdutoView().exibe();
    }

    private void sairTelaHome() {
        homePageView.dispose();
        telaLoginController.exibe();
    }

    private void atualizarTabelaMovimentacoesEstoque() {
        JTable tabela = homePageView.getTabela();
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0); // Limpar as linhas existentes

        List<MovimentacaoEstoque> produtos = telaLoginController.getEstoque().getMovimentacoes();

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
        homePageView.exibe();
    }

}
