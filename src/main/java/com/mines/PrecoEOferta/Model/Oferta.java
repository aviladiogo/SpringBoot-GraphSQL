package com.mines.PrecoEOferta.Model;

import java.util.Date;
import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;

public class Oferta {

    private Integer id;
    private String titulo;
    private Fornecedor fornecedor;
    private Date inicioValidade;
    private Date finalValidade;
    List<Empresa> operadoresLogisticos;
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
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    public Date getInicioValidade() {
        return inicioValidade;
    }
    public void setInicioValidade(Date inicioValidade) {
        this.inicioValidade = inicioValidade;
    }
    public Date getFinalValidade() {
        return finalValidade;
    }
    public void setFinalValidade(Date finalValidade) {
        this.finalValidade = finalValidade;
    }
    public List<Empresa> getOperadoresLogisticos() {
        return operadoresLogisticos;
    }
    public void setOperadoresLogisticos(List<Empresa> operadoresLogisticos) {
        this.operadoresLogisticos = operadoresLogisticos;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }

}
