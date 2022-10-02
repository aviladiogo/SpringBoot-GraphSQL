package com.mines.ClassificacaoMercadologica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.util.exceptions.DomainException;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.CategoriaInput;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepository;
import com.mines.ClassificacaoMercadologica.Service.CategoriaService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private CategoriaService categoriaServ;

    public Categoria categoria(Integer id){

        Categoria categoria = null;
        try {
            categoria = categoriaServ.obterPorIdCategoria(id);
        } catch (Exception e) {
            return categoria;
        }
        return categoria;
    }

    public List<Categoria> categorias(Integer entidade){

        List<Categoria> lista = categoriaServ.obterTodosCategorias(entidade);
        return lista;

    }

    public List<Categoria> categoriasPorSecao(Integer entidade, Integer secao){

        List<Categoria> lista = categoriaServ.obterCategoriasPorSecao(entidade, secao);
        return lista;

    }

    public Boolean deletarCategoria(Integer id){

        Boolean ret = false;

        try{
            Categoria categoria = categoriaRepo.obterPorIdCategoria(id);
            ret = categoriaRepo.deletarCategoria(categoria);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Categoria salvarCategoria(CategoriaInput categoriaInput) throws SQLException{

        Categoria categoria = new Categoria();

        categoria.setDescricao(categoriaInput.getDescricao());

        Secao secao = new Secao();
        secao.setId(categoriaInput.getSecao());
        categoria.setSecao(secao);

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(categoriaInput.getUsuario());
        categoria.setUsuario(usuario);

        PessoaJuridica entidade = new PessoaJuridica();
        entidade.setId(categoriaInput.getEntidade());
        categoria.setEntidade(entidade);

        try{
            if(categoriaInput.getId() == 0){
                categoria = categoriaRepo.salvarCategoria(categoria);
            }else{
                categoria.setId(categoriaInput.getId());
                categoria = categoriaRepo.atualizarCategoria(categoria);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return categoria;

    }

}
