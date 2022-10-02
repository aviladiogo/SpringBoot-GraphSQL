package com.mines.CurvaABCZ.Model;

public class CurvaMargemContribuicaoInput {

    private Integer id;
    private Double margem;
    private Double frequencia;
    private Integer entidadeGestora;
    
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
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
