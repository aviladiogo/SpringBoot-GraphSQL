package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.PoliticaCurva;
import com.mines.CurvaABCZ.Model.PoliticaCurvaInput;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.CurvaABCZ.Repository.PoliticaCurvaRepository;
import com.mines.CurvaABCZ.Service.PoliticaCurvaService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoliticaCurvaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PoliticaCurvaRepository politicaCurvaRepo;

    @Autowired
    private PoliticaCurvaService politicaCurvaServ;

    public PoliticaCurva politicaCurva(Integer id){

        PoliticaCurva politicaCurva = null;
        try {
            politicaCurva = politicaCurvaServ.obterPorIdPoliticaCurva(id);
        } catch (Exception e) {
            return politicaCurva;
        }
        return politicaCurva;
    }

    public List<PoliticaCurva> politicaCurvas(Integer entidade){

        List<PoliticaCurva> lista = politicaCurvaServ.obterTodosPoliticaCurvas(entidade);
        return lista;

    }

    public Boolean deletarPoliticaCurva(Integer id){

        Boolean ret = false;

        try{
            PoliticaCurva politicaCurva = politicaCurvaRepo.obterPorIdPoliticaCurva(id);
            ret = politicaCurvaRepo.deletarPoliticaCurva(politicaCurva);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public PoliticaCurva salvarPoliticaCurva(PoliticaCurvaInput politicaCurvaInput) throws SQLException{

        PoliticaCurva politicaCurva = new PoliticaCurva();

        TipoCurvaAbcz tipoCurva = new TipoCurvaAbcz();
        tipoCurva.setId(politicaCurvaInput.getTipoCurva());
        politicaCurva.setTipoCurva(tipoCurva);

        politicaCurva.setDiasA(politicaCurvaInput.getDiasA());
        politicaCurva.setDiasB(politicaCurvaInput.getDiasB());
        politicaCurva.setDiasC(politicaCurvaInput.getDiasC());
        politicaCurva.setDiasZ(politicaCurvaInput.getDiasZ());
        politicaCurva.setFrequenciaA(politicaCurvaInput.getFrequenciaA());
        politicaCurva.setFrequenciaB(politicaCurvaInput.getFrequenciaB());
        politicaCurva.setFrequenciaC(politicaCurvaInput.getFrequenciaC());
        politicaCurva.setFrequenciaZ(politicaCurvaInput.getFrequenciaZ());
        politicaCurva.setMaxDiasA(politicaCurvaInput.getMaxDiasA());
        politicaCurva.setMaxDiasB(politicaCurvaInput.getMaxDiasB());
        politicaCurva.setMaxDiasC(politicaCurvaInput.getMaxDiasC());
        politicaCurva.setMaxDiasZ(politicaCurvaInput.getMaxDiasZ());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(politicaCurvaInput.getEntidadeGestora());
        politicaCurva.setEntidadeGestora(entidadeGestora);

        try{
            if(politicaCurvaInput.getId() == 0){
                politicaCurva = politicaCurvaRepo.salvarPoliticaCurva(politicaCurva);
            }else{
                politicaCurva.setId(politicaCurvaInput.getId());
                politicaCurva = politicaCurvaRepo.atualizarPoliticaCurva(politicaCurva);
            }
        }
        catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return politicaCurva;

    }

}
