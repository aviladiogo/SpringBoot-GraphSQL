package com.mines.CondicaoPagto.Model;

public class PrazoParcelaInput {

    private Integer id;
    private String titulo;
    private Integer dias;
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
    public Integer getDias() {
        return dias;
    }
    public void setDias(Integer dias) {
        this.dias = dias;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    } 
    
}
