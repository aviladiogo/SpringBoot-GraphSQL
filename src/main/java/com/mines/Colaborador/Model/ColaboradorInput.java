package com.mines.Colaborador.Model;

public class ColaboradorInput {
    
    private Integer id;
    private String descricao;
    private Integer funcao;
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
    public Integer getFuncao() {
        return funcao;
    }
    public void setFuncao(Integer funcao) {
        this.funcao = funcao;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
