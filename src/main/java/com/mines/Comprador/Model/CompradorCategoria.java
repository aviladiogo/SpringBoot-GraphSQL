package com.mines.Comprador.Model;

import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.Empresa.Model.Empresa;

public class CompradorCategoria {
    
    private Integer comprador;
    private Double limiteCompra;
    private Integer ativo;
    private Categoria categoria;
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
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
