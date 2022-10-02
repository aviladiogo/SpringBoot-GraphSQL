package com.mines.Fornecedor.Model;

import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.Produto.Model.Produto;

public class Fornecedor {

    private Integer id;
    private String tipoPessoa;
    private String nomeFornecedor; //Somente Leitura
    private PessoaFisica responsavel;
    private Boolean ativo;
    private Integer prazoEntrega;
    private Double pedidoMinimo;
    private PessoaFisica compradorPadrao;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private List<Produto> produtos;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTipoPessoa() {
        return tipoPessoa;
    }
    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    public String getNomeFornecedor() {
        return nomeFornecedor;
    }
    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(PessoaFisica responsavel) {
        this.responsavel = responsavel;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }
    public Double getPedidoMinimo() {
        return pedidoMinimo;
    }
    public void setPedidoMinimo(Double pedidoMinimo) {
        this.pedidoMinimo = pedidoMinimo;
    }
    public PessoaFisica getCompradorPadrao() {
        return compradorPadrao;
    }
    public void setCompradorPadrao(PessoaFisica compradorPadrao) {
        this.compradorPadrao = compradorPadrao;
    }
    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }
    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }
    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
}
