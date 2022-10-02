package com.mines.TabelaPreco.Model;

public class ItemPedidoVendaInput {

    private Integer id;
    private Integer tabelaPreco;
    private Integer produto;
    private Double valorUnitario;
    private Integer qtdeMinima;
    private Integer entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTabelaPreco() {
        return tabelaPreco;
    }
    public void setTabelaPreco(Integer tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }
    public Integer getProduto() {
        return produto;
    }
    public void setProduto(Integer produto) {
        this.produto = produto;
    }
    public Double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public Integer getQtdeMinima() {
        return qtdeMinima;
    }
    public void setQtdeMinima(Integer qtdeMinima) {
        this.qtdeMinima = qtdeMinima;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
