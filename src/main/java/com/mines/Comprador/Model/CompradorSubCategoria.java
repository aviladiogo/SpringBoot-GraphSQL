package com.mines.Comprador.Model;

import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Empresa.Model.Empresa;

public class CompradorSubCategoria {
    
    private Integer comprador;
    private Double limiteCompra;
    private Integer ativo;
    private SubCategoria subCategoria;
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
    public SubCategoria getSubCategoria() {
        return subCategoria;
    }
    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
