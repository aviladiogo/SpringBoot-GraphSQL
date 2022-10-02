package com.mines.EntidadeJuridica.Model;

import java.util.Date;
import com.mines.Empresa.Model.Empresa;

public class RamoAtividade {

    private Integer id;
    private String descricao;
    private Date registro;
    private PessoaFisica usuario;
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
    public Date getRegistro() {
        return registro;
    }
    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    public PessoaFisica getUsuario() {
        return usuario;
    }
    public void setUsuario(PessoaFisica usuario) {
        this.usuario = usuario;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
