package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Model.ModuloInput;
import com.mines.Seguranca.Model.Sistema;
import com.mines.Seguranca.Repository.ModuloRepository;
import com.mines.Seguranca.Service.ModuloService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModuloGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private ModuloRepository moduloRepo;

    @Autowired
    private ModuloService moduloServ;

    public Modulo modulo(Integer id){

        Modulo modulo = null;
        try {
            modulo = moduloServ.obterPorIdModulo(id);
        } catch (Exception e) {
            return modulo;
        }
        return modulo;
    }

    public List<Modulo> modulos(Integer entidade){
        
        List<Modulo> lista = moduloServ.obterTodosModulos(entidade);
        return lista;
    }

    public Boolean deletarModulo(Integer id){

        Boolean ret = false;

        try{
            Modulo modulo = moduloRepo.obterPorIdModulo(id);
            ret = moduloRepo.deletarModulo(modulo);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Modulo salvarModulo(ModuloInput moduloInput) throws SQLException{

        Modulo modulo = new Modulo();

        modulo.setDescricao(moduloInput.getDescricao());
        modulo.setAtivo(moduloInput.getAtivo());

        Sistema sistema = new Sistema();
        sistema.setId(moduloInput.getSistema());
        modulo.setSistema(sistema);
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(moduloInput.getEntidadeGestora());
        modulo.setEntidadeGestora(entidadeGestora);
        
        try{
            if(moduloInput.getId() == 0){
                modulo = moduloRepo.salvarModulo(modulo);
            }else{
                modulo.setId(moduloInput.getId());
                modulo = moduloRepo.atualizarModulo(modulo);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return modulo;

    }

}