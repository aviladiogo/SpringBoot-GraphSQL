package com.mines.CurvaABCZ.Model;

import java.util.List;

public class ClasseProdutoInput {
    
    private Integer id;
    private String titulo;
    private String descricao;
    private List<Integer> curvas;
    private Integer usuario;
    private Integer entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<Integer> getCurvas() {
        return curvas;
    }
    public void setCurvas(List<Integer> curvas) {
        this.curvas = curvas;
    }
    public Integer getUsuario() {
        return usuario;
    }
    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
