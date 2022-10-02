package com.mines.Estoque.Model;

import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Produto.Model.Produto;

public class Estoque {

    private Integer id;
    private Empresa entidadeEstoque;
    private Almoxarifado almoxarifado;
    private PessoaFisica responsavel;
    private Produto produto;
    private Double quantidade;
    private Double giroVendaDia;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Empresa getEntidadeEstoque() {
        return entidadeEstoque;
    }
    public void setEntidadeEstoque(Empresa entidadeEstoque) {
        this.entidadeEstoque = entidadeEstoque;
    }
    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }
    public void setAlmoxarifado(Almoxarifado almoxarifado) {
        this.almoxarifado = almoxarifado;
    }
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(PessoaFisica responsavel) {
        this.responsavel = responsavel;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
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
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
