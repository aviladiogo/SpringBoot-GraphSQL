package com.mines.Seguranca.Model;

public class UsuarioInput {

    private Integer pessoaFisicaId;
    private String login;
    private String senha;
    private Boolean ativo;
    private Integer perfil;
    private Integer entidadeGestora;
    
    public Integer getPessoaFisicaId() {
        return pessoaFisicaId;
    }
    public void setPessoaFisicaId(Integer pessoaFisicaId) {
        this.pessoaFisicaId = pessoaFisicaId;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Integer getPerfil() {
        return perfil;
    }
    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
