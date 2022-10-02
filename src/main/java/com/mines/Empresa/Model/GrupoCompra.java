package com.mines.Empresa.Model;

public class GrupoCompra {
    
    private Integer id;
    private String descricao;
    private Integer externo;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getExterno() {
        return externo;
    }
    public void setExterno(Integer externo) {
        this.externo = externo;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

    
}
