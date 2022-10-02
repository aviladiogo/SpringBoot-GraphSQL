package com.mines.Estoque.Model;

import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class Almoxarifado {

    private Integer id;
    private Empresa entidadeEstoque;
    private String descricao;
    private PessoaFisica responsavel;
    private Boolean ativo;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Empresa getEntidadeEstoque() {
        return entidadeEstoque;
    }
    public void setEntidadeEstoque(Empresa entidadeEstoque) {
        this.entidadeEstoque = entidadeEstoque;
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
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
