package com.mines.CondicaoPagto.Model;

public class CondicaoPagtoInput {
    
    private Integer id;
    private String titulo;
    private String descricao;
    private Integer parcelas;
    private Integer prazoParcelas;
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
    public Integer getParcelas() {
        return parcelas;
    }
    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }
    public Integer getPrazoParcelas() {
        return prazoParcelas;
    }
    public void setPrazoParcelas(Integer prazoParcelas) {
        this.prazoParcelas = prazoParcelas;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
