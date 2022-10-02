package com.mines.CurvaABCZ.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.CurvaABCZ.Model.TipoCurvaAbczInput;
import com.mines.CurvaABCZ.Repository.TipoCurvaAbczRepository;
import com.mines.CurvaABCZ.Service.TipoCurvaAbczService;
import com.mines.Empresa.Model.Empresa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mines.util.exceptions.DomainException;

@Component
public class TipoCurvaAbczGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    @Autowired
    private TipoCurvaAbczService tipoCurvaAbczServ;

    public TipoCurvaAbcz tipoCurvaAbcz(Integer id){

        TipoCurvaAbcz tipoCurvaAbcz = null;
        try {
            tipoCurvaAbcz = tipoCurvaAbczServ.obterPorIdTipoCurvaAbcz(id);
        } catch (Exception e) {
            return tipoCurvaAbcz;
        }
        return tipoCurvaAbcz;
    }

    public List<TipoCurvaAbcz> tiposCurvaAbcz(Integer entidade){

        List<TipoCurvaAbcz> lista = tipoCurvaAbczServ.obterTodosTiposCurvaAbcz(entidade);
        return lista;

    }

    public Boolean deletarTipoCurvaAbcz(Integer id){

        Boolean ret = false;

        try{
            TipoCurvaAbcz tipoCurvaAbcz = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(id);
            ret = tipoCurvaAbczRepo.deletarTipoCurvaAbcz(tipoCurvaAbcz);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public TipoCurvaAbcz salvarTipoCurvaAbcz(TipoCurvaAbczInput tipoCurvaAbczInput) throws SQLException{

        TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();

        tipoCurvaAbcz.setDescricao(tipoCurvaAbczInput.getDescricao());
        tipoCurvaAbcz.setTipoCurva(tipoCurvaAbczInput.getTipoCurva());

        Empresa entidadeEstoque = new Empresa();
        entidadeEstoque.setId(tipoCurvaAbczInput.getEntidadeEstoque());
        tipoCurvaAbcz.setEntidadeEstoque(entidadeEstoque);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(tipoCurvaAbczInput.getEntidadeGestora());
        tipoCurvaAbcz.setEntidadeGestora(entidadeGestora);


        try{
            if(tipoCurvaAbczInput.getId() == 0){
                tipoCurvaAbcz = tipoCurvaAbczRepo.salvarTipoCurvaAbcz(tipoCurvaAbcz);
            }else{
                tipoCurvaAbcz.setId(tipoCurvaAbczInput.getId());
                tipoCurvaAbcz = tipoCurvaAbczRepo.atualizarTipoCurvaAbcz(tipoCurvaAbcz);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return tipoCurvaAbcz;

    }

}
