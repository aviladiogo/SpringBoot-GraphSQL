package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class TipoCurvaAbcz {

    private Integer id;
    private Empresa entidadeEstoque;
    private String descricao;
    private String tipoCurva;
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTipoCurva() {
        return tipoCurva;
    }
    public void setTipoCurva(String tipoCurva) {
        this.tipoCurva = tipoCurva;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
