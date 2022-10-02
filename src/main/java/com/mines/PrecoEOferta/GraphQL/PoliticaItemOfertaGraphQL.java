package com.mines.PrecoEOferta.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;
import com.mines.PrecoEOferta.Model.PoliticaItemOfertaInput;
import com.mines.PrecoEOferta.Repository.PoliticaItemOfertaRepository;
import com.mines.PrecoEOferta.Service.PoliticaItemOfertaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoliticaItemOfertaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PoliticaItemOfertaRepository politicaItemOfertaRepo;

    @Autowired
    private PoliticaItemOfertaService politicaItemOfertaServ;

    public PoliticaItemOferta politicaItemOferta(Integer id){

        PoliticaItemOferta politicaItemOferta = null;
        try {
            politicaItemOferta = politicaItemOfertaServ.obterPorIdPoliticaItemOferta(id);
        } catch (Exception e) {
            return politicaItemOferta;
        }
        return politicaItemOferta;
    }

    public List<PoliticaItemOferta> politicaItensOferta(Integer entidade){

        List<PoliticaItemOferta> lista = politicaItemOfertaServ.obterTodosPoliticaItensOferta(entidade);
        return lista;

    }

    public Boolean deletarPoliticaItemOferta(Integer id){

        Boolean ret = false;

        try{
            PoliticaItemOferta politicaItemOferta = politicaItemOfertaRepo.obterPorIdPoliticaItemOferta(id);
            ret = politicaItemOfertaRepo.deletarPoliticaItemOferta(politicaItemOferta);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public PoliticaItemOferta salvarPoliticaItemOferta(PoliticaItemOfertaInput politicaItemOfertaInput) throws SQLException{

        PoliticaItemOferta politicaItemOferta = new PoliticaItemOferta();

        politicaItemOferta.setItemOferta(politicaItemOfertaInput.getItemOferta());

        politicaItemOferta.setQuantidadeMinima(politicaItemOfertaInput.getQuantidadeMinima());
        politicaItemOferta.setQuantidadeMaxima(politicaItemOfertaInput.getQuantidadeMaxima());
        politicaItemOferta.setPrecoUnitario(politicaItemOfertaInput.getPrecoUnitario());
        politicaItemOferta.setPercentualDesconto(politicaItemOfertaInput.getPercentualDesconto());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(politicaItemOfertaInput.getEntidadeGestora());
        politicaItemOferta.setEntidadeGestora(entidadeGestora);

        try{
            if(politicaItemOfertaInput.getId() == 0){
                politicaItemOferta = politicaItemOfertaRepo.salvarPoliticaItemOferta(politicaItemOferta);
            }else{
                politicaItemOferta.setId(politicaItemOfertaInput.getId());
                politicaItemOferta = politicaItemOfertaRepo.atualizarPoliticaItemOferta(politicaItemOferta);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return politicaItemOferta;

    }

}

