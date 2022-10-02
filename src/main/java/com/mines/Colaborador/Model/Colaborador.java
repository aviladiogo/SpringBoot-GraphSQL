package com.mines.Colaborador.Model;

import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;

public class Colaborador {

    private Integer id;
    private String descricao;
    private Funcao funcao;
    private List<EntidadeJuridica> empresas;
    private List<Funcao> funcoes;
    private Empresa entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Funcao getFuncao() {
        return funcao;
    }
    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
    public List<EntidadeJuridica> getEmpresas() {
        return empresas;
    }
    public void setEmpresas(List<EntidadeJuridica> empresas) {
        this.empresas = empresas;
    }
    public List<Funcao> getFuncoes() {
        return funcoes;
    }
    public void setFuncoes(List<Funcao> funcoes) {
        this.funcoes = funcoes;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
