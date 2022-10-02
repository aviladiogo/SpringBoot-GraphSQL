package com.mines.Seguranca.Model;

import com.mines.Empresa.Model.Empresa;

public class Permissao {

    private Integer id;
    private String descricao;
    private Perfil perfil;
    private Usuario usuario;
    private Transacao transacao;
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
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Transacao getTransacao() {
        return transacao;
    }
    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
