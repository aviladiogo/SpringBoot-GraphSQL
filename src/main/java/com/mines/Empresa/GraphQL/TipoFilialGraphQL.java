package com.mines.Empresa.GraphQL;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.TipoFilial;
import com.mines.Empresa.Model.TipoFilialInput;
import com.mines.Empresa.Repository.TipoFilialRepository;
import com.mines.Empresa.Service.TipoFilialService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoFilialGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TipoFilialRepository tipoFilialRepo;

    @Autowired
    private TipoFilialService tipoFilialServ;

    public TipoFilial tipoFilial(Integer id){

        TipoFilial tipoFilial = null;
        try {
            tipoFilial = tipoFilialServ.obterPorIdTipoFilial(id);
        } catch (Exception e) {
            return tipoFilial;
        }
        return tipoFilial;
    }

    public List<TipoFilial> tipoFiliais(Integer entidade){

        List<TipoFilial> lista = tipoFilialServ.obterTodosTipoFiliais(entidade);
        return lista;

    }

    public Boolean deletarTipoFilial(Integer id){

        Boolean ret = false;

        try{
            TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(id);
            ret = tipoFilialRepo.deletarTipoFilial(tipoFilial);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public TipoFilial salvarTipoFilial(TipoFilialInput tipoFilialInput){

        TipoFilial tipoFilial = new TipoFilial();
        tipoFilial.setDescricao(tipoFilialInput.getDescricao());
        tipoFilial.setVendeProduto(tipoFilialInput.getVendeProduto());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(tipoFilialInput.getEntidadeGestora());
        tipoFilial.setEntidadeGestora(entidadeGestora);


        try{
            if(tipoFilialInput.getId() == 0){
                tipoFilial = tipoFilialRepo.salvarTipoFilial(tipoFilial);
            }else{
                tipoFilial.setId(tipoFilialInput.getId());
                tipoFilial = tipoFilialRepo.atualizarTipoFilial(tipoFilial);
            }    
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }
        
        return tipoFilial;

    }

}

