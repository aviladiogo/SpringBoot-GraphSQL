package com.mines.Seguranca.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Seguranca.Model.Perfil;
import com.mines.Seguranca.Model.PerfilInput;
import com.mines.Seguranca.Repository.PerfilRepository;
import com.mines.Seguranca.Service.PerfilService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PerfilGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PerfilRepository perfilRepo;

    @Autowired
    private PerfilService perfilServ;

    public Perfil perfil(Integer id){

        Perfil perfil = null;
        try {
            perfil = perfilServ.obterPorIdPerfil(id);
        } catch (Exception e) {
            return perfil;
        }
        return perfil;
    }

    public List<Perfil> perfis(Integer entidade){
        
        List<Perfil> lista = perfilServ.obterTodosPerfis(entidade);
        return lista;
    }

    public Boolean deletarPerfil(Integer id){

        Boolean ret = false;

        try{
            Perfil perfil = perfilRepo.obterPorIdPerfil(id);
            ret = perfilRepo.deletarPerfil(perfil);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Perfil salvarPerfil(PerfilInput perfilInput) throws SQLException{

        Perfil perfil = new Perfil();

        perfil.setDescricao(perfilInput.getDescricao());
        perfil.setAtivo(perfilInput.getAtivo());
        
        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(perfilInput.getEntidadeGestora());
        perfil.setEntidadeGestora(entidadeGestora);

        try{
            if(perfilInput.getId() == 0){
                perfil = perfilRepo.salvarPerfil(perfil);
            }else{
                perfil.setId(perfilInput.getId());
                perfil = perfilRepo.atualizarPerfil(perfil);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return perfil;

    }

}
