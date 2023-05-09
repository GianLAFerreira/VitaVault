/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vitavault;

import br.com.vitavault.exceptions.GerenciadorClientesException;
import br.com.vitavault.model.Cliente;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoNaoDepreciavel;
import br.com.vitavault.swing.HomePage_1;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ariad
 */
public class MainAri {
    public static void main(String[] args) throws GerenciadorClientesException {
//        HomePage_1 tela = new HomePage_1();
//        tela.setVisible(true);
          Cliente oCliente = new Cliente(12, "teste1", "3213", 3213, "3213123");
          Cliente oCliente2 = new Cliente(2, "teste2", "3213", 3213, "3213123");
          oCliente2.cadastrarCliente(oCliente2);
          oCliente.cadastrarCliente(oCliente);
          Cliente.listarClientes();
          criarProduto();
          Produto.imprimeProdutosLista();
          
    }
    
    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(UUID.randomUUID(), 1, "Teste Ari",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }
}
