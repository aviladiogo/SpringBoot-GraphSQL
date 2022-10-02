package com.mines.Empresa.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.Empresa.Model.GrupoFilialInput;
import com.mines.Empresa.Repository.GrupoFilialRepository;
import com.mines.Empresa.Service.GrupoFilialService;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoFilialGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private GrupoFilialRepository grupoFilialRepo;

    @Autowired
    private GrupoFilialService grupoFilialServ;

    public GrupoFilial grupoFilial(Integer id){

        GrupoFilial grupoFilial = null;
        try {
            grupoFilial = grupoFilialServ.obterPorIdGrupoFilial(id);
        } catch (Exception e) {
            return grupoFilial;
        }
        return grupoFilial;
    }

    public List<GrupoFilial> grupoFiliais(Integer entidade){

        List<GrupoFilial> lista = grupoFilialServ.obterTodosGrupoFiliais(entidade);
        return lista;

    }

    public Boolean deletarGrupoFilial(Integer id){

        Boolean ret = false;

        try{
            GrupoFilial grupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(id);
            ret = grupoFilialRepo.deletarGrupoFilial(grupoFilial);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public GrupoFilial salvarGrupoFilial(GrupoFilialInput grupoFilialInput) throws SQLException{

        GrupoFilial grupoFilial = new GrupoFilial();
        grupoFilial.setDescricao(grupoFilialInput.getDescricao());

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(grupoFilialInput.getResponsavel());
        grupoFilial.setResponsavel(responsavel);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(grupoFilialInput.getEntidadeGestora());
        grupoFilial.setEntidadeGestora(entidadeGestora);

        try{
            if(grupoFilialInput.getId() == 0){
                grupoFilial = grupoFilialRepo.salvarGrupoFilial(grupoFilial);
            }else{
                grupoFilial.setId(grupoFilialInput.getId());
                grupoFilial = grupoFilialRepo.atualizarGrupoFilial(grupoFilial);
            }
    
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }
        
        return grupoFilial;

    }

}
