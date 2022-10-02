package com.mines.CondicaoPagto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CondicaoPagto.Model.TipoPagto;
import com.mines.CondicaoPagto.Model.TipoPagtoInput;
import com.mines.CondicaoPagto.Repository.TipoPagtoRepository;
import com.mines.CondicaoPagto.Service.TipoPagtoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoPagtoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TipoPagtoRepository tipoPagtoRepo;

    @Autowired
    private TipoPagtoService tipoPagtoService;

    public TipoPagto tipoPagto(Integer id){

        TipoPagto tipoPagto = null;
        try {
            tipoPagto = tipoPagtoService.obterPorIdTipoPagto(id);
        } catch (Exception e) {
            return tipoPagto;
        }
        return tipoPagto;
    }

    public List<TipoPagto> tiposPagto(Integer entidade){

        List<TipoPagto> lista = tipoPagtoService.obterTodosTiposPagto(entidade);
        return lista;

    }

    public Boolean deletarTipoPagto(Integer id){

        Boolean ret = false;

        try{
            TipoPagto tipoPagto = tipoPagtoRepo.obterPorIdTipoPagto(id);
            ret = tipoPagtoRepo.deletarTipoPagto(tipoPagto);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public TipoPagto salvarTipoPagto(TipoPagtoInput tipoPagtoInput) throws SQLException{

        TipoPagto tipoPagto = new TipoPagto();

        tipoPagto.setTitulo(tipoPagtoInput.getTitulo());
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(tipoPagtoInput.getEntidadeGestora());
        tipoPagto.setEntidadeGestora(entidadeGestora);

        try{
            if(tipoPagtoInput.getId() == 0){
                tipoPagto = tipoPagtoRepo.salvarTipoPagto(tipoPagto);
            }else{
                tipoPagto.setId(tipoPagtoInput.getId());
                tipoPagto = tipoPagtoRepo.atualizarTipoPagto(tipoPagto);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return tipoPagto;

    }

}
