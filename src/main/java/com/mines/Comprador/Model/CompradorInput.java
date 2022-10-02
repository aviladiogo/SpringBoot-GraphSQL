package com.mines.Comprador.Model;

public class CompradorInput {
    
    private Integer id;
    private Integer pessoa;
    private Double limiteCompra;
    private Integer ativo;
    private Integer compradorLider;
    private Integer entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getPessoa() {
        return pessoa;
    }
    public void setPessoa(Integer pessoa) {
        this.pessoa = pessoa;
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
    public Integer getCompradorLider() {
        return compradorLider;
    }
    public void setCompradorLider(Integer compradorLider) {
        this.compradorLider = compradorLider;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
