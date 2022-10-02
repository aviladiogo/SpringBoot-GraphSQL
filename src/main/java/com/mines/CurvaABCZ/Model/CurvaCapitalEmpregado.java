package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class CurvaCapitalEmpregado {
    
    private Integer id;
    private Double valorCapital;
    private Double frequencia;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Double getValorCapital() {
        return valorCapital;
    }
    public void setValorCapital(Double valorCapital) {
        this.valorCapital = valorCapital;
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
