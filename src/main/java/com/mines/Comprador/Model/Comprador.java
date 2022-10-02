package com.mines.Comprador.Model;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;

public class Comprador {

    private Integer id;
    private PessoaFisica pessoa;
    private Double limiteCompra;
    private Integer ativo;
    private PessoaFisica compradorLider;
    private List<Departamento> departamentos;
    private List<Secao> secao;
    private List<Categoria> categoria;
    private List<SubCategoria> subCategoria;
    private Empresa entidadeGestora;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public PessoaFisica getPessoa() {
        return pessoa;
    }
    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }
    public Double getLimiteCompra() {
        return limiteCompra;
    }
    public void setLimiteCompra(Double limiteCompra) {
        this.limiteCompra = limiteCompra;
    }
    public Integer getAtivo() {
        return ativo;
    }
    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }
    public PessoaFisica getCompradorLider() {
        return compradorLider;
    }
    public void setCompradorLider(PessoaFisica compradorLider) {
        this.compradorLider = compradorLider;
    }
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }
    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
    public List<Secao> getSecao() {
        return secao;
    }
    public void setSecao(List<Secao> secao) {
        this.secao = secao;
    }
    public List<Categoria> getCategoria() {
        return categoria;
    }
    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }
    public List<SubCategoria> getSubCategoria() {
        return subCategoria;
    }
    public void setSubCategoria(List<SubCategoria> subCategoria) {
        this.subCategoria = subCategoria;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
