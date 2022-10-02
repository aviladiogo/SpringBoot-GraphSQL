package com.mines.Comprador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.Comprador.Model.CompradorSecao;
import com.mines.Comprador.Model.CompradorSecaoInput;
import com.mines.Comprador.Repository.CompradorSecaoRepository;
import com.mines.Comprador.Service.CompradorSecaoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompradorSecaoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CompradorSecaoRepository compradorSecaoRepo;

    @Autowired
    private CompradorSecaoService compradorSecaoServ;

    public CompradorSecao compradorSecao(Integer id){

        CompradorSecao compradorSecao = null;
        try {
            compradorSecao = compradorSecaoServ.obterPorIdCompradorSecao(id);
        } catch (Exception e) {
            return compradorSecao;
        }
        return compradorSecao;
    }

    public List<CompradorSecao> compradoresSecao(Integer entidade){

        List<CompradorSecao> lista = compradorSecaoServ.obterTodosCompradoresSecao(entidade);
        return lista;

    }

    public Boolean deletarCompradorSecao(Integer id){

        Boolean ret = false;
        
        try{
            CompradorSecao compradorSecao = compradorSecaoRepo.obterPorIdCompradorSecao(id);
            ret = compradorSecaoRepo.deletarCompradorSecao(compradorSecao);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CompradorSecao salvarCompradorSecao(CompradorSecaoInput compradorSecaoInput) throws SQLException{

        CompradorSecao compradorSecao = new CompradorSecao();

        compradorSecao.setLimiteCompra(compradorSecaoInput.getLimiteCompra());
        compradorSecao.setAtivo(compradorSecaoInput.getAtivo());

        Secao secao = new Secao();
        secao.setId(compradorSecaoInput.getSecao());
        compradorSecao.setSecao(secao);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorSecaoInput.getEntidadeGestora());
        compradorSecao.setEntidadeGestora(entidadeGestora);

        try{
            compradorSecao.setComprador(compradorSecaoInput.getComprador());
                compradorSecao = compradorSecaoRepo.salvarCompradorSecao(compradorSecao);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorSecao;

    }

    public CompradorSecao atualizarCompradorSecao(CompradorSecaoInput compradorSecaoInput) throws SQLException{

        CompradorSecao compradorSecao = new CompradorSecao();

        compradorSecao.setLimiteCompra(compradorSecaoInput.getLimiteCompra());
        compradorSecao.setAtivo(compradorSecaoInput.getAtivo());

        Secao secao = new Secao();
        secao.setId(compradorSecaoInput.getSecao());
        compradorSecao.setSecao(secao);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorSecaoInput.getEntidadeGestora());
        compradorSecao.setEntidadeGestora(entidadeGestora);

        try{
            compradorSecao.setComprador(compradorSecaoInput.getComprador());
                compradorSecao = compradorSecaoRepo.atualizarCompradorSecao(compradorSecao);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorSecao;

    }

}



