package com.mines.Produto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Produto.Model.Apresentacao;
import com.mines.Produto.Model.Embalagem;
import com.mines.Produto.Model.Marca;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Model.ProdutoInput;
import com.mines.Produto.Model.Unidade;
import com.mines.Produto.Repository.ProdutoRepository;
import com.mines.Produto.Service.ProdutoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private ProdutoService produtoServ;

    public Produto produto(Integer id){

        Produto produto = null;
        try {
            produto = produtoServ.obterPorIdProduto(id);
        } catch (Exception e) {
            return produto;
        }
        return produto;
    }

    public List<Produto> produtos(Integer entidade){

        List<Produto> lista = produtoServ.obterTodosProdutos(entidade);
        return lista;
    }

    public List<Produto> obterProdutosPorFornecedores(Integer entidadeGestora, List<Integer> fornecedores){

        List<Produto> lista = produtoServ.obterProdutosPorFornecedores(entidadeGestora, fornecedores);
        return lista;
    }

    public Boolean deletarProduto(Integer id){

        Boolean ret = false;

        try{
            Produto produto = produtoRepo.obterPorIdProduto(id);
            ret = produtoRepo.deletarProduto(produto);
        }catch(Exception e){
            return ret;
        }
        return ret;    
        
    }   

    public Produto salvarProduto(ProdutoInput produtoInput) throws SQLException{

        Produto produto = new Produto();

        produto.setDescricao(produtoInput.getDescricao());
        produto.setEan1(produtoInput.getEan1());
        produto.setEan2(produtoInput.getEan2());
        produto.setCodigo_cd(produtoInput.getCodigo_cd());
        
        SubCategoria subCategoria = new SubCategoria();
        subCategoria.setId(produtoInput.getSubCategoria());
        produto.setSubCategoria(subCategoria);

        Unidade unidade = new Unidade();
        unidade.setId(produtoInput.getUnidade());
        produto.setUnidade(unidade);

        Fornecedor fabricante = new Fornecedor();
        fabricante.setId(produtoInput.getFabricante());
        produto.setFabricante(fabricante);

        Marca marca = new Marca();
        marca.setId(produtoInput.getMarca());
        produto.setMarca(marca);

        Embalagem embalagem = new Embalagem();
        embalagem.setId(produtoInput.getEmbalagem());
        produto.setEmbalagem(embalagem);

        Apresentacao apresentacao = new Apresentacao();
        apresentacao.setId(produtoInput.getApresentacao());
        produto.setApresentacao(apresentacao);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(produtoInput.getEntidadeGestora());
        produto.setEntidadeGestora(entidadeGestora);

        try{
            if(produtoInput.getId() == 0){
                produto = produtoRepo.salvarProduto(produto);
            }else{
                produto.setId(produtoInput.getId());
                produto = produtoRepo.atualizarProduto(produto);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return produto;

    }
}

