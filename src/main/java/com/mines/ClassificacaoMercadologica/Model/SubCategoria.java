package com.mines.ClassificacaoMercadologica.Model;

import java.util.Date;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;

public class SubCategoria {
    
    private Integer id;
    private String descricao;
    private Categoria categoria;
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
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
