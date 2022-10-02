package com.mines.CurvaABCZ.Model;

public class CurvaAbczInput {
 
    private Integer id;
    private String descricao;
    private Integer tipoCurvaAbcz;
    private String curva;
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
    public Integer getTipoCurvaAbcz() {
        return tipoCurvaAbcz;
    }
    public void setTipoCurvaAbcz(Integer tipoCurvaAbcz) {
        this.tipoCurvaAbcz = tipoCurvaAbcz;
    }
    public String getCurva() {
        return curva;
    }
    public void setCurva(String curva) {
        this.curva = curva;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
