package com.mines.Produto.Model;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;

public class Produto {

    private Integer id;
    private String descricao;
    private String ean1;
    private String ean2;
    private String codigo_cd;
    private SubCategoria subCategoria;
    private Unidade unidade;
    private Fornecedor fabricante;
    private Marca marca;
    private Embalagem embalagem;
    private Apresentacao apresentacao;
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
    public String getEan1() {
        return ean1;
    }
    public void setEan1(String ean1) {
        this.ean1 = ean1;
    }
    public String getEan2() {
        return ean2;
    }
    public void setEan2(String ean2) {
        this.ean2 = ean2;
    }
    public String getCodigo_cd() {
        return codigo_cd;
    }
    public void setCodigo_cd(String codigo_cd) {
        this.codigo_cd = codigo_cd;
    }
    public SubCategoria getSubCategoria() {
        return subCategoria;
    }
    public void setSubCategoria(SubCategoria subCategoria) {
        this.subCategoria = subCategoria;
    }
    public Unidade getUnidade() {
        return unidade;
    }
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    public Fornecedor getFabricante() {
        return fabricante;
    }
    public void setFabricante(Fornecedor fabricante) {
        this.fabricante = fabricante;
    }
    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public Embalagem getEmbalagem() {
        return embalagem;
    }
    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }
    public Apresentacao getApresentacao() {
        return apresentacao;
    }
    public void setApresentacao(Apresentacao apresentacao) {
        this.apresentacao = apresentacao;
    }
    public Empresa getEntidadeGestora() {
        return entidadeGestora;
    }
    public void setEntidadeGestora(Empresa entidadeGestora) {
        this.entidadeGestora = entidadeGestora;
    }
    
}
