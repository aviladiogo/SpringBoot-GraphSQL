package com.mines.Empresa.Model;

import java.util.Date;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class Filial {

    private Integer id;
    private PessoaFisica responsavel;
    private PessoaFisica supervisor;
    private Boolean ativo;
    private Date dataAbertura;
    private String uf;
    private GrupoFilial grupoFilial;
    private GrupoCompra grupoCompraInterno;
    private GrupoCompra grupoCompraExterno;
    private TipoFilial tipoFilial;
    private String observacao;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(PessoaFisica responsavel) {
        this.responsavel = responsavel;
    }
    public PessoaFisica getSupervisor() {
        return supervisor;
    }
    public void setSupervisor(PessoaFisica supervisor) {
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
    public GrupoFilial getGrupoFilial() {
        return grupoFilial;
    }
    public void setGrupoFilial(GrupoFilial grupoFilial) {
        this.grupoFilial = grupoFilial;
    }
    public GrupoCompra getGrupoCompraInterno() {
        return grupoCompraInterno;
    }
    public void setGrupoCompraInterno(GrupoCompra grupoCompraInterno) {
        this.grupoCompraInterno = grupoCompraInterno;
    }
    public GrupoCompra getGrupoCompraExterno() {
        return grupoCompraExterno;
    }
    public void setGrupoCompraExterno(GrupoCompra grupoCompraExterno) {
        this.grupoCompraExterno = grupoCompraExterno;
    }
    public TipoFilial getTipoFilial() {
        return tipoFilial;
    }
    public void setTipoFilial(TipoFilial tipoFilial) {
        this.tipoFilial = tipoFilial;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
