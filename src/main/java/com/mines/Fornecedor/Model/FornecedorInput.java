package com.mines.Fornecedor.Model;

import java.util.List;

public class FornecedorInput {

    private Integer id;
    private String tipoPessoa;
    private Integer responsavel;
    private Boolean ativo;
    private Integer prazoEntrega;
    private Double pedidoMinimo;
    private Integer compradorPadrao;
    private Integer pessoaFisica;
    private Integer pessoaJuridica;
    private List<Integer> produtos;
    private Integer entidadeGestora;
    
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
    public Integer getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Integer responsavel) {
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
    public Integer getCompradorPadrao() {
        return compradorPadrao;
    }
    public void setCompradorPadrao(Integer compradorPadrao) {
        this.compradorPadrao = compradorPadrao;
    }
    public Integer getPessoaFisica() {
        return pessoaFisica;
    }
    public void setPessoaFisica(Integer pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    public Integer getPessoaJuridica() {
        return pessoaJuridica;
    }
    public void setPessoaJuridica(Integer pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
    public List<Integer> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Integer> produtos) {
        this.produtos = produtos;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
