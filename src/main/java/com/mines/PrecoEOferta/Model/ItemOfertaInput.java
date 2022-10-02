package com.mines.PrecoEOferta.Model;

public class ItemOfertaInput {
    
    private Integer id;
    private String descricao;
    private Integer produto;
    private Integer fabricante;
    private Integer entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getProduto() {
        return produto;
    }
    public void setProduto(Integer produto) {
        this.produto = produto;
    }
    public Integer getfabricante() {
        return fabricante;
    }
    public void setfabricante(Integer fabricante) {
        this.fabricante = fabricante;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
