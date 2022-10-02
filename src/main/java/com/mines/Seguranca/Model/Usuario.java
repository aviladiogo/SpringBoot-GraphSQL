package com.mines.Seguranca.Model;

import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class Usuario {

    private PessoaFisica pessoaFisicaId;
    private String login;
    private String senha;
    private Boolean ativo;
    private Perfil perfil;
    private Empresa entidadeGestora;
    
    public PessoaFisica getPessoaFisicaId() {
        return pessoaFisicaId;
    }
    public void setPessoaFisicaId(PessoaFisica pessoaFisicaId) {
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
    public Perfil getPerfil() {
        return perfil;
    }
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
