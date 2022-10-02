package com.mines.Fornecedor.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Fornecedor.Model.TipoFrete;
import com.mines.Fornecedor.Model.TipoFreteInput;
import com.mines.Fornecedor.Repository.TipoFreteRepository;
import com.mines.Fornecedor.Service.TipoFreteService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TipoFreteGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TipoFreteRepository tipoFreteRepo;

    @Autowired
    private TipoFreteService tipoFreteServ;

    public TipoFrete tipoFrete(Integer id){

        TipoFrete tipoFrete = null;
        try {
            tipoFrete = tipoFreteServ.obterPorIdTipoFrete(id);
        } catch (Exception e) {
            return tipoFrete;
        }
        return tipoFrete;
    }

    public List<TipoFrete> tiposFrete(Integer entidade){

        List<TipoFrete> lista = tipoFreteServ.obterTodosTiposFrete(entidade);
        return lista;

    }

    public Boolean deletarTipoFrete(Integer id){

        Boolean ret = false;

        try{
            TipoFrete tipoFrete = tipoFreteRepo.obterPorIdTipoFrete(id);
            ret = tipoFreteRepo.deletarTipoFrete(tipoFrete);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public TipoFrete salvarTipoFrete(TipoFreteInput tipoFreteInput) throws SQLException{

        TipoFrete tipoFrete = new TipoFrete();

        tipoFrete.setDescricao(tipoFreteInput.getDescricao());

        PessoaFisica usuario = new PessoaFisica();
        usuario.setId(tipoFreteInput.getUsuario());
        tipoFrete.setUsuario(usuario);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(tipoFreteInput.getEntidadeGestora());
        tipoFrete.setEntidadeGestora(entidadeGestora);

        try{
            if(tipoFreteInput.getId() == 0){
                tipoFrete = tipoFreteRepo.salvarTipoFrete(tipoFrete);
            }else{
                tipoFrete.setId(tipoFreteInput.getId());
                tipoFrete = tipoFreteRepo.atualizarTipoFrete(tipoFrete);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return tipoFrete;

    }

}
