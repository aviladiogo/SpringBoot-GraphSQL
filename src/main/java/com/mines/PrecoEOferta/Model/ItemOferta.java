package com.mines.PrecoEOferta.Model;

import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Produto.Model.Produto;

public class ItemOferta {

    private Integer id;
    private String descricao;
    private Produto produto;
    private Fornecedor fabricante;
    private List<PoliticaItemOferta> politicas;
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
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Fornecedor getFabricante() {
        return fabricante;
    }
    public void setFabricante(Fornecedor fabricante) {
        this.fabricante = fabricante;
    }
    public List<PoliticaItemOferta> getPoliticas() {
        return politicas;
    }
    public void setPoliticas(List<PoliticaItemOferta> politicas) {
        this.politicas = politicas;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
