package com.mines.AgendaCompra.Model;

import java.util.List;

public class FiltroAgendaCompraInput {
    
    private Integer comprador;
    private List<Integer> fornecedores;
    private String dataInicio;
    private String dataFim;
    private Integer entidadeGestora;
    
    public Integer getComprador() {
        return comprador;
    }
    public void setComprador(Integer comprador) {
        this.comprador = comprador;
    }
    public List<Integer> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Integer> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public String getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
    public String getDataFim() {
        return dataFim;
    }
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
