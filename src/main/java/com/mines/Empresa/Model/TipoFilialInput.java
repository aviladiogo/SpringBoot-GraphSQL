package com.mines.Empresa.Model;

public class TipoFilialInput {
    
    private Integer id;
    private String descricao;
    private Boolean vendeProduto;
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
    public Boolean getVendeProduto() {
        return vendeProduto;
    }
    public void setVendeProduto(Boolean vendeProduto) {
        this.vendeProduto = vendeProduto;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
