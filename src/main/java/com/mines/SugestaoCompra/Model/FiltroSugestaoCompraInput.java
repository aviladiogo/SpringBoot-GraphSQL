package com.mines.SugestaoCompra.Model;

import java.util.List;

public class FiltroSugestaoCompraInput {

    private List<Integer> listaFornecedores;
    private List<Integer> listaStatus;
    private String tempoInicio;
    private String tempoFim;
    private Integer usuario;

    public List<Integer> getListaFornecedores() {
        return listaFornecedores;
    }
    public void setListaFornecedores(List<Integer> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }
    public List<Integer> getListaStatus() {
        return listaStatus;
    }
    public void setListaStatus(List<Integer> listaStatus) {
        this.listaStatus = listaStatus;
    }
    public String getTempoInicio() {
        return tempoInicio;
    }
    public void setTempoInicio(String tempoInicio) {
        this.tempoInicio = tempoInicio;
    }
    public String getTempoFim() {
        return tempoFim;
    }
    public void setTempoFim(String tempoFim) {
        this.tempoFim = tempoFim;
    }
    public Integer getUsuario() {
        return usuario;
    }
    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    
}
