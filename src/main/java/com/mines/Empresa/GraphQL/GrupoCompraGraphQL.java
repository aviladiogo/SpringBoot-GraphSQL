package com.mines.Empresa.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoCompra;
import com.mines.Empresa.Model.GrupoCompraInput;
import com.mines.Empresa.Repository.GrupoCompraRepository;
import com.mines.Empresa.Service.GrupoCompraService;
import com.mines.util.exceptions.DomainException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoCompraGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private GrupoCompraRepository grupoCompraRepo;

    @Autowired
    private GrupoCompraService grupoCompraServ;

    public GrupoCompra grupoCompra(Integer id){

        GrupoCompra grupoCompra = null;
        try {
            grupoCompra = grupoCompraServ.obterPorIdGrupoCompra(id);
        } catch (Exception e) {
            return grupoCompra;
        }
        return grupoCompra;
    }

    public List<GrupoCompra> grupoCompras(Integer entidade){

        List<GrupoCompra> lista = grupoCompraServ.obterTodosGrupoCompras(entidade);
        return lista;

    }

    public Boolean deletarGrupoCompra(Integer id){

        Boolean ret = false;

        try{
            GrupoCompra grupoCompra = grupoCompraRepo.obterPorIdGrupoCompra(id);
            ret = grupoCompraRepo.deletarGrupoCompra(grupoCompra);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public GrupoCompra salvarGrupoCompra(GrupoCompraInput grupoCompraInput) throws SQLException{

        GrupoCompra grupoCompra = new GrupoCompra();
        grupoCompra.setDescricao(grupoCompraInput.getDescricao());
        grupoCompra.setExterno(grupoCompraInput.getExterno());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(grupoCompraInput.getEntidadeGestora());
        grupoCompra.setEntidadeGestora(entidadeGestora);

        try{
            if(grupoCompraInput.getId() == 0){
                grupoCompra = grupoCompraRepo.salvarGrupoCompra(grupoCompra);
            }else{
                grupoCompra.setId(grupoCompraInput.getId());
                grupoCompra = grupoCompraRepo.atualizarGrupoCompra(grupoCompra);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return grupoCompra;

    }

}
