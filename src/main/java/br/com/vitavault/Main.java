package br.com.vitavault;

import br.com.vitavault.dao.EstoqueRepository;
import br.com.vitavault.dao.FuncionarioRepository;
import br.com.vitavault.dao.ItemEstoqueRepository;
import br.com.vitavault.dao.MovimentacaoEstoqueRepository;
import br.com.vitavault.dao.impl.EstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.FuncionarioRepositoryImpl;
import br.com.vitavault.dao.impl.ItemEstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.MovimentacaoEstoqueRepositoryImpl;
import br.com.vitavault.dao.impl.ProdutoRepositoryImpl;
import br.com.vitavault.domain.EnumTipoMovimentacao;
import br.com.vitavault.domain.MovimentacaoEstoque;
import br.com.vitavault.model.Estoque;
import br.com.vitavault.model.Funcionario;
import br.com.vitavault.model.ItemEstoque;
import br.com.vitavault.model.Produto;
import br.com.vitavault.model.ProdutoDepreciavel;
import br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws Exception {


//

////        //movimentação do estoque

//
//        MovimentacaoEstoque movimentarEntrada2 =
//                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.ENTRADA, 5L, estoque);
//
//        MovimentacaoEstoque movimentarEntrada3 =
//                new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.of(2023, 5, 2), EnumTipoMovimentacao.SAIDA, 13L, estoque);
//
//        // -> chama erro que não existe no estoque
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


        // rodarTesteFuncionarioNoBanco();
        //rodarTesteProdutosNoBnco();
        //rodarTesteEstoqueNoBanco();
        //rodarTesteItemEstoqueNoBanco();

        EstoqueRepository estoqueRepository = new EstoqueRepositoryImpl();
        ProdutoRepositoryImpl produtoRepository = new ProdutoRepositoryImpl();
        FuncionarioRepository funcionarioRepository = new FuncionarioRepositoryImpl();
        MovimentacaoEstoqueRepository movimentacaoEstoqueRepository = new MovimentacaoEstoqueRepositoryImpl();
        ItemEstoqueRepository itemEstoqueRepository = new ItemEstoqueRepositoryImpl();
        Produto produto = criarProduto();
        Funcionario funcionario = criarFuncionario();
        produtoRepository.gravar(produto);
        funcionarioRepository.gravar(funcionario);


        MovimentacaoEstoque movimentarEntrada =
                new MovimentacaoEstoque(produto, funcionario, LocalDate.of(2023, 5, 1), EnumTipoMovimentacao.ENTRADA, 10L, estoqueRepository.buscarEstoque());
        itemEstoqueRepository.gravar(movimentarEntrada.getItem());
        movimentacaoEstoqueRepository.gravar(movimentarEntrada);


    }

    private static void rodarTesteItemEstoqueNoBanco() {
        ItemEstoqueRepository itemEstoqueRepository = new ItemEstoqueRepositoryImpl();
        Produto produto = criarProduto();
        ItemEstoque itemEstoque = new ItemEstoque(UUID.randomUUID(), produto, new EstoqueRepositoryImpl().buscarEstoque(), LocalDate.now(), 3L, EnumTipoMovimentacao.SAIDA);
        ProdutoRepositoryImpl produtoRepository = new ProdutoRepositoryImpl();

        produtoRepository.gravar(produto);

        itemEstoqueRepository.gravar(itemEstoque);
    }

    private static void rodarTesteEstoqueNoBanco() {
        EstoqueRepository estoqueRepository = new EstoqueRepositoryImpl();
//        estoqueRepository.gravar(estoque);

        Estoque buscarEstoque = estoqueRepository.buscarEstoque();
        System.out.println(buscarEstoque.getId());
    }


    private static void rodarTesteFuncionarioNoBanco() {
        Funcionario funcionarioGian = criarFuncionario();
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

    private static Funcionario criarFuncionario() {
        return new Funcionario("09017144963", "Gian", "RUa 1", "47991061875",
                "123");
    }


}
