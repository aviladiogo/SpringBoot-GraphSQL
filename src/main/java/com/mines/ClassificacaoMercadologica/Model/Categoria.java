package com.mines.ClassificacaoMercadologica.Model;

import java.util.Date;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;

public class Categoria {

    private Integer id;
    private String descricao;
    private Secao secao;
    private Date registro;
    private PessoaFisica usuario;
    private PessoaJuridica entidade;

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
    public Secao getSecao() {
        return secao;
    }
    public void setSecao(Secao secao) {
        this.secao = secao;
    }
    public Date getRegistro() {
        return registro;
    }
    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    public PessoaFisica getUsuario() {
        return usuario;
    }
    public void setUsuario(PessoaFisica usuario) {
        this.usuario = usuario;
    }
    public PessoaJuridica getEntidade() {
        return entidade;
    }
    public void setEntidade(PessoaJuridica entidade) {
        this.entidade = entidade;
    }
    
}
