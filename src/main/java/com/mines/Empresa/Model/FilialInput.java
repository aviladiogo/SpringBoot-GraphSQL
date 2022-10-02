package com.mines.Empresa.Model;

import java.util.Date;

public class FilialInput {

    private Integer id;
    private Integer responsavel;
    private Integer supervisor;
    private Boolean ativo;
    private Date dataAbertura;
    private String uf;
    private Integer grupoFilial;
    private Integer grupoCompraInterno;
    private Integer grupoCompraExterno;
    private Integer tipoFilial;
    private String observacao;
    private Integer entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }
    public Integer getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Date getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }
    public Integer getGrupoFilial() {
        return grupoFilial;
    }
    public void setGrupoFilial(Integer grupoFilial) {
        this.grupoFilial = grupoFilial;
    }
    public Integer getGrupoCompraInterno() {
        return grupoCompraInterno;
    }
    public void setGrupoCompraInterno(Integer grupoCompraInterno) {
        this.grupoCompraInterno = grupoCompraInterno;
    }
    public Integer getGrupoCompraExterno() {
        return grupoCompraExterno;
    }
    public void setGrupoCompraExterno(Integer grupoCompraExterno) {
        this.grupoCompraExterno = grupoCompraExterno;
    }
    public Integer getTipoFilial() {
        return tipoFilial;
    }
    public void setTipoFilial(Integer tipoFilial) {
        this.tipoFilial = tipoFilial;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
