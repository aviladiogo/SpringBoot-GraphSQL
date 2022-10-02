package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class CurvaGiroVendaDia {

    private Integer id;
    private Double valorGiroDia;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getValorGiroDia() {
        return valorGiroDia;
    }
    public void setValorGiroDia(Double valorGiroDia) {
        this.valorGiroDia = valorGiroDia;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
