/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.vitavault.time.comentarios;

/**
 * Classe voltada para adicionar comentários referente aos códigos, utilizada para sugestões de desenvolvimento ou possíveis dúvidas. Utilizando essa classe,
 * iremos deixar o nosso código mais limpo para as implementações e podemos nos comunicar de uma forma mais clara.
 * Lembrando que os comentários nos códigos ainda podem ser incluídos, uma vez que facilita o nosso desenvolvimento, principalmente se um método não foi inteiramente implementado então
 * você poderá colocar o que falta para ser implementado, por exemplo.
 * <p>
 * GUIA DE CRIAÇÃO DE COMENTÁRIOS:
 * -- Número da Dúvida (Seguir o padrão)
 * -- Autor(a)
 * -- Dúvida ou Sugestão
 * -- Código a ser analisado
 * -- Classe a qual o código pertence
 */
public class Comentarios {
      
    /*
    1 -
    Autora: Ariadne Cavilha Jorge
    
    Sugestão: Podemos implementar uma classe que verifica qual tipo de movimentação que é e realiza essas movimentações, instanciando a classe Estoque uma única vez,
    para não perder os registros que foram adicionados ou removidos, porém temos que ver como implementar essa classe, tal como supracitado acima.
    
    Classe: MovimentacaoEstoque
    
    Código: 
    public void adicionaMovimentacao(EnumTipoMovimentacao tipoMovimentacao, Produto produto, ItemEstoque itemEstoque, Long quantidade) {
        Estoque estoque = new Estoque();
        
        if(tipoMovimentacao == EnumTipoMovimentacao.ENTRADA) {
            movimentarEntrada(produto, itemEstoque, quantidade, estoque);
        } 
        else {
            //movimentarSaida
        }
    }

    R: A criação de uma classe que controla as movimentações do estoque em vez de fazer a movimentação direta do estoque tem várias vantagens.

    Aqui estão algumas delas:

    1. Separação de responsabilidades: A classe de movimentação de estoque é responsável apenas por controlar as movimentações do estoque,
    enquanto a classe de estoque é responsável por gerenciar o estoque em si. Isso torna o código mais organizado e fácil de manter.

    2. Flexibilidade: Quando você tem uma classe de movimentação de estoque, pode adicionar ou modificar diferentes tipos de movimentações
    (por exemplo, movimentação de entrada, movimentação de saída, movimentação de transferência entre estoques) sem precisar
    modificar a classe de estoque. Isso permite que o código seja mais flexível e extensível.

    3. Validar movimentações: Ao ter uma classe de movimentação de estoque, você pode validar as movimentações antes de executá-las.
    Por exemplo, verificar se há estoque suficiente para a movimentação solicitada, verificar se o produto existe no estoque,
    entre outras validações. Essas verificações podem ser feitas na classe de movimentação antes de afetar o estoque, evitando erros ou inconsistências.

    4. Histórico de movimentações: A classe de movimentação de estoque pode manter um registro histórico de todas as movimentações
    feitas no estoque, com informações sobre o funcionário responsável, data da movimentação, tipo de movimentação,
    quantidade movimentada, entre outras informações. Essas informações podem ser úteis para fins de auditoria, controle de estoque e análise de dados.

    Em resumo, a criação de uma classe que controla as movimentações do estoque em vez de fazer a
    movimentação direta do estoque pode tornar o código mais organizado, flexível, seguro e com informações históricas importantes.
    
    2 -
    Autora: Ariadne Cavilha Jorge
    
    Sugestão: Gostei muito da ideia de fazer uma classe apenas para a movimentação de produtos porém ela ocasiona alguns erros em relação ao estoque do produto, uma vez que se eu realizar duas movimentações eu irei criar duas instâncias de estoque
    Poderiamos utilizar uma instância de movimentação estoque para realizar diversas movimentações mas acredito que isso saia um pouco do foco do que é a MovimentacaoEstoquea e também pode ocasionar erros, uma vez que teria o mesmo ItemEstoque para
    diversos produtos que eu iria criar. Podemos pensar em uma maneira mais fácil e objetiva de criar essa classe, ou podemos utilizar apenas a classe Estoque, tal como exemplificado no Main do projeto.
    
    Classe: MovimentacaoEstoque
    
    Código:
    public MovimentacaoEstoque(ItemEstoque item, Funcionario funcionario, Papel alcada, LocalDate dataMovimentacao, EnumTipoMovimentacao tipoMovimentacao, Long quantidade) {
        this.id = UUID.randomUUID();
        this.item = item;
        this.funcionario = funcionario;
        this.alcada = alcada;
        this.dataMovimentacao = dataMovimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.quantidade = quantidade;
    }

    R: Mesmo caso da de cima
    */

}
