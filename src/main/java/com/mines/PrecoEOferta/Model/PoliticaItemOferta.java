package com.mines.PrecoEOferta.Model;

import com.mines.Empresa.Model.Empresa;

public class PoliticaItemOferta {

    private Integer id;
    private Integer itemOferta;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;
    private Double precoUnitario;
    private Double percentualDesconto;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getItemOferta() {
        return itemOferta;
    }
    public void setItemOferta(Integer itemOferta) {
        this.itemOferta = itemOferta;
    }
    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }
    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }
    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }
    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }
    public Double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public Double getPercentualDesconto() {
        return percentualDesconto;
    }
    public void setPercentualDesconto(Double percentualDesconto) {
        this.percentualDesconto = percentualDesconto;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
