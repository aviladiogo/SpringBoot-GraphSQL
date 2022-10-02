package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Model.CurvaAbczInput;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepository;
import com.mines.CurvaABCZ.Service.CurvaAbczService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurvaAbczGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    @Autowired
    private CurvaAbczService curvaAbczServ;

    public CurvaAbcz curvaAbcz(Integer id){

        CurvaAbcz curvaAbcz = null;
        try {
            curvaAbcz = curvaAbczServ.obterPorIdCurvaABCZ(id);
        } catch (Exception e) {
            return curvaAbcz;
        }
        return curvaAbcz;
    }

    public List<CurvaAbcz> curvasAbcz(Integer entidade){

        List<CurvaAbcz> lista = curvaAbczServ.obterTodosCurvasABCZ(entidade);
        return lista;

    }

    public Boolean deletarCurvaAbcz(Integer id){

        Boolean ret = false;

        try{
            CurvaAbcz curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(id);
            ret = curvaAbczRepo.deletarCurvaABCZ(curvaAbcz);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CurvaAbcz salvarCurvaAbcz(CurvaAbczInput curvaAbczInput) throws SQLException{

        CurvaAbcz curvaAbcz = new CurvaAbcz();

        curvaAbcz.setDescricao(curvaAbczInput.getDescricao());

        TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();
        tipoCurvaAbcz.setId(curvaAbczInput.getTipoCurvaAbcz());
        curvaAbcz.setTipoCurvaAbcz(tipoCurvaAbcz);

        curvaAbcz.setCurva(curvaAbczInput.getCurva());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(curvaAbczInput.getEntidadeGestora());
        curvaAbcz.setEntidadeGestora(entidadeGestora);

        try{
            if(curvaAbczInput.getId() == 0){
                curvaAbcz = curvaAbczRepo.salvarCurvaABCZ(curvaAbcz);
            }else{
                curvaAbcz.setId(curvaAbczInput.getId());
                curvaAbcz = curvaAbczRepo.atualizarCurvaABCZ(curvaAbcz);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return curvaAbcz;

    }

}