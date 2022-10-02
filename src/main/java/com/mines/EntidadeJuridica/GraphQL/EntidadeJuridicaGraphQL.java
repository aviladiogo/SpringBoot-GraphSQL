package com.mines.EntidadeJuridica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.EntidadeJuridica;
import com.mines.EntidadeJuridica.Model.EntidadeJuridicaInput;
import com.mines.EntidadeJuridica.Repository.EntidadeJuridicaRepository;
import com.mines.EntidadeJuridica.Service.EntidadeJuridicaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntidadeJuridicaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private EntidadeJuridicaRepository entidadeJuridicaRepo;

    @Autowired
    private EntidadeJuridicaService entidadeJuridicaServ;

    public EntidadeJuridica entidadeJuridica(Integer id){

        EntidadeJuridica entidadeJuridica = null;
        try {
            entidadeJuridica = entidadeJuridicaServ.obterPorIdEntidadeJuridica(id);
        } catch (Exception e) {
            return entidadeJuridica;
        }
        return entidadeJuridica;
    }

    public List<EntidadeJuridica> entidadesJuridicas(Integer entidade){

        List<EntidadeJuridica> lista = entidadeJuridicaServ.obterTodosEntidadesJuridicas(entidade);
        return lista;

    }

    public Boolean deletarEntidadeJuridica(Integer id){

        Boolean ret = false;

        try{
            EntidadeJuridica entidadeJuridica = entidadeJuridicaRepo.obterPorIdEntidadeJuridica(id);
            ret = entidadeJuridicaRepo.deletarEntidadeJuridica(entidadeJuridica);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public EntidadeJuridica salvarEntidadeJuridica(EntidadeJuridicaInput entidadeJuridicaInput) throws SQLException{

        EntidadeJuridica entidadeJuridica = new EntidadeJuridica();

        entidadeJuridica.setDescricao(entidadeJuridicaInput.getDescricao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(entidadeJuridicaInput.getEntidadeGestora());
        entidadeJuridica.setEntidadeGestora(entidadeGestora);

        try{
            if(entidadeJuridicaInput.getId() == 0){
                entidadeJuridica = entidadeJuridicaRepo.salvarEntidadeJuridica(entidadeJuridica);
            }else{
                entidadeJuridica.setId(entidadeJuridicaInput.getId());
                entidadeJuridica = entidadeJuridicaRepo.atualizarEntidadeJuridica(entidadeJuridica);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return entidadeJuridica;

    }
}
