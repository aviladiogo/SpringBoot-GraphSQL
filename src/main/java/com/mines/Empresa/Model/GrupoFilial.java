package com.mines.Empresa.Model;

import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class GrupoFilial {
    
    private Integer id;
    private String descricao;
    private PessoaFisica responsavel;
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
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(PessoaFisica responsavel) {
        this.responsavel = responsavel;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

    
}
