package com.mines.AgendaCompra.Model;

import java.util.Date;
import java.util.List;

public class AgendaCompraInput {

    private Integer id;
    private String titulo;
    private Integer comprador;
    private Integer frequencia;
    private Date inicioAgendaCompra;
    private Date terminoAgendaCompra;
    private Integer domingo;
    private Integer segunda;
    private Integer terca;
    private Integer quarta;
    private Integer quinta;
    private Integer sexta;
    private Integer sabado;
    private Integer ativo;
    private Integer departamento;
    private Integer secao;
    private Integer categoria;
    private Integer subCategoria;
    private List<Integer> fornecedores;
    private Integer entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getComprador() {
        return comprador;
    }
    public void setComprador(Integer comprador) {
        this.comprador = comprador;
    }
    public Integer getFrequencia() {
        return frequencia;
    }
    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }
    public Date getInicioAgendaCompra() {
        return inicioAgendaCompra;
    }
    public void setInicioAgendaCompra(Date inicioAgendaCompra) {
        this.inicioAgendaCompra = inicioAgendaCompra;
    }
    public Date getTerminoAgendaCompra() {
        return terminoAgendaCompra;
    }
    public void setTerminoAgendaCompra(Date terminoAgendaCompra) {
        this.terminoAgendaCompra = terminoAgendaCompra;
    }
    public Integer getDomingo() {
        return domingo;
    }
    public void setDomingo(Integer domingo) {
        this.domingo = domingo;
    }
    public Integer getSegunda() {
        return segunda;
    }
    public void setSegunda(Integer segunda) {
        this.segunda = segunda;
    }
    public Integer getTerca() {
        return terca;
    }
    public void setTerca(Integer terca) {
        this.terca = terca;
    }
    public Integer getQuarta() {
        return quarta;
    }
    public void setQuarta(Integer quarta) {
        this.quarta = quarta;
    }
    public Integer getQuinta() {
        return quinta;
    }
    public void setQuinta(Integer quinta) {
        this.quinta = quinta;
    }
    public Integer getSexta() {
        return sexta;
    }
    public void setSexta(Integer sexta) {
        this.sexta = sexta;
    }
    public Integer getSabado() {
        return sabado;
    }
    public void setSabado(Integer sabado) {
        this.sabado = sabado;
    }
    public Integer getAtivo() {
        return ativo;
    }
    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }
    public Integer getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }
    public Integer getSecao() {
        return secao;
    }
    public void setSecao(Integer secao) {
        this.secao = secao;
    }
    public Integer getCategoria() {
        return categoria;
    }
    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }
    public Integer getSubCategoria() {
        return subCategoria;
    }
    public void setSubCategoria(Integer subCategoria) {
        this.subCategoria = subCategoria;
    }
    public List<Integer> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Integer> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
