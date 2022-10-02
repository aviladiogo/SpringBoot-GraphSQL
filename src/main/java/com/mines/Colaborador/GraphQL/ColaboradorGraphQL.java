package com.mines.Colaborador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Colaborador.Model.Colaborador;
import com.mines.Colaborador.Model.ColaboradorInput;
import com.mines.Colaborador.Model.Funcao;
import com.mines.Colaborador.Repository.ColaboradorRepository;
import com.mines.Colaborador.Service.ColaboradorService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ColaboradorGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ColaboradorRepository colaboradorRepo;

    @Autowired
    private ColaboradorService colaboradorServ;

    public Colaborador colaborador(Integer id){

        Colaborador colaborador = null;
        try {
            colaborador = colaboradorServ.obterPorIdColaborador(id);
        } catch (Exception e) {
            return colaborador;
        }
        return colaborador;
    }


    public List<Colaborador> colaboradores(Integer entidade){

        List<Colaborador> lista = colaboradorServ.obterTodosColaboradores(entidade);
        return lista;

    }

    public Boolean deletarColaborador(Integer id){

        Boolean ret = false;

        try{
            Colaborador colaborador = colaboradorRepo.obterPorIdColaborador(id);
            ret = colaboradorRepo.deletarColaborador(colaborador);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Colaborador salvarColaborador(ColaboradorInput colaboradorInput) throws SQLException{

        Colaborador colaborador = new Colaborador();

        colaborador.setDescricao(colaboradorInput.getDescricao());

        Funcao funcao = new Funcao();
        funcao.setId(colaboradorInput.getFuncao());
        colaborador.setFuncao(funcao);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(colaboradorInput.getEntidadeGestora());
        colaborador.setEntidadeGestora(entidadeGestora);

        try{
            if(colaboradorInput.getId() == 0){
                colaborador = colaboradorRepo.salvarColaborador(colaborador);
            }else{
                colaborador.setId(colaboradorInput.getId());
                colaborador = colaboradorRepo.atualizarColaborador(colaborador);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return colaborador;

    }

}

