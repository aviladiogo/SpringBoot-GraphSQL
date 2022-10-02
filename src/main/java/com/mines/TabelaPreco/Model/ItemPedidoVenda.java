package com.mines.TabelaPreco.Model;

import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Produto;

public class ItemPedidoVenda {

    private Integer id;
    private TabelaPreco tabelaPreco;
    private Produto produto;
    private Double valorUnitario;
    private Integer qtdeMinima;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TabelaPreco getTabelaPreco() {
        return tabelaPreco;
    }
    public void setTabelaPreco(TabelaPreco tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
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
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
