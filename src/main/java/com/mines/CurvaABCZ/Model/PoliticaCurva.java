package com.mines.CurvaABCZ.Model;

import com.mines.Empresa.Model.Empresa;

public class PoliticaCurva {

    private Integer id;
    private TipoCurvaAbcz tipoCurva;
    private Integer diasA;
    private Integer diasB;
    private Integer diasC;
    private Integer diasZ;
    private Integer frequenciaA;
    private Integer frequenciaB;
    private Integer frequenciaC;
    private Integer frequenciaZ;
    private Integer maxDiasA;
    private Integer maxDiasB;
    private Integer maxDiasC;
    private Integer maxDiasZ;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public TipoCurvaAbcz getTipoCurva() {
        return tipoCurva;
    }
    public void setTipoCurva(TipoCurvaAbcz tipoCurva) {
        this.tipoCurva = tipoCurva;
    }
    public Integer getDiasA() {
        return diasA;
    }
    public void setDiasA(Integer diasA) {
        this.diasA = diasA;
    }
    public Integer getDiasB() {
        return diasB;
    }
    public void setDiasB(Integer diasB) {
        this.diasB = diasB;
    }
    public Integer getDiasC() {
        return diasC;
    }
    public void setDiasC(Integer diasC) {
        this.diasC = diasC;
    }
    public Integer getDiasZ() {
        return diasZ;
    }
    public void setDiasZ(Integer diasZ) {
        this.diasZ = diasZ;
    }
    public Integer getFrequenciaA() {
        return frequenciaA;
    }
    public void setFrequenciaA(Integer frequenciaA) {
        this.frequenciaA = frequenciaA;
    }
    public Integer getFrequenciaB() {
        return frequenciaB;
    }
    public void setFrequenciaB(Integer frequenciaB) {
        this.frequenciaB = frequenciaB;
    }
    public Integer getFrequenciaC() {
        return frequenciaC;
    }
    public void setFrequenciaC(Integer frequenciaC) {
        this.frequenciaC = frequenciaC;
    }
    public Integer getFrequenciaZ() {
        return frequenciaZ;
    }
    public void setFrequenciaZ(Integer frequenciaZ) {
        this.frequenciaZ = frequenciaZ;
    }
    public Integer getMaxDiasA() {
        return maxDiasA;
    }
    public void setMaxDiasA(Integer maxDiasA) {
        this.maxDiasA = maxDiasA;
    }
    public Integer getMaxDiasB() {
        return maxDiasB;
    }
    public void setMaxDiasB(Integer maxDiasB) {
        this.maxDiasB = maxDiasB;
    }
    public Integer getMaxDiasC() {
        return maxDiasC;
    }
    public void setMaxDiasC(Integer maxDiasC) {
        this.maxDiasC = maxDiasC;
    }
    public Integer getMaxDiasZ() {
        return maxDiasZ;
    }
    public void setMaxDiasZ(Integer maxDiasZ) {
        this.maxDiasZ = maxDiasZ;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
