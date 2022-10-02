package com.mines.Colaborador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Colaborador.Model.FuncaoInput;
import com.mines.Colaborador.Repository.FuncaoRepository;
import com.mines.Colaborador.Service.FuncaoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private FuncaoRepository funcaoRepo;

    @Autowired
    private FuncaoService funcaoServ;

    public Funcao funcao(Integer id){

        Funcao funcao = null;
        try {
            funcao = funcaoServ.obterPorIdFuncao(id);
        } catch (Exception e) {
            return funcao;
        }
        return funcao;
    }

    public List<Funcao> funcoes(Integer entidade){

        List<Funcao> lista = funcaoServ.obterTodosFuncoes(entidade);
        return lista;

    }

    public Boolean deletarFuncao(Integer id){

        Boolean ret = false;

        try{
            Funcao funcao = funcaoRepo.obterPorIdFuncao(id);
            ret = funcaoRepo.deletarFuncao(funcao);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Funcao salvarFuncao(FuncaoInput funcaoInput) throws SQLException{

        Funcao funcao = new Funcao();

        funcao.setTitulo(funcaoInput.getTitulo());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(funcaoInput.getEntidadeGestora());
        funcao.setEntidadeGestora(entidadeGestora);

        try{
            if(funcaoInput.getId() == 0){
                funcao = funcaoRepo.salvarFuncao(funcao);
            }else{
                funcao.setId(funcaoInput.getId());
                funcao = funcaoRepo.atualizarFuncao(funcao);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return funcao;

    }

}
