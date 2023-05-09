/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vitavault;

import br.com.vitavault.exceptions.GerenciadorClientesException;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author ariad
 */
public class MainAri {
    public static void main(String[] args) throws GerenciadorClientesException {
//        HomePage_1 tela = new HomePage_1();
//        tela.setVisible(true);
          Funcionario oFuncionario = new Funcionario("090171414496312", "teste1", "3213", "3213", "3213123");
          Funcionario oFuncionario2 = new Funcionario("545454", "teste2", "3213", "3213", "3213123");
          oFuncionario2.cadastrarCliente(oFuncionario2);
          oFuncionario.cadastrarCliente(oFuncionario);
          Funcionario.listarClientes();
          criarProduto();
          Produto.imprimeProdutosLista();
          
    }
    
    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(UUID.randomUUID(), 1, "Teste Ari",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }
}