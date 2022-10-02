package com.mines.SugestaoCompra.Model;
import com.mines.Empresa.Model.Empresa;

public class SituacaoSugestaoCompra {

    private Integer id;
    private String descricao;
    private Integer permiteEditar;
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
    public Integer getPermiteEditar() {
        return permiteEditar;
    }
    public void setPermiteEditar(Integer permiteEditar) {
        this.permiteEditar = permiteEditar;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
