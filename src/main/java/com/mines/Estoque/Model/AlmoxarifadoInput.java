package com.mines.Estoque.Model;

public class AlmoxarifadoInput {

    private Integer id;
    private Integer entidadeEstoque;
    private String descricao;
    private Integer responsavel;
    private Boolean ativo;
    private Integer entidadeGestora;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getEntidadeEstoque() {
        return entidadeEstoque;
    }
    public void setEntidadeEstoque(Integer entidadeEstoque) {
        this.entidadeEstoque = entidadeEstoque;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(Integer responsavel) {
        this.responsavel = responsavel;
    }
    public Boolean getAtivo() {
        return ativo;
    }
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
