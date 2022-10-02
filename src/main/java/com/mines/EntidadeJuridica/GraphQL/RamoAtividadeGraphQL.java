package com.mines.EntidadeJuridica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import com.mines.EntidadeJuridica.Model.RamoAtividadeInput;
import com.mines.EntidadeJuridica.Repository.RamoAtividadeRepository;
import com.mines.EntidadeJuridica.Service.RamoAtividadeService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RamoAtividadeGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private RamoAtividadeRepository ramoAtividadeRepo;

    @Autowired
    private RamoAtividadeService ramoAtividadeServ;

    public RamoAtividade ramoAtividade(Integer id){

        RamoAtividade ramoAtividade = null;
        try {
            ramoAtividade = ramoAtividadeServ.obterPorIdRamoAtividade(id);
        } catch (Exception e) {
            return ramoAtividade;
        }
        return ramoAtividade;
    }

    public List<RamoAtividade> ramoAtividades(Integer entidade){

        List<RamoAtividade> lista = ramoAtividadeServ.obterTodosRamoAtividades(entidade);
        return lista;

    }

    public Boolean deletarRamoAtividade(Integer id){

        Boolean ret = false;

        try{
            RamoAtividade ramoAtividade = ramoAtividadeRepo.obterPorIdRamoAtividade(id);
            ret = ramoAtividadeRepo.deletarRamoAtividade(ramoAtividade);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public RamoAtividade salvarRamoAtividade(RamoAtividadeInput ramoAtividadeInput) throws SQLException{

        RamoAtividade ramoAtividade = new RamoAtividade();

        ramoAtividade.setDescricao(ramoAtividadeInput.getDescricao());

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(ramoAtividadeInput.getUsuario());
        ramoAtividade.setUsuario(usuario);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(ramoAtividadeInput.getEntidadeGestora());
        ramoAtividade.setEntidadeGestora(entidadeGestora);

        try{
            if(ramoAtividadeInput.getId() == 0){
                ramoAtividade = ramoAtividadeRepo.salvarRamoAtividade(ramoAtividade);
            }else{
                ramoAtividade.setId(ramoAtividadeInput.getId());
                ramoAtividade = ramoAtividadeRepo.atualizarRamoAtividade(ramoAtividade);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return ramoAtividade;

    }

}
