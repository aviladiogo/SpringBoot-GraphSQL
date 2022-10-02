package com.mines.CondicaoPagto.Model;

import java.util.List;
import com.mines.Empresa.Model.Empresa;

public class CondicaoPagto {

    private Integer id;
    private String titulo;
    private String descricao;
    private Integer parcelas;
    private PrazoParcela prazoParcelas;
    private List<TipoPagto> tipos;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Integer getParcelas() {
        return parcelas;
    }
    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }
    public PrazoParcela getPrazoParcelas() {
        return prazoParcelas;
    }
    public void setPrazoParcelas(PrazoParcela prazoParcelas) {
        this.prazoParcelas = prazoParcelas;
    }
    public List<TipoPagto> getTipos() {
        return tipos;
    }
    public void setTipos(List<TipoPagto> tipos) {
        this.tipos = tipos;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
