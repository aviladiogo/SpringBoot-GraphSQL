package com.mines.CurvaABCZ.Model;

import java.sql.Date;
import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class ClasseProduto {
    
    private Integer id;
    private String titulo;
    private String descricao;
    private List<ItemClasseProduto> itensClassesProduto;
    private PessoaFisica usuario;
    private Date registro;
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
    public List<ItemClasseProduto> getItensClassesProduto() {
        return itensClassesProduto;
    }
    public void setItensClassesProduto(List<ItemClasseProduto> itensClassesProduto) {
        this.itensClassesProduto = itensClassesProduto;
    }
    public PessoaFisica getUsuario() {
        return usuario;
    }
    public void setUsuario(PessoaFisica usuario) {
        this.usuario = usuario;
    }
    public Date getRegistro() {
        return registro;
    }
    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
