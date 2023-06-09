package br.com.vitavault.model;

import br.com.vitavault.dao.ProdutoRepository;
import br.com.vitavault.dao.impl.ProdutoRepositoryImpl;
import br.com.vitavault.exceptions.GerenciadorFuncionariosException;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;


public abstract class Produto {
    private UUID id;
    private int codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String categoria;
    private boolean situacao;
    private ProdutoRepository produtoRepository;

    protected Produto(UUID id, int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.situacao = situacao;
        produtoRepository = new ProdutoRepositoryImpl();
    }

    protected Produto(int codigo, String nome, String descricao, BigDecimal preco, String categoria, boolean situacao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.situacao = situacao;
        produtoRepository = new ProdutoRepositoryImpl();
    }

//    public static void imprimeProdutosLista() {
//        for (Map.Entry<UUID, Produto> produto : produtos.entrySet()) {
//            UUID id = produto.getKey();
//            Produto oProduto = produto.getValue();
//            System.out.println(Funcionario.separador() + "Nome:" + oProduto.getNome() + "\n" +
//                    "ID Produto: " + id + "\n" +
//                    "Descrição do Produto: " + oProduto.getDescricao());
//        }
//    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public void cadastrarProduto(Produto produto) throws GerenciadorFuncionariosException {
        if (verificarProdutoExiste(produto)) {
            produtoRepository.gravar(produto);
            System.out.printf(Produto.separador() + "Produto \n Nome: %s \n Descricao: %s \n Codigo: %s \n Preço: %s \n Categoria: %s \n Cadastrado com sucesso" + Funcionario.separador(), produto.getNome(), produto.getDescricao(), produto.getCodigo(), produto.getPreco(), produto.getCategoria());
        } else {
            throw new GerenciadorFuncionariosException("Produto já cadastrado");
        }
    }

    private boolean verificarProdutoExiste(Produto produto) {
        return Objects.isNull(produtoRepository.buscarProduto(produto.getId()));
    }

    public static String separador() {
        return "\n------------------------\n";
    }

    @Override
    public String toString() {
        return this.nome;
    }

}
