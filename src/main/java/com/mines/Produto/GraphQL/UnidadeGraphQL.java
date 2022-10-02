package com.mines.Produto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Unidade;
import com.mines.Produto.Model.UnidadeInput;
import com.mines.Produto.Repository.UnidadeRepository;
import com.mines.Produto.Service.UnidadeService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnidadeGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private UnidadeRepository unidadeRepo;

    @Autowired
    private UnidadeService unidadeServ;

    public Unidade unidade(Integer id){

        Unidade unidade = null;
        try {
            unidade = unidadeServ.obterPorIdUnidade(id);
        } catch (Exception e) {
            return unidade;
        }

        return unidade;
    }

    public List<Unidade> unidades(Integer entidade){

        List<Unidade> lista = unidadeServ.obterTodosUnidades(entidade);
        return lista;
    }

    public Boolean deletarUnidade(Integer id){

        Boolean ret = false;

        try{
            Unidade unidade = unidadeRepo.obterPorIdUnidade(id);
            ret = unidadeRepo.deletarUnidade(unidade);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Unidade salvarUnidade(UnidadeInput unidadeInput) throws SQLException{

        Unidade unidade = new Unidade();

        unidade.setDescricao(unidadeInput.getDescricao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(unidadeInput.getEntidadeGestora());
        unidade.setEntidadeGestora(entidadeGestora);

        try{
            if(unidadeInput.getId() == 0){
                unidade = unidadeRepo.salvarUnidade(unidade);
            }else{
                unidade.setId(unidadeInput.getId());
                unidade = unidadeRepo.atualizarUnidade(unidade);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return unidade;

    }

}
