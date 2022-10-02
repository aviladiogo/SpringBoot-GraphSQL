package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.ClasseProduto;
import com.mines.CurvaABCZ.Model.ClasseProdutoInput;
import com.mines.CurvaABCZ.Repository.ClasseProdutoRepository;
import com.mines.CurvaABCZ.Service.ClasseProdutoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClasseProdutoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ClasseProdutoRepository classeProdutoRepo;

    @Autowired
    private ClasseProdutoService classeProdutoServ;

    @Autowired
    ItemClasseProdutoGraphQL itemClasseProdutoGraphQL;

    public ClasseProduto classeProduto(Integer id){

        ClasseProduto classeProduto = null;
        try {
            classeProduto = classeProdutoServ.obterPorIdClasseProduto(id);
        } catch (Exception e) {
            return classeProduto;
        }
        return classeProduto;
    }

    public List<ClasseProduto> classesProdutos(Integer entidade){

        List<ClasseProduto> lista = classeProdutoServ.obterTodosClasseProduto(entidade);
        return lista;

    }

    public Boolean deletarClasseProduto(Integer id){

        Boolean ret = false;

        try{
            ClasseProduto classeProduto = classeProdutoRepo.obterPorIdClasseProduto(id);
            ret = classeProdutoRepo.deletarClasseProduto(classeProduto);
            itemClasseProdutoGraphQL.deletarItemClasseProduto(id);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public ClasseProduto salvarClasseProduto(ClasseProdutoInput classeProdutoInput) throws SQLException{

        ClasseProduto classeProduto = new ClasseProduto();  

        classeProduto.setTitulo(classeProdutoInput.getTitulo());
        classeProduto.setDescricao(classeProdutoInput.getDescricao());
        
        PessoaFisica usuario  = new PessoaFisica();
        usuario.setId(classeProdutoInput.getUsuario());
        classeProduto.setUsuario(usuario);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(classeProdutoInput.getEntidadeGestora());
        classeProduto.setEntidadeGestora(entidadeGestora);

        try{
            if(classeProdutoInput.getId() == 0){
                classeProduto = classeProdutoRepo.salvarClasseProduto(classeProduto);

                itemClasseProdutoGraphQL.salvarItemClasseProduto(classeProduto.getId(), 
                    classeProdutoInput.getCurvas());
            }else{
                classeProduto.setId(classeProdutoInput.getId());
                classeProduto = classeProdutoRepo.atualizarClasseProduto(classeProduto);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return classeProduto;

    }

}