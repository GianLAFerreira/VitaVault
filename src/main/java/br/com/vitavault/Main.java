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
import java.util.HashSet;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {

        //cria o papel
        Papel papel = criarPapel();

        //cria o funcionario
        Funcionario funcionarioGian = criarFuncionario(papel);

        //cria o produto
        Produto produto = criarProduto();
        Produto produto2 = criarProduto2();

        //Cria um estoque
        Estoque estoque = new Estoque(new HashSet<>());

        //movimentação do estoque
        MovimentacaoEstoque movimentarEntrada =
                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 1), EnumTipoMovimentacao.ENTRADA, 10L, estoque);

        MovimentacaoEstoque movimentarEntrada2 =
                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.ENTRADA, 5L, estoque);

        MovimentacaoEstoque movimentarEntrada3 =
                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.SAIDA, 13L, estoque);

        // -> chama erro que não existe no estoque
//        ItemEstoque itemEstoque = new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), 3L, EnumTipoMovimentacao.SAIDA);
//        movimentarEntrada.movimentarEstoque(itemEstoque, 50L, EnumTipoMovimentacao.SAIDA);


        movimentarEntrada.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada.getQuantidade(), movimentarEntrada2.getTipoMovimentacao());


        System.out.println("--------------------cria entrada--------------");


        movimentarEntrada2.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada2.getQuantidade(), movimentarEntrada2.getTipoMovimentacao());


        System.out.println("--------------------cria entrada 2--------------");

//        movimentarEntrada2.movimentarEntrada(movimentarEntrada2.getItem(), 25L);
//
//        System.out.println("--------------------fim da inclusão item 2--------------");

        movimentarEntrada3.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada3.getQuantidade(), movimentarEntrada3.getTipoMovimentacao());
        System.out.println("--------------------saida do entrada--------------");


        estoque.listar();

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
        return new Funcionario("09017144963", "Gian", "RUa 1", "47991061875",
                "123");
    }

    private static Papel criarPapel() {
        return new Papel(UUID.randomUUID(), EnumNivelAcesso.DIGITADOR, "Teste");
    }
}
