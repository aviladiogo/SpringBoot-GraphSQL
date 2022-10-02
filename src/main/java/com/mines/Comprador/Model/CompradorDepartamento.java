package com.mines.Comprador.Model;

import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.Empresa.Model.Empresa;

public class CompradorDepartamento {

    private Integer comprador;
    private Double limiteCompra;
    private Integer ativo;
    private Departamento departamento;
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
    public Departamento getDepartamento() {
        return departamento;
    }
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
