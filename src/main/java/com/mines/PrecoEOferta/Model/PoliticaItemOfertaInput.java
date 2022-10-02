package com.mines.PrecoEOferta.Model;

public class PoliticaItemOfertaInput {

    private Integer id;
    private Integer itemOferta;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;
    private Double precoUnitario;
    private Double percentualDesconto;
    private Integer entidadeGestora;

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
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
