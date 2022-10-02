package com.mines.CondicaoPagto.Model;

import com.mines.Empresa.Model.Empresa;

public class PrazoParcela {

    private Integer id;
    private String titulo;
    private Integer dias;
    private Empresa entidadeGestora;
    
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
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
