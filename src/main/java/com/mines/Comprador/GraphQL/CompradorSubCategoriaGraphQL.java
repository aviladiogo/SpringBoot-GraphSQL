package com.mines.Comprador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.Comprador.Model.CompradorSubCategoria;
import com.mines.Comprador.Model.CompradorSubCategoriaInput;
import com.mines.Comprador.Repository.CompradorSubCategoriaRepository;
import com.mines.Comprador.Service.CompradorSubCategoriaService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompradorSubCategoriaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CompradorSubCategoriaRepository compradorSubCategoriaRepo;

    @Autowired
    private CompradorSubCategoriaService compradorSubCategoriaServ;

    public CompradorSubCategoria compradorSubCategoria(Integer id){

        CompradorSubCategoria compradorSubCategoria = null;
        try {
            compradorSubCategoria = compradorSubCategoriaServ.obterPorIdCompradorSubCategoria(id);
        } catch (Exception e) {
            return compradorSubCategoria;
        }
        return compradorSubCategoria;
    }

    public List<CompradorSubCategoria> compradoresSubCategoria(Integer entidade){

        List<CompradorSubCategoria> lista = compradorSubCategoriaServ.obterTodosCompradoresSubCategoria(entidade);
        return lista;

    }

    public Boolean deletarCompradorSubCategoria(Integer id){

        Boolean ret = false;
        
        try{
            CompradorSubCategoria compradorSubCategoria = compradorSubCategoriaRepo.obterPorIdCompradorSubCategoria(id);
            ret = compradorSubCategoriaRepo.deletarCompradorSubCategoria(compradorSubCategoria);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CompradorSubCategoria salvarCompradorSubCategoria(CompradorSubCategoriaInput compradorSubCategoriaInput) throws SQLException{

        CompradorSubCategoria compradorSubCategoria = new CompradorSubCategoria();

        compradorSubCategoria.setLimiteCompra(compradorSubCategoriaInput.getLimiteCompra());
        compradorSubCategoria.setAtivo(compradorSubCategoriaInput.getAtivo());

        SubCategoria subCategoria = new SubCategoria();
        subCategoria.setId(compradorSubCategoriaInput.getSubCategoria());
        compradorSubCategoria.setSubCategoria(subCategoria);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorSubCategoriaInput.getEntidadeGestora());
        compradorSubCategoria.setEntidadeGestora(entidadeGestora);

        try{
            compradorSubCategoria.setComprador(compradorSubCategoriaInput.getComprador());
                compradorSubCategoria = compradorSubCategoriaRepo.salvarCompradorSubCategoria(compradorSubCategoria);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorSubCategoria;

    }

    public CompradorSubCategoria atualizarCompradorSubCategoria(CompradorSubCategoriaInput compradorSubCategoriaInput) throws SQLException{

        CompradorSubCategoria compradorSubCategoria = new CompradorSubCategoria();

        compradorSubCategoria.setLimiteCompra(compradorSubCategoriaInput.getLimiteCompra());
        compradorSubCategoria.setAtivo(compradorSubCategoriaInput.getAtivo());

        SubCategoria subCategoria = new SubCategoria();
        subCategoria.setId(compradorSubCategoriaInput.getSubCategoria());
        compradorSubCategoria.setSubCategoria(subCategoria);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorSubCategoriaInput.getEntidadeGestora());
        compradorSubCategoria.setEntidadeGestora(entidadeGestora);

        try{
            compradorSubCategoria.setComprador(compradorSubCategoriaInput.getComprador());
                compradorSubCategoria = compradorSubCategoriaRepo.atualizarCompradorSubCategoria(compradorSubCategoria);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorSubCategoria;

    }

}




