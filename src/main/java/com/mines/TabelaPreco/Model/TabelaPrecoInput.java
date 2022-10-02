package com.mines.TabelaPreco.Model;

import java.util.Date;
import java.util.List;

public class TabelaPrecoInput {
    
    private Integer id;
    private List<Integer> fornecedores;
    private Integer responsavel;
    private Boolean promocao;
    private String titulo;
    private Date inicioValidade;
    private Date terminoValidade;
    private Boolean ativo;
    private Integer entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<Integer> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Integer> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public Integer getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }
    public Boolean getPromocao() {
        return promocao;
    }
    public void setPromocao(Boolean promocao) {
        this.promocao = promocao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Date getInicioValidade() {
        return inicioValidade;
    }
    public void setInicioValidade(Date inicioValidade) {
        this.inicioValidade = inicioValidade;
    }
    public Date getTerminoValidade() {
        return terminoValidade;
    }
    public void setTerminoValidade(Date terminoValidade) {
        this.terminoValidade = terminoValidade;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
