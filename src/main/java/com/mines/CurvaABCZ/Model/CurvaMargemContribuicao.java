package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class CurvaMargemContribuicao {

    private Integer id;
    private Double margem;
    private Double frequencia;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getMargem() {
        return margem;
    }
    public void setMargem(Double margem) {
        this.margem = margem;
    }
    public Double getFrequencia() {
        return frequencia;
    }
    public void setFrequencia(Double frequencia) {
        this.frequencia = frequencia;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
