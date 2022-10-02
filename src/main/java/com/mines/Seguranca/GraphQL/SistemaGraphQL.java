package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Seguranca.Model.Sistema;
import com.mines.Seguranca.Model.SistemaInput;
import com.mines.Seguranca.Repository.SistemaRepository;
import com.mines.Seguranca.Service.SistemaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SistemaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private SistemaRepository sistemaRepo;

    @Autowired
    private SistemaService sistemaServ;

    public Sistema sistema(Integer id){

        Sistema sistema = null;
        try {
            sistema = sistemaServ.obterPorIdSistema(id);
        } catch (Exception e) {
            return sistema;
        }
        return sistema;
    }

    public List<Sistema> sistemas(Integer entidade){
        
        List<Sistema> lista = sistemaServ.obterTodosSistemas(entidade);
        return lista;
    }

    public Boolean deletarSistema(Integer id){

        Boolean ret = false;

        try{
            Sistema sistema = sistemaRepo.obterPorIdSistema(id);
            ret = sistemaRepo.deletarSistema(sistema);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Sistema salvarSistema(SistemaInput sistemaInput) throws SQLException{

        Sistema sistema = new Sistema();

        sistema.setDescricao(sistemaInput.getDescricao());
        sistema.setAtivo(sistemaInput.getAtivo());
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(sistemaInput.getEntidadeGestora());
        sistema.setEntidadeGestora(entidadeGestora);
        
        try{
            if(sistemaInput.getId() == 0){
                sistema = sistemaRepo.salvarSistema(sistema);
            }else{
                sistema.setId(sistemaInput.getId());
                sistema = sistemaRepo.atualizarSistema(sistema);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return sistema;

    }

}