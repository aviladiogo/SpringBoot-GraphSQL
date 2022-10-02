package com.mines.Comprador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.Comprador.Model.CompradorDepartamento;
import com.mines.Comprador.Model.CompradorDepartamentoInput;
import com.mines.Comprador.Repository.CompradorDepartamentoRepository;
import com.mines.Comprador.Service.CompradorDepartamentoService;
import com.mines.Empresa.Model.Empresa;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompradorDepartamentoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CompradorDepartamentoRepository compradorDepartamentoRepo;

    @Autowired
    private CompradorDepartamentoService compradorDepartamentoServ;

    public CompradorDepartamento compradorDepartamento(Integer id){

        CompradorDepartamento compradorDepartamento = null;
        try {
            compradorDepartamento = compradorDepartamentoServ.obterPorIdCompradorDepartamento(id);
        } catch (Exception e) {
            return compradorDepartamento;
        }
        return compradorDepartamento;
    }

    public List<CompradorDepartamento> compradoresDepartamento(Integer entidade){

        List<CompradorDepartamento> lista = compradorDepartamentoServ.obterTodosCompradoresDepartamento(entidade);
        return lista;

    }

    public Boolean deletarCompradorDepartamento(Integer id){

        Boolean ret = false;
        
        try{
            CompradorDepartamento compradorDepartamento = compradorDepartamentoRepo.obterPorIdCompradorDepartamento(id);
            ret = compradorDepartamentoRepo.deletarCompradorDepartamento(compradorDepartamento);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public CompradorDepartamento salvarCompradorDepartamento(CompradorDepartamentoInput compradorDepartamentoInput) throws SQLException{

        CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

        compradorDepartamento.setLimiteCompra(compradorDepartamentoInput.getLimiteCompra());
        compradorDepartamento.setAtivo(compradorDepartamentoInput.getAtivo());

        Departamento departamento = new Departamento();
        departamento.setId(compradorDepartamentoInput.getDepartamento());
        compradorDepartamento.setDepartamento(departamento);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorDepartamentoInput.getEntidadeGestora());
        compradorDepartamento.setEntidadeGestora(entidadeGestora);

        try{
            compradorDepartamento.setComprador(compradorDepartamentoInput.getComprador());
                compradorDepartamento = compradorDepartamentoRepo.salvarCompradorDepartamento(compradorDepartamento);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorDepartamento;

    }

    public CompradorDepartamento atualizarCompradorDepartamento(CompradorDepartamentoInput compradorDepartamentoInput) throws SQLException{

        CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

        compradorDepartamento.setLimiteCompra(compradorDepartamentoInput.getLimiteCompra());
        compradorDepartamento.setAtivo(compradorDepartamentoInput.getAtivo());

        Departamento departamento = new Departamento();
        departamento.setId(compradorDepartamentoInput.getDepartamento());
        compradorDepartamento.setDepartamento(departamento);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorDepartamentoInput.getEntidadeGestora());
        compradorDepartamento.setEntidadeGestora(entidadeGestora);

        try{
            compradorDepartamento.setComprador(compradorDepartamentoInput.getComprador());
                compradorDepartamento = compradorDepartamentoRepo.atualizarCompradorDepartamento(compradorDepartamento);
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return compradorDepartamento;

    }

}



