package com.mines.Fornecedor.Model;

public class FornecedorFilialInput {
    
    private Integer fornecedor;
    private Integer filial;
    private Integer prazoEntrega;
    private Double percentualIcms;
    private Double percentualCofins;
    private Integer compradorPadrao;
    private Integer entidadeGestora;

    public Integer getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }
    public Integer getFilial() {
        return filial;
    }
    public void setFilial(Integer filial) {
        this.filial = filial;
    }
    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }
    public Double getPercentualIcms() {
        return percentualIcms;
    }
    public void setPercentualIcms(Double percentualIcms) {
        this.percentualIcms = percentualIcms;
    }
    public Double getPercentualCofins() {
        return percentualCofins;
    }
    public void setPercentualCofins(Double percentualCofins) {
        this.percentualCofins = percentualCofins;
    }
    public Integer getCompradorPadrao() {
        return compradorPadrao;
    }
    public void setCompradorPadrao(Integer compradorPadrao) {
        this.compradorPadrao = compradorPadrao;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    } 
    
}
