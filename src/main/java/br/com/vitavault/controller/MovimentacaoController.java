package br.com.vitavault.controller;

import br.com.vitavault.Utils.Utils;
import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.ItemEstoqueRepository;
import br.com.vitavault.dao.MovimentacaoEstoqueRepository;
import br.com.vitavault.dao.impl.EstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.ItemEstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.MovimentacaoEstoqueRepositoryImpl;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.exceptions.EstoqueException;
import br.com.vitavault.exceptions.MovimentacaoEstoqueException;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Produto;
import br.com.vitavault.validation.MovimentacaoEstoqueValidation;
import br.com.vitavault.validation.impl.MovimentacaoEstoqueValidationImpl;
import br.com.vitavault.view.CadastroContaView;
import br.com.vitavault.view.MovimentacaoView;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MovimentacaoController {

    private MovimentacaoView movimentacaoView;
    private EstoqueRepository estoqueRepository;
    private MovimentacaoEstoqueValidation movimentacaoEstoqueValidation;
    private Funcionario funcionarioLogado;
    private ItemEstoqueRepository itemEstoqueRepository;
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private HomePageController homePageController;

    public MovimentacaoController(HomePageController homePage) {
        movimentacaoView = new MovimentacaoView();
        movimentacaoEstoqueValidation = new MovimentacaoEstoqueValidationImpl();
        estoqueRepository = new EstoqueRepositoryImpl();
        itemEstoqueRepository = new ItemEstoqueRepositoryImpl();
        movimentacaoEstoqueRepository = new MovimentacaoEstoqueRepositoryImpl();
        homePageController = homePage;
        inicializarBotoes();
    }

    public void inicializarBotoes() {
        movimentacaoView.adicionarAcaoMovimentarEstoque((a) -> movimentarEstoque());
    }

    private void movimentarEstoque() {
        Produto produto = movimentacaoView.getProduto();
        String data = movimentacaoView.getData();
        String motivo = movimentacaoView.getMotivo();
        String quantidade = movimentacaoView.getQuantidade();
        EnumTipoMovimentacao tipoMovimentacao = movimentacaoView.getTipoMovimentacao();


        List<String> mensagens = movimentacaoEstoqueValidation.validar(data, quantidade);

        if (!mensagens.isEmpty()) {
            movimentacaoView.alertaMensagens(mensagens);
            return;
        }

        LocalDate dataFormatada = Utils.formatarData(data);
        Long quantidadeFormatada = Utils.formatarQuantidade(quantidade);
        MovimentacaoEstoque movimentacaoEstoque
                = new MovimentacaoEstoque(produto,
                homePageController.getFuncionario(),
                dataFormatada,
                tipoMovimentacao,
                quantidadeFormatada,
                getEstoque());

        ItemEstoque itemEstoque = itemEstoqueRepository.buscarItemEstoquePeloProduto(produto.getId());
        if (!Objects.isNull(itemEstoque)) {
            movimentacaoEstoque.setItem(itemEstoque);
        }

        movimentacaoEstoque.getItem().getProduto().setId(produto.getId());
        itemEstoqueRepository.gravar(movimentacaoEstoque.getItem());
        movimentacaoEstoqueRepository.gravar(movimentacaoEstoque);

        try {
            movimentacaoEstoque.movimentarEstoque(movimentacaoEstoque.getItem(), movimentacaoEstoque.getQuantidade(), movimentacaoEstoque.getTipoMovimentacao());
            JOptionPane.showMessageDialog(movimentacaoView, "Estoque movimentado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            movimentacaoView.dispose();
        } catch (MovimentacaoEstoqueException | EstoqueException ex) {
            JOptionPane.showMessageDialog(movimentacaoView, "Erro ao movimentar o estoque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CadastroContaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Estoque getEstoque() {
        return estoqueRepository.buscarEstoque();
    }

    public void exibe() {
        movimentacaoView.setVisible(true);
    }

    public MovimentacaoView retornaMovimentacaoView() {
        return movimentacaoView;
    }

}
