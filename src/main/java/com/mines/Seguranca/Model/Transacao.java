package com.mines.Seguranca.Model;

import com.mines.Empresa.Model.Empresa;

public class Transacao {

    private Integer id;
    private String descricao;
    private Boolean ativo;
    private Modulo modulo;
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
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Modulo getModulo() {
        return modulo;
    }
    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
