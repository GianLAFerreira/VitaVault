/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vitavault;

import br.com.vitavault.exceptions.GerenciadorFuncionariosException;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;

/**
 * @author ariad
 */
public class MainAri {
    public static void main(String[] args) throws GerenciadorFuncionariosException {
//        HomePage_1 tela = new HomePage_1();
//        tela.setVisible(true);
        Funcionario oFuncionario = new Funcionario("09017141", "teste1", "3213", "3213", "3213123");
        Funcionario oFuncionario2 = new Funcionario("545454", "teste2", "3213", "3213", "3213123");
        oFuncionario2.cadastrarFuncionario(oFuncionario2);
        oFuncionario.cadastrarFuncionario(oFuncionario);
        Funcionario.listarClientes();
        criarProduto();
        Produto.imprimeProdutosLista();

    }

    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(1, "Teste Ari",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }
}
