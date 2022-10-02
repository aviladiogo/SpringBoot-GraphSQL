package com.mines.TabelaPreco.Model;

import java.util.Date;
import java.util.List;
import com.mines.CondicaoPagto.Model.CondicaoPagto;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Produto.Model.Produto;

public class TabelaPreco {

    private Integer id;
    private List<Fornecedor> fornecedores;
    private PessoaFisica responsavel;
    private Boolean promocao;
    private String titulo;
    private Date inicioValidade;
    private Date terminoValidade;
    private Boolean ativo;
    private List<Produto> itensTabela;
    private List<CondicaoPagto> condicoesPagto;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    public PessoaFisica getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(PessoaFisica responsavel) {
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
    public List<Produto> getItensTabela() {
        return itensTabela;
    }
    public void setItensTabela(List<Produto> itensTabela) {
        this.itensTabela = itensTabela;
    }
    public List<CondicaoPagto> getCondicoesPagto() {
        return condicoesPagto;
    }
    public void setCondicoesPagto(List<CondicaoPagto> condicoesPagto) {
        this.condicoesPagto = condicoesPagto;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
