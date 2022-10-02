package com.mines.PrecoEOferta.Model;

import java.util.Date;

public class OfertaInput {

    private Integer id;
    private String titulo;
    private Integer fornecedor;
    private Date inicioValidade;
    private Date finalValidade;
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
    public Integer getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Integer fornecedor) {
        this.fornecedor = fornecedor;
    }
    public Date getInicioValidade() {
        return inicioValidade;
    }
    public void setInicioValidade(Date inicioValidade) {
        this.inicioValidade = inicioValidade;
    }
    public Date getFinalValidade() {
        return finalValidade;
    }
    public void setFinalValidade(Date finalValidade) {
        this.finalValidade = finalValidade;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
