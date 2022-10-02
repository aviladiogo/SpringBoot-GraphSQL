package com.mines.SugestaoCompra.Model;

import java.util.List;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Fornecedor.Model.Fornecedor;

public class FiltroSugestaoCompra {
    
    private List<Fornecedor> listaFornecedores;
    private List<Integer> listaStatus;
    private String tempoInicio;
    private String tempoFim;
    private PessoaFisica usuario;
    
    public List<Fornecedor> getListaFornecedores() {
        return listaFornecedores;
    }
    public void setListaFornecedores(List<Fornecedor> listaFornecedores) {
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
    public PessoaFisica getUsuario() {
        return usuario;
    }
    public void setUsuario(PessoaFisica usuario) {
        this.usuario = usuario;
    }
    
}
