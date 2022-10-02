package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDia;
import com.mines.CurvaABCZ.Model.CurvaGiroVendaDiaInput;
import com.mines.CurvaABCZ.Repository.CurvaGiroVendaDiaRepository;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurvaGiroVendaDiaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CurvaGiroVendaDiaRepository curvaGiroVendaDiaRepo;

    public CurvaGiroVendaDia curvaGiroVendaDia(Integer id){

        CurvaGiroVendaDia curvaGiroVendaDia = null;
        try {
            curvaGiroVendaDia = curvaGiroVendaDiaRepo.obterPorIdCurvaGiroVendaDia(id);
        } catch (Exception e) {
            return curvaGiroVendaDia;
        }
        return curvaGiroVendaDia;
    }

    public List<CurvaGiroVendaDia> curvasGiroVendaDia(Integer entidade){

        List<CurvaGiroVendaDia> lista = curvaGiroVendaDiaRepo.obterTodosCurvasGiroVendaDia(entidade);
        return lista;

    }

    public Boolean deletarCurvaGiroVendaDia(Integer id){

        Boolean ret = false;

        try{
            CurvaGiroVendaDia curvaGiroVendaDia = curvaGiroVendaDiaRepo.obterPorIdCurvaGiroVendaDia(id);
            ret = curvaGiroVendaDiaRepo.deletarCurvaGiroVendaDia(curvaGiroVendaDia);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CurvaGiroVendaDia salvarCurvaGiroVendaDia(CurvaGiroVendaDiaInput curvaGiroVendaDiaInput) throws SQLException{

        CurvaGiroVendaDia curvaGiroVendaDia = new CurvaGiroVendaDia();

        curvaGiroVendaDia.setValorGiroDia(curvaGiroVendaDiaInput.getValorGiroDia());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(curvaGiroVendaDiaInput.getEntidadeGestora());
        curvaGiroVendaDia.setEntidadeGestora(entidadeGestora);

        try{
            if(curvaGiroVendaDiaInput.getId() == 0){
                curvaGiroVendaDia = curvaGiroVendaDiaRepo.salvarCurvaGiroVendaDia(curvaGiroVendaDia);
            }else{
                curvaGiroVendaDia.setId(curvaGiroVendaDiaInput.getId());
                curvaGiroVendaDia = curvaGiroVendaDiaRepo.atualizarCurvaGiroVendaDia(curvaGiroVendaDia);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return curvaGiroVendaDia;

    }

}

