package com.mines.SugestaoCompra.Model;

import java.util.Date;
import java.util.List;

public class SugestaoCompraInput {

    private Integer id;
    private Integer curvaCalculo;
    private Integer coberturaDiasA;
    private Integer coberturaDiasB;
    private Integer coberturaDiasC;
    private Integer coberturaDiasZ;
    private List<Integer> fornecedores;
    private Integer prazoEntregaCD;
    private Integer prazoEntregaLoja;
    private Integer prazoEntregaTotal;
    private Integer opcaoGiroDia;
    private Integer condicaoPagto;
    private Integer obterEstoqueCD;
    private Double giroDiaMinimo;
    private Integer compraGrupo;
    private Date registro;
    private Integer usuario;
    private Integer situacao;
    private Integer entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCurvaCalculo() {
        return curvaCalculo;
    }
    public void setCurvaCalculo(Integer curvaCalculo) {
        this.curvaCalculo = curvaCalculo;
    }
    public Integer getCoberturaDiasA() {
        return coberturaDiasA;
    }
    public void setCoberturaDiasA(Integer coberturaDiasA) {
        this.coberturaDiasA = coberturaDiasA;
    }
    public Integer getCoberturaDiasB() {
        return coberturaDiasB;
    }
    public void setCoberturaDiasB(Integer coberturaDiasB) {
        this.coberturaDiasB = coberturaDiasB;
    }
    public Integer getCoberturaDiasC() {
        return coberturaDiasC;
    }
    public void setCoberturaDiasC(Integer coberturaDiasC) {
        this.coberturaDiasC = coberturaDiasC;
    }
    public Integer getCoberturaDiasZ() {
        return coberturaDiasZ;
    }
    public void setCoberturaDiasZ(Integer coberturaDiasZ) {
        this.coberturaDiasZ = coberturaDiasZ;
    }
    public List<Integer> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Integer> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public Integer getPrazoEntregaCD() {
        return prazoEntregaCD;
    }
    public void setPrazoEntregaCD(Integer prazoEntregaCD) {
        this.prazoEntregaCD = prazoEntregaCD;
    }
    public Integer getPrazoEntregaLoja() {
        return prazoEntregaLoja;
    }
    public void setPrazoEntregaLoja(Integer prazoEntregaLoja) {
        this.prazoEntregaLoja = prazoEntregaLoja;
    }
    public Integer getPrazoEntregaTotal() {
        return prazoEntregaTotal;
    }
    public void setPrazoEntregaTotal(Integer prazoEntregaTotal) {
        this.prazoEntregaTotal = prazoEntregaTotal;
    }
    public Integer getOpcaoGiroDia() {
        return opcaoGiroDia;
    }
    public void setOpcaoGiroDia(Integer opcaoGiroDia) {
        this.opcaoGiroDia = opcaoGiroDia;
    }
    public Integer getCondicaoPagto() {
        return condicaoPagto;
    }
    public void setCondicaoPagto(Integer condicaoPagto) {
        this.condicaoPagto = condicaoPagto;
    }
    public Integer getObterEstoqueCD() {
        return obterEstoqueCD;
    }
    public void setObterEstoqueCD(Integer obterEstoqueCD) {
        this.obterEstoqueCD = obterEstoqueCD;
    }
    public Double getGiroDiaMinimo() {
        return giroDiaMinimo;
    }
    public void setGiroDiaMinimo(Double giroDiaMinimo) {
        this.giroDiaMinimo = giroDiaMinimo;
    }
    public Integer getCompraGrupo() {
        return compraGrupo;
    }
    public void setCompraGrupo(Integer compraGrupo) {
        this.compraGrupo = compraGrupo;
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
    public Integer getSituacao() {
        return situacao;
    }
    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
