package br.com.vitavault.controller;

import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.dao.impl.FuncionarioRepositoryImpl;
import br.com.vitavault.dao.impl.ProdutoRepositoryImpl;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Produto;
import br.com.vitavault.view.HomePageView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class HomePageController {
    private HomePageView homePageView;
    private LoginController loginController;
    private FuncionarioRepository funcionarioRepository;
    private CadastroProdutoController cadastroProdutoController;
    private ConsultaEstoqueController consultaEstoqueController;
    private MovimentacaoController movimentacaoController;
    private ProdutoRepository produtoRepository;

    public HomePageController(HomePageView homePageView, LoginController loginController) {
        this.homePageView = homePageView;
        this.loginController = loginController;
        this.funcionarioRepository = new FuncionarioRepositoryImpl();
        cadastroProdutoController = new CadastroProdutoController();
        consultaEstoqueController = new ConsultaEstoqueController();
        movimentacaoController = new MovimentacaoController(this);
        produtoRepository = new ProdutoRepositoryImpl();
        inicializarBotoes();
    }

    private void inicializarBotoes() {
        homePageView.adicionaAcaoBotaoMovimentaEstoque((a) -> exibeTelaConsultaEstoque());
        homePageView.adicionarAcaoBotaoConsultaEstoque((a) -> exibeTelaMovimentaoEstoque());
        homePageView.adicionaAcaoBotaoCadastroProduto((a) -> exibeTelaCadastroProduto());
        homePageView.adicionaAcaoBotaoSair((a) -> sairTelaHome());
    }

    private void exibeTelaConsultaEstoque() {
        consultaEstoqueController.exibe();
    }

    private void exibeTelaMovimentaoEstoque() {
        movimentacaoController.retornaMovimentacaoView().limparCampos();
        movimentacaoController.retornaMovimentacaoView().popularCampoProduto(getProdutos());
        movimentacaoController.exibe();
    }

    private void exibeTelaCadastroProduto() {
        cadastroProdutoController.exibe();
    }

    private void sairTelaHome() {
        homePageView.dispose();
        loginController.limparCampos();
        loginController.exibe();
    }

    public void atualizarTabelaMovimentacoesEstoque() {
        JTable tabela = homePageView.getTabela();
        DefaultTableModel tableModel = (DefaultTableModel) tabela.getModel();
        tableModel.setRowCount(0); // Limpar as linhas existentes


        List<MovimentacaoEstoque> produtos = movimentacaoController.getMovimentacoes();

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
        atualizarTabelaMovimentacoesEstoque();
        homePageView.exibe();
    }

    public Funcionario getFuncionario() {
        return funcionarioRepository.buscarFuncionarioByCPF(loginController.getCpf());
    }

    public List<Produto> getProdutos() {
        return produtoRepository.listarProdutos();
    }

}
