package com.mines.EntidadeJuridica.Model;

import com.mines.Empresa.Model.Empresa;

public class PessoaJuridica {

    private Integer id;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private AtividadeComercial atividadeComercial;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomeFantasia() {
        return nomeFantasia;
    }
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public AtividadeComercial getAtividadeComercial() {
        return atividadeComercial;
    }
    public void setAtividadeComercial(AtividadeComercial atividadeComercial) {
        this.atividadeComercial = atividadeComercial;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
