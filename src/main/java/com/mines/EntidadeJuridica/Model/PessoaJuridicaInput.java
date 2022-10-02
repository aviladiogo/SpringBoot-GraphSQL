package com.mines.EntidadeJuridica.Model;

public class PessoaJuridicaInput {
    
    private Integer id;
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private Integer atividadeComercial;
    private Integer entidadeGestora;
    
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
    public Integer getAtividadeComercial() {
        return atividadeComercial;
    }
    public void setAtividadeComercial(Integer atividadeComercial) {
        this.atividadeComercial = atividadeComercial;
    }
    public Integer getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Integer entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
}
