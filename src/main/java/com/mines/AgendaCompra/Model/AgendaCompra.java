package com.mines.AgendaCompra.Model;

import java.util.Date;
import java.util.List;

import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Comprador.Model.Comprador;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;

public class AgendaCompra {

    private Integer id;
    private String titulo;
    private Comprador comprador;
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
    private Departamento departamento;
    private Secao secao;
    private Categoria categoria;
    private SubCategoria subCategoria;
    private List<Fornecedor> fornecedores;
    private Empresa entidadeGestora;
    
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
    public Comprador getComprador() {
        return comprador;
    }
    public void setComprador(Comprador comprador) {
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
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public Secao getSecao() {
        return secao;
    }
    public void setSecao(Secao secao) {
        this.secao = secao;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public SubCategoria getSubCategoria() {
        return subCategoria;
    }
    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
