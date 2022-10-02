package com.mines.Estoque.Model;

public class EstoqueInput {

    private Integer id;
    private Integer entidadeEstoque;
    private Integer almoxarifado;
    private Integer responsavel;
    private Integer produto;
    private Double quantidade;
    private Double giroVendaDia;
    private Integer entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEntidadeEstoque() {
        return entidadeEstoque;
    }
    public void setEntidadeEstoque(Integer entidadeEstoque) {
        this.entidadeEstoque = entidadeEstoque;
    }
    public Integer getAlmoxarifado() {
        return almoxarifado;
    }
    public void setAlmoxarifado(Integer almoxarifado) {
        this.almoxarifado = almoxarifado;
    }
    public Integer getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }
    public Integer getProduto() {
        return produto;
    }
    public void setProduto(Integer produto) {
        this.produto = produto;
    }
    public Double getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
    public Double getGiroVendaDia() {
        return giroVendaDia;
    }
    public void setGiroVendaDia(Double giroVendaDia) {
        this.giroVendaDia = giroVendaDia;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
