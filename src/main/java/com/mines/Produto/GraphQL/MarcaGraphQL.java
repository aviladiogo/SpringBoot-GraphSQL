package com.mines.Produto.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Produto.Model.Marca;
import com.mines.Produto.Model.MarcaInput;
import com.mines.Produto.Repository.MarcaRepository;
import com.mines.Produto.Service.MarcaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarcaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private MarcaRepository marcaRepo;

    @Autowired
    private MarcaService marcaServ;

    public Marca marca(Integer id){

        Marca marca = null;
        try {
            marca = marcaServ.obterPorIdMarca(id);
        } catch (Exception e) {
            return marca;
        }
        return marca;
    }

    public List<Marca> marcas(Integer entidade){

        List<Marca> lista = marcaServ.obterTodosMarcas(entidade);
        return lista;
    }

    public Boolean deletarMarca(Integer id){

        Boolean ret = false;

        try{
            Marca marca = marcaRepo.obterPorIdMarca(id);
            ret = marcaRepo.deletarMarca(marca);
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }   

    public Marca salvarMarca(MarcaInput marcaInput) throws SQLException{

        Marca marca = new Marca();

        marca.setDescricao(marcaInput.getDescricao());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(marcaInput.getEntidadeGestora());
        marca.setEntidadeGestora(entidadeGestora);

        try{
            if(marcaInput.getId() == 0){
                marca = marcaRepo.salvarMarca(marca);
            }else{
                marca.setId(marcaInput.getId());
                marca = marcaRepo.atualizarMarca(marca);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return marca;

    }

}

