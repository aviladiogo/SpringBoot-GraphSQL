package com.mines.SugestaoCompra.Model;

import java.util.Date;
import java.util.List;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Fornecedor.Model.Fornecedor;

public class SugestaoCompra {
    
    private Integer id;
    private List<Filial> filiais;
    private CurvaAbcz curvaCalculo;
    private Integer coberturaDiasA;
    private Integer coberturaDiasB;
    private Integer coberturaDiasC;
    private Integer coberturaDiasZ;
    private List<Fornecedor> fornecedores;
    private Integer prazoEntregaCD;
    private Integer prazoEntregaLoja;
    private Integer prazoEntregaTotal;
    //private List<NivelServico> niveisServico;
    private Integer opcaoGiroDia;
    private Integer condicaoPagto;
    private Integer obterEstoqueCD;
    private Double giroDiaMinimo;
    private Integer compraGrupo;
    private Date registro;
    private PessoaFisica usuario;
    private SituacaoSugestaoCompra situacao;
    private List<SugestaoCompraItem> produtos;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<Filial> getFiliais() {
        return filiais;
    }
    public void setFiliais(List<Filial> filiais) {
        this.filiais = filiais;
    }
    public CurvaAbcz getCurvaCalculo() {
        return curvaCalculo;
    }
    public void setCurvaCalculo(CurvaAbcz curvaCalculo) {
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
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedor> fornecedores) {
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
    public PessoaFisica getUsuario() {
        return usuario;
    }
    public void setUsuario(PessoaFisica usuario) {
        this.usuario = usuario;
    }
    public SituacaoSugestaoCompra getSituacao() {
        return situacao;
    }
    public void setSituacao(SituacaoSugestaoCompra situacao) {
        this.situacao = situacao;
    }
    public List<SugestaoCompraItem> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<SugestaoCompraItem> produtos) {
        this.produtos = produtos;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
