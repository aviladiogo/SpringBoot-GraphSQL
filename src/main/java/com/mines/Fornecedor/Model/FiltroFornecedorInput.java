package com.mines.Fornecedor.Model;

import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class FiltroFornecedorInput {
    
    private String tipo;
    private String tempoInicio;
    private String tempoFim;
    private PessoaFisica usuario;
    private Empresa entidadeGestora;
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
