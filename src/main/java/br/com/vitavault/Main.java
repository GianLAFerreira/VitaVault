package br.com.vitavault;

import br.com.vitavault.domain.EnumNivelAcesso;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Papel;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.UUID;

public class Main {


    public static void main(String[] args) {


        //cria o papel
        Papel papel = criarPapel();

        //cria o funcionario
        Funcionario funcionarioGian = criarFuncionario(papel);

        //cria o produto
        Produto produto = criarProduto();
        Produto produto2 = criarProduto2();
<<<<<<< Updated upstream
=======

        //Cria um estoque
        Estoque estoque = new Estoque(new HashMap<>());
>>>>>>> Stashed changes

        //movimentação do estoque
        MovimentacaoEstoque movimentarEntrada =
                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.now(), EnumTipoMovimentacao.ENTRADA, 10L, estoque);

        //movimentação do estoque
<<<<<<< Updated upstream
        MovimentacaoEstoque movimentarEntrada2 = new MovimentacaoEstoque(produto2, funcionarioGian, papel, LocalDate.now(), EnumTipoMovimentacao.ENTRADA, 10L);


        Estoque estoque = new Estoque(new HashMap<>());

        movimentarEntrada.movimentarEntrada(movimentarEntrada.getItem(), movimentarEntrada.getQuantidade(), estoque);


        System.out.println("--------------------fim da primeira inclusão--------------");


        movimentarEntrada.movimentarEntrada(movimentarEntrada.getItem(), 5L, estoque);


        System.out.println("--------------------fim da segunda inclusão--------------");

        movimentarEntrada2.movimentarEntrada(movimentarEntrada2.getItem(), 25L, estoque);

        System.out.println("--------------------fim da inclusão item 2--------------");
=======
        MovimentacaoEstoque movimentarEntrada2 =
                new MovimentacaoEstoque(produto2, funcionarioGian, papel, LocalDate.now(), EnumTipoMovimentacao.ENTRADA, 10L, estoque);


        movimentarEntrada.movimentarEntrada(movimentarEntrada.getItem(), movimentarEntrada.getQuantidade());
>>>>>>> Stashed changes


        System.out.println("--------------------fim da primeira inclusão--------------");


        movimentarEntrada.movimentarEntrada(movimentarEntrada.getItem(), 5L);


        System.out.println("--------------------fim da segunda inclusão--------------");

        movimentarEntrada2.movimentarEntrada(movimentarEntrada2.getItem(), 25L);

        System.out.println("--------------------fim da inclusão item 2--------------");

        movimentarEntrada2.getEstoque().listarProdutosEstoque();

        System.out.println("--------listagem----------");
    }

    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(UUID.randomUUID(), 1, "Teste",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }

    private static Produto criarProduto2() {
        return new ProdutoNaoDepreciavel(UUID.randomUUID(), 2, "teste2",
                "Produto criado para teste 2", new BigDecimal("200.00"), "A definir", true);
    }

    private static Funcionario criarFuncionario(Papel papel) {
        return new Funcionario(UUID.randomUUID(), "Gian", "999", "email@teste",
                "End teste", "senha1", papel);
    }

    private static Papel criarPapel() {
        return new Papel(UUID.randomUUID(), EnumNivelAcesso.DIGITADOR, "Teste");
    }
}
