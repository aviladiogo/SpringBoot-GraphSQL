package com.mines.ClassificacaoMercadologica.Model;

import java.util.Date;

public class DepartamentoInput {
    
    private Integer id;
    private String descricao;
    private Date registro;
    private Integer usuario;
    private Integer entidade;
    
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
    public Date getRegistro() {
        return registro;
    }
    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    public Integer getUsuario() {
        return usuario;
    }
    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }
    public Integer getEntidade() {
        return entidade;
    }
    public void setEntidade(Integer entidade) {
        this.entidade = entidade;
    }
}
