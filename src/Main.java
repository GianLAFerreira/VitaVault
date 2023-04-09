import main.java.br.com.vitavault.domain.EnumNivelAcesso;
import main.java.br.com.vitavault.domain.EnumTipoMovimentacao;
import main.java.br.com.vitavault.domain.MovimentacaoEstoque;
import main.java.br.com.vitavault.model.Funcionario;
import main.java.br.com.vitavault.model.Papel;
import main.java.br.com.vitavault.model.Produto;
import main.java.br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        //cria o papel
        Papel papel = criarPapel();
        //cria o funcionario
        Funcionario funcionarioGian = criarFuncionario(papel);
        //cria o produto
        Produto produto = criarProduto();

        //Adiciona o primeiro item o Estoque
        //ItemEstoque itemTesteEstoque = new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), 10L, EnumTipoMovimentacao.ENTRADA);


        MovimentacaoEstoque movimentarEntrada = new MovimentacaoEstoque(produto, funcionarioGian, papel, LocalDate.now(), EnumTipoMovimentacao.ENTRADA, 10L);

        movimentarEntrada.movimentarEntrada(movimentarEntrada.getItem().getProduto(), movimentarEntrada.getItem(), movimentarEntrada.getQuantidade());


    }

    private static Produto criarProduto() {
        return new ProdutoNaoDepreciavel(UUID.randomUUID(), 1, "Teste",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);
    }

    private static Funcionario criarFuncionario(Papel papel) {
        return new Funcionario(UUID.randomUUID(), "Gian", "999", "email@teste",
                "End teste", "senha1", papel);
    }

    private static Papel criarPapel() {
        return new Papel(UUID.randomUUID(), EnumNivelAcesso.DIGITADOR, "Teste");
    }
}
