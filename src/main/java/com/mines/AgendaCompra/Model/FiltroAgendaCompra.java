package com.mines.AgendaCompra.Model;

import java.util.List;

import com.mines.Comprador.Model.Comprador;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;

public class FiltroAgendaCompra {
    
    private Comprador comprador;
    private List<Fornecedor> fornecedores;
    private String dataInicio;
    private String dataFim;
    private Empresa entidadeGestora;
    
    public Comprador getComprador() {
        return comprador;
    }
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedor> fornecedores) {
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
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
 
}
