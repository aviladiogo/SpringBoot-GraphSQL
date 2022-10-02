package com.mines.Comprador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.Comprador.Model.CompradorCategoria;
import com.mines.Comprador.Model.CompradorCategoriaInput;
import com.mines.Comprador.Repository.CompradorCategoriaRepository;
import com.mines.Comprador.Service.CompradorCategoriaService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompradorCategoriaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CompradorCategoriaRepository compradorCategoriaRepo;

    @Autowired
    private CompradorCategoriaService compradorCategoriaServ;

    public CompradorCategoria compradorCategoria(Integer id){

        CompradorCategoria compradorCategoria = null;
        try {
            compradorCategoria = compradorCategoriaServ.obterPorIdCompradorCategoria(id);
        } catch (Exception e) {
            return compradorCategoria;
        }
        return compradorCategoria;
    }

    public List<CompradorCategoria> compradoresCategoria(Integer entidade){

        List<CompradorCategoria> lista = compradorCategoriaServ.obterTodosCompradoresCategoria(entidade);
        return lista;

    }

    public Boolean deletarCompradorCategoria(Integer id){

        Boolean ret = false;
        
        try{
            CompradorCategoria compradorCategoria = compradorCategoriaRepo.obterPorIdCompradorCategoria(id);
            ret = compradorCategoriaRepo.deletarCompradorCategoria(compradorCategoria);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CompradorCategoria salvarCompradorCategoria(CompradorCategoriaInput compradorCategoriaInput) throws SQLException{

        CompradorCategoria compradorCategoria = new CompradorCategoria();

        compradorCategoria.setLimiteCompra(compradorCategoriaInput.getLimiteCompra());
        compradorCategoria.setAtivo(compradorCategoriaInput.getAtivo());

        Categoria categoria = new Categoria();
        categoria.setId(compradorCategoriaInput.getCategoria());
        compradorCategoria.setCategoria(categoria);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorCategoriaInput.getEntidadeGestora());
        compradorCategoria.setEntidadeGestora(entidadeGestora);

        try{
            compradorCategoria.setComprador(compradorCategoriaInput.getComprador());
                compradorCategoria = compradorCategoriaRepo.salvarCompradorCategoria(compradorCategoria);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorCategoria;

    }

    public CompradorCategoria atualizarCompradorCategoria(CompradorCategoriaInput compradorCategoriaInput) throws SQLException{

        CompradorCategoria compradorCategoria = new CompradorCategoria();

        compradorCategoria.setLimiteCompra(compradorCategoriaInput.getLimiteCompra());
        compradorCategoria.setAtivo(compradorCategoriaInput.getAtivo());

        Categoria Categoria = new Categoria();
        Categoria.setId(compradorCategoriaInput.getCategoria());
        compradorCategoria.setCategoria(Categoria);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorCategoriaInput.getEntidadeGestora());
        compradorCategoria.setEntidadeGestora(entidadeGestora);

        try{
            compradorCategoria.setComprador(compradorCategoriaInput.getComprador());
                compradorCategoria = compradorCategoriaRepo.atualizarCompradorCategoria(compradorCategoria);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorCategoria;

    }

}




