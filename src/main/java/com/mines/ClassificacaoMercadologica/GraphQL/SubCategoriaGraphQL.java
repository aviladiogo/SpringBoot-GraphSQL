package com.mines.ClassificacaoMercadologica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Model.SubCategoriaInput;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.ClassificacaoMercadologica.Service.SubCategoriaService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubCategoriaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private SubCategoriaRepository subCategoriaRepo;

    @Autowired
    private SubCategoriaService subCategoriaServ;

    public SubCategoria subCategoria(Integer id){

        SubCategoria subCategoria = null;
        try {
            subCategoria = subCategoriaServ.obterPorIdSubCategoria(id);
        } catch (Exception e) {
            return subCategoria;
        }
        return subCategoria;
    }

    public List<SubCategoria> subCategorias(Integer entidade){

        List<SubCategoria> lista = subCategoriaServ.obterTodosSubCategorias(entidade);
        return lista;

    }

    public List<SubCategoria> subCategoriasPorCategoria(Integer entidade, Integer categoria){

        List<SubCategoria> lista = subCategoriaServ.obterSubCategoriasPorCategoria(entidade, categoria);
        return lista;

    }

    public Boolean deletarSubCategoria(Integer id){

        Boolean ret = false;

        try{
            SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(id);
            ret = subCategoriaRepo.deletarSubCategoria(subCategoria);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public SubCategoria salvarSubCategoria(SubCategoriaInput subCategoriaInput) throws SQLException{

        SubCategoria subCategoria = new SubCategoria();

        subCategoria.setDescricao(subCategoriaInput.getDescricao());

        Categoria categoria = new Categoria();
        categoria.setId(subCategoriaInput.getCategoria());
        subCategoria.setCategoria(categoria);

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(subCategoriaInput.getUsuario());
        subCategoria.setUsuario(usuario);

        PessoaJuridica entidade = new PessoaJuridica();
        entidade.setId(subCategoriaInput.getEntidade());
        subCategoria.setEntidade(entidade);

        try{
            if(subCategoriaInput.getId() == 0){
                subCategoria = subCategoriaRepo.salvarSubCategoria(subCategoria);
            }else{
                subCategoria.setId(subCategoriaInput.getId());
                subCategoria = subCategoriaRepo.atualizarSubCategoria(subCategoria);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return subCategoria;

    }

}
