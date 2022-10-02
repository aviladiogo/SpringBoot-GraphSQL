package com.mines.Seguranca.Model;

public class PermissaoInput {

    private Integer id;
    private String descricao;
    private Integer perfil;
    private Integer usuario;
    private Integer transacao;
    private Integer entidadeGestora;
    
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
    public Integer getPerfil() {
        return perfil;
    }
    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }
    public Integer getUsuario() {
        return usuario;
    }
    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    public Integer getTransacao() {
        return transacao;
    }
    public void setTransacao(Integer transacao) {
        this.transacao = transacao;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
