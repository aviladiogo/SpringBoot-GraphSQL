package com.mines.Fornecedor.Model;

import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class FornecedorFilial {

    private Fornecedor fornecedor;
    private Filial filial;
    private Integer prazoEntrega;
    private Double percentualIcms;
    private Double percentualCofins;
    private PessoaFisica compradorPadrao;
    private Empresa entidadeGestora;
    private List<TipoFrete> tipoFretes;
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public Filial getFilial() {
        return filial;
    }
    public void setFilial(Filial filial) {
        this.filial = filial;
    }
    public Integer getPrazoEntrega() {
        return prazoEntrega;
    }
    public void setPrazoEntrega(Integer prazoEntrega) {
        this.prazoEntrega = prazoEntrega;
    }
    public Double getPercentualIcms() {
        return percentualIcms;
    }
    public void setPercentualIcms(Double percentualIcms) {
        this.percentualIcms = percentualIcms;
    }
    public Double getPercentualCofins() {
        return percentualCofins;
    }
    public void setPercentualCofins(Double percentualCofins) {
        this.percentualCofins = percentualCofins;
    }
    public PessoaFisica getCompradorPadrao() {
        return compradorPadrao;
    }
    public void setCompradorPadrao(PessoaFisica compradorPadrao) {
        this.compradorPadrao = compradorPadrao;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    public List<TipoFrete> getTipoFretes() {
        return tipoFretes;
    }
    public void setTipoFretes(List<TipoFrete> tipoFretes) {
        this.tipoFretes = tipoFretes;
    }

}
