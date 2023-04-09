import main.java.br.com.vitavault.domain.EnumNivelAcesso;
import main.java.br.com.vitavault.domain.EnumTipoMovimentacao;
import main.java.br.com.vitavault.domain.MovimentacaoEstoque;
import main.java.br.com.vitavault.model.Estoque;
import main.java.br.com.vitavault.model.Funcionario;
import main.java.br.com.vitavault.model.ItemEstoque;
import main.java.br.com.vitavault.model.Papel;
import main.java.br.com.vitavault.model.Produto;
import main.java.br.com.vitavault.model.ProdutoNaoDepreciavel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Produto produto = new ProdutoNaoDepreciavel(UUID.randomUUID(), 1, "Teste",
                "Produto criado para teste", new BigDecimal("100.00"), "Dentista", true);

        Papel papel = new Papel(UUID.randomUUID(), EnumNivelAcesso.DIGITADOR, "Teste");
        Funcionario funcionarioGian = new Funcionario(UUID.randomUUID(), "Gian", "999", "email@teste",
                "End teste", "senha1", papel);


        ItemEstoque itemTesteEstoque = new ItemEstoque(UUID.randomUUID(), produto, null, LocalDate.now(), 10L, EnumTipoMovimentacao.ENTRADA);


        Estoque estoque = new Estoque();
        estoque.adicionarProduto(itemTesteEstoque.getProduto().getId(), itemTesteEstoque);

        itemTesteEstoque.setEstoque(estoque);


        MovimentacaoEstoque movimentarEntrada =
                new MovimentacaoEstoque(UUID.randomUUID(), itemTesteEstoque, funcionarioGian, papel, LocalDate.now(), EnumTipoMovimentacao.ENTRADA, 10L);

        movimentarEntrada.movimentarEntrada(itemTesteEstoque.getEstoque(), itemTesteEstoque.getProduto(), itemTesteEstoque);

    }
}
