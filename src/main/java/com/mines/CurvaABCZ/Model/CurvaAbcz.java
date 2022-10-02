package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class CurvaAbcz {

    private Integer id;
    private String descricao;
    private TipoCurvaAbcz tipoCurvaAbcz;
    private String curva;
    private Empresa entidadeGestora;

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
    public TipoCurvaAbcz getTipoCurvaAbcz() {
        return tipoCurvaAbcz;
    }
    public void setTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz) {
        this.tipoCurvaAbcz = tipoCurvaAbcz;
    }
    public String getCurva() {
        return curva;
    }
    public void setCurva(String curva) {
        this.curva = curva;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
