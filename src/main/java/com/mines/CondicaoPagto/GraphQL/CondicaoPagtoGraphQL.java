package com.mines.CondicaoPagto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CondicaoPagto.Model.CondicaoPagto;
import com.mines.CondicaoPagto.Model.CondicaoPagtoInput;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.CondicaoPagto.Repository.CondicaoPagtoRepository;
import com.mines.CondicaoPagto.Service.CondicaoPagtoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CondicaoPagtoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CondicaoPagtoRepository condicaoPagtoRepo;

    @Autowired
    private CondicaoPagtoService condicaoPagtoServ;

    public CondicaoPagto condicaoPagto(Integer id){

        CondicaoPagto condicaoPagto = null;
        try {
            condicaoPagto = condicaoPagtoServ.obterPorIdCondicaoPagto(id);
        } catch (Exception e) {
            return condicaoPagto;
        }
        return condicaoPagto;
    }

    public List<CondicaoPagto> condicoesPagto(Integer entidade){

        List<CondicaoPagto> lista = condicaoPagtoServ.obterTodosCondicoesPagto(entidade);
        return lista;

    }

    public Boolean deletarCondicaoPagto(Integer id){

        Boolean ret = false;

        try{
            CondicaoPagto condicaoPagto = condicaoPagtoRepo.obterPorIdCondicaoPagto(id);
            ret = condicaoPagtoRepo.deletarCondicaoPagto(condicaoPagto);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CondicaoPagto salvarCondicaoPagto(CondicaoPagtoInput condicaoPagtoInput) throws SQLException{

        CondicaoPagto condicaoPagto = new CondicaoPagto();

        condicaoPagto.setTitulo(condicaoPagtoInput.getTitulo());
        condicaoPagto.setDescricao(condicaoPagtoInput.getDescricao());
        condicaoPagto.setParcelas(condicaoPagtoInput.getParcelas());

        PrazoParcela prazoParcelas = new PrazoParcela();
        prazoParcelas.setId(condicaoPagtoInput.getPrazoParcelas());
        condicaoPagto.setPrazoParcelas(prazoParcelas);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(condicaoPagtoInput.getEntidadeGestora());
        condicaoPagto.setEntidadeGestora(entidadeGestora);

        try{
            if(condicaoPagtoInput.getId() == 0){
                condicaoPagto = condicaoPagtoRepo.salvarCondicaoPagto(condicaoPagto);
            }else{
                condicaoPagto.setId(condicaoPagtoInput.getId());
                condicaoPagto = condicaoPagtoRepo.atualizarCondicaoPagto(condicaoPagto);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return condicaoPagto;

    }

}
