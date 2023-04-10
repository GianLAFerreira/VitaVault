package main.java.br.com.vitavault.domain;

import main.java.br.com.vitavault.model.Estoque;
import main.java.br.com.vitavault.model.Funcionario;
import main.java.br.com.vitavault.model.ItemEstoque;
import main.java.br.com.vitavault.model.Papel;
import main.java.br.com.vitavault.model.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MovimentacaoEstoque {
        private UUID id;
        private ItemEstoque item;
        private Funcionario funcionario;
        private Papel alcada;
        private LocalDate dataMovimentacao;
        private EnumTipoMovimentacao tipoMovimentacao;
        private Long quantidade;
        private Estoque estoque;

    /*
        Autora: Ariadne Cavilha Jorge
        Gostei muito da ideia de fazer uma classe apenas para a movimentação de produtos porém ela ocasiona alguns erros em relação ao estoque do produto, uma vez que se eu realizar duas movimentações eu irei criar duas instâncias de estoque
        Poderiamos utilizar uma instância de movimentação estoque para realizar diversas movimentações mas acredito que isso saia um pouco do foco do que é a MovimentacaoEstoquea e também pode ocasionar erros, uma vez que teria o mesmo ItemEstoque para
        diversos produtos que eu iria criar. Podemos pensar em uma maneira mais fácil e objetiva de criar essa classe, ou podemos utilizar apenas a classe Estoque, tal como exemplificado no Main do projeto.
    */
    public MovimentacaoEstoque(ItemEstoque item, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this.id = UUID.randomUUID();
        this.item = item;
        this.funcionario = funcionario;
        this.alcada = alcada;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }
    
    public MovimentacaoEstoque(Produto produto, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this(new ItemEstoque(UUID.randomUUID(), produto, LocalDate.now(), quantidade, tipoMovimentacao), funcionario, alcada, dataMovimentacao, tipoMovimentacao, quantidade);
    }
    
    /*
    Autora: Ariadne Cavilha Jorge
    Podemos implementar uma classe que verifica qual tipo de movimentação que é e realiza essas movimentações, instanciando a classe Estoque uma única vez,
    para não perder os registros que foram adicionados ou removidos, porém temos que ver como implementar essa classe, tal como supracitado acima.
    */
    public void adicionaMovimentacao(EnumTipoMovimentacao tipoMovimentacao, Produto produto, ItemEstoque itemEstoque, Long quantidade) {
        Estoque estoque = new Estoque();
        
        if(tipoMovimentacao == EnumTipoMovimentacao.ENTRADA) {
            movimentarEntrada(produto, itemEstoque, quantidade, estoque);
        } 
        else {
            //movimentarSaida
        }
    }
    
    private void movimentarEntrada(Produto produto, ItemEstoque itemEstoque, Long quantidade, Estoque estoque) {
        estoque.vincularEstoqueAoItem(itemEstoque, estoque);
        setEstoque(estoque);
        //setarQuantidade(itemEstoque.getQuantidade(), quantidade, itemEstoque);
        itemEstoque.setQuantidade(quantidade);
        estoque.adicionarProduto(produto.getId(), itemEstoque);
        System.out.println("Id do produto no item estoque " + produto.getId());
        System.out.println("Saldo atual: " + itemEstoque.getQuantidade());
    }

    private void setarQuantidade(Long quantidadeEstoque, Long quantidadeAumentar, ItemEstoque itemEstoque) {
        itemEstoque.setQuantidade(quantidadeEstoque + quantidadeAumentar);
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public ItemEstoque getItem() {
        return item;
    }
    
    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    public void listarProdutosEstoque() {
        estoque.listarProdutosEstoque();
    }
}
