package br.com.vitavault.controller;

import br.com.vitavault.Utils.Utils;
import br.com.vitavault.exceptions.GerenciadorFuncionariosException;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoDepreciavel;
import br.com.vitavault.model.ProdutoNaoDepreciavel;
import br.com.vitavault.validation.CadastroProdutoValidation;
import br.com.vitavault.validation.impl.CadastroProdutoValidationImpl;
import br.com.vitavault.view.CadastroContaView;
import br.com.vitavault.view.CadastroProdutoView;

import javax.swing.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroProdutoController {
    private CadastroProdutoView cadastroProdutoView;
    private CadastroProdutoValidation cadastroProdutoValidation;

    public CadastroProdutoController() {
        cadastroProdutoView = new CadastroProdutoView();
        cadastroProdutoValidation = new CadastroProdutoValidationImpl();
        inicializarBotoes();
    }

    public void inicializarBotoes() {
        cadastroProdutoView.adicionarAcaoAdicionarProduto((a) -> cadastrarProduto());
    }

    private void cadastrarProduto() {
        String nomeProduto = cadastroProdutoView.getNomeProduto();
        String descricaoProduto = cadastroProdutoView.getDescricaoProduto();
        String codigoProduto = cadastroProdutoView.getCodigoProduto();
        String precoProduto = cadastroProdutoView.getPrecoProduto();
        String categoriaProduto = cadastroProdutoView.getCategoriaProduto();
        String dataValidadeProduto = cadastroProdutoView.getDataValidadeProduto();


        List<String> mensagens = cadastroProdutoValidation.validar(nomeProduto, descricaoProduto, codigoProduto, precoProduto, categoriaProduto);

        if (!mensagens.isEmpty()) {
            cadastroProdutoView.alertaMensagens(mensagens);
            return;
        }

        Produto produto = getProduto(nomeProduto, descricaoProduto, codigoProduto, precoProduto, categoriaProduto, dataValidadeProduto);

        try {
            produto.cadastrarProduto(produto);
            JOptionPane.showMessageDialog(cadastroProdutoView, "Produto criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            cadastroProdutoView.resetarCampos();
            cadastroProdutoView.dispose();
        } catch (GerenciadorFuncionariosException ex) {
            JOptionPane.showMessageDialog(cadastroProdutoView, "Erro ao criar o Produto: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CadastroContaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Produto getProduto(String nomeProduto, String descricaoProduto, String codigoProduto, String precoProduto, String categoriaProduto, String dataValidadeProduto) {
        if (dataValidadeProduto.isEmpty()) {
            return new ProdutoNaoDepreciavel(Integer.parseInt(codigoProduto), nomeProduto, descricaoProduto, new BigDecimal(precoProduto), categoriaProduto, true);
        } else {
            return new ProdutoDepreciavel(Integer.parseInt(codigoProduto), nomeProduto, descricaoProduto, new BigDecimal(precoProduto), categoriaProduto, true, LocalDate.now());
        }
    }

    public void exibe() {
        cadastroProdutoView.exibe();
    }
}
