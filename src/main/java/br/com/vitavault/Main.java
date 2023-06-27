package br.com.vitavault;

import br.com.vitavault.dao.impl.FuncionarioRepositoryImpl;
import br.com.vitavault.dao.impl.PapelRepositoryImpl;
import br.com.vitavault.dao.impl.ProdutoRepositoryImpl;
import br.com.vitavault.domain.EnumNivelAcesso;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.Papel;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoDepreciavel;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {


//
        //cria o produto

//
//        //Cria um estoque
//        Estoque estoque = new Estoque(new HashSet<>());
//
//        //movimentação do estoque
//        MovimentacaoEstoque movimentarEntrada =
//                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 1), EnumTipoMovimentacao.ENTRADA, 10L, estoque);
//
//        MovimentacaoEstoque movimentarEntrada2 =
//                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.ENTRADA, 5L, estoque);
//
//        MovimentacaoEstoque movimentarEntrada3 =
//                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.SAIDA, 13L, estoque);
//
//        // -> chama erro que não existe no estoque
////        ItemEstoque itemEstoque = new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), 3L, EnumTipoMovimentacao.SAIDA);
////        movimentarEntrada.movimentarEstoque(itemEstoque, 50L, EnumTipoMovimentacao.SAIDA);
//
//
//        movimentarEntrada.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada.getQuantidade(), movimentarEntrada2.getTipoMovimentacao());
//
//
//        System.out.println("--------------------cria entrada--------------");
//
//
//        movimentarEntrada2.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada2.getQuantidade(), movimentarEntrada2.getTipoMovimentacao());
//
//
//        System.out.println("--------------------cria entrada 2--------------");
//
////        movimentarEntrada2.movimentarEntrada(movimentarEntrada2.getItem(), 25L);
////
////        System.out.println("--------------------fim da inclusão item 2--------------");
//
//        movimentarEntrada3.movimentarEstoque(movimentarEntrada.getItem(), movimentarEntrada3.getQuantidade(), movimentarEntrada3.getTipoMovimentacao());
//        System.out.println("--------------------saida do entrada--------------");
//
//
//        estoque.listar();


        //rodarTesteFuncionarioNoBanco();
        //rodarTesteProdutosNoBnco();
        //rodarTestePapelNoBanco();
    }

    private static void rodarTestePapelNoBanco() {
        Papel papel = criarPapel();

        PapelRepositoryImpl papelRepository = new PapelRepositoryImpl();
        papelRepository.gravar(papel);
        papelRepository.buscarPapel(papel.getId());
        papelRepository.removerPapel(papel.getId());
    }

    private static void rodarTesteFuncionarioNoBanco() {
        Funcionario funcionarioGian = criarFuncionario(criarPapel());
        FuncionarioRepositoryImpl funcionarioRepository = new FuncionarioRepositoryImpl();

        funcionarioRepository.gravar(funcionarioGian);
        funcionarioRepository.buscarFuncionario(funcionarioGian.getId());
        List<Funcionario> funcionarios = funcionarioRepository.listarFuncionarios();
        funcionarios.forEach(p -> System.out.println(p.getNome()));
        funcionarioRepository.removerFuncionario(funcionarioGian.getId());
    }

    private static void rodarTesteProdutosNoBnco() {
        Produto produto = criarProduto();
        Produto produto2 = criarProduto2();
        ProdutoRepositoryImpl produtoRepository = new ProdutoRepositoryImpl();


        produtoRepository.gravar(produto);
        Produto buscarProduto = produtoRepository.buscarProduto(produto.getId());

        produtoRepository.gravar(produto2);
        Produto buscarProduto2 = produtoRepository.buscarProduto(produto2.getId());

        List<Produto> produtos = produtoRepository.listarProdutos();
        produtos.forEach(p -> System.out.println(p.getNome()));
    }

    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(1, "Teste",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }

    private static Produto criarProduto2() {
        return new ProdutoDepreciavel(2, "teste2",
                "Produto criado para teste 2", new BigDecimal("200.00"), "A definir", true, LocalDate.now());
    }

    private static Funcionario criarFuncionario(Papel papel) {
        return new Funcionario("09017144963", "Gian", "RUa 1", "47991061875",
                "123");
    }

    private static Papel criarPapel() {
        return new Papel(EnumNivelAcesso.DIGITADOR, "Teste");
    }

}
