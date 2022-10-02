package com.mines.CondicaoPagto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.CondicaoPagto.Model.PrazoParcelaInput;
import com.mines.CondicaoPagto.Repository.PrazoParcelaRepository;
import com.mines.CondicaoPagto.Service.PrazoParcelaService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrazoParcelaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PrazoParcelaRepository prazoParcelaRepo;

    @Autowired
    private PrazoParcelaService prazoParcelaService;

    public PrazoParcela prazoParcela(Integer id){

        PrazoParcela prazoParcela = null;
        try {
            prazoParcela = prazoParcelaService.obterPorIdPrazoParcela(id);
        } catch (Exception e) {
            return prazoParcela;
        }
        return prazoParcela;
    }

    public List<PrazoParcela> prazoParcelas(Integer entidade){

        List<PrazoParcela> lista = prazoParcelaService.obterTodosPrazoParcelas(entidade);
        return lista;

    }

    public Boolean deletarPrazoParcela(Integer id){

        Boolean ret = false;

        try{
            PrazoParcela prazoParcela = prazoParcelaRepo.obterPorIdPrazoParcela(id);
            ret = prazoParcelaRepo.deletarPrazoParcela(prazoParcela);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public PrazoParcela salvarPrazoParcela(PrazoParcelaInput prazoParcelaInput) throws SQLException{

        PrazoParcela prazoParcela = new PrazoParcela();

        prazoParcela.setTitulo(prazoParcelaInput.getTitulo());
        prazoParcela.setDias(prazoParcelaInput.getDias());
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(prazoParcelaInput.getEntidadeGestora());
        prazoParcela.setEntidadeGestora(entidadeGestora);

        try{
            if(prazoParcelaInput.getId() == 0){
                prazoParcela = prazoParcelaRepo.salvarPrazoParcela(prazoParcela);
            }else{
                prazoParcela.setId(prazoParcelaInput.getId());
                prazoParcela = prazoParcelaRepo.atualizarPrazoParcela(prazoParcela);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return prazoParcela;

    }

}