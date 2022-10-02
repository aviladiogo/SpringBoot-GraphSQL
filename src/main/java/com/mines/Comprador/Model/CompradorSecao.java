package com.mines.Comprador.Model;

import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.Empresa.Model.Empresa;

public class CompradorSecao {

    private Integer comprador;
    private Double limiteCompra;
    private Integer ativo;
    private Secao secao;
    private Empresa entidadeGestora;
    
    public Integer getComprador() {
        return comprador;
    }
    public void setComprador(Integer comprador) {
        this.comprador = comprador;
    }
    public Double getLimiteCompra() {
        return limiteCompra;
    }
    public void setLimiteCompra(Double limiteCompra) {
        this.limiteCompra = limiteCompra;
    }
    public Integer getAtivo() {
        return ativo;
    }
    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }
    public Secao getSecao() {
        return secao;
    }
    public void setSecao(Secao secao) {
        this.secao = secao;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
