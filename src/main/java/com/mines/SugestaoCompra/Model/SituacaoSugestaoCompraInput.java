package com.mines.SugestaoCompra.Model;

public class SituacaoSugestaoCompraInput {

    private Integer id;
    private String descricao;
    private Integer permiteEditar;
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
    public Integer getPermiteEditar() {
        return permiteEditar;
    }
    public void setPermiteEditar(Integer permiteEditar) {
        this.permiteEditar = permiteEditar;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
