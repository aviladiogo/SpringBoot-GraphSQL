package com.mines.EntidadeJuridica.Model;

import com.mines.Empresa.Model.Empresa;

public class PessoaFisica {
    
    private Integer id;
    private String nome;
    private String apelido;
    private String cpf;
    private String email;
    private Empresa entidade;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getApelido() {
        return apelido;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Empresa getEntidade() {
        return entidade;
    }
    public void setEntidade(Empresa entidade) {
        this.entidade = entidade;
    }
}
