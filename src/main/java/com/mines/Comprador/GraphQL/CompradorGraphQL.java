package com.mines.Comprador.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Comprador.Model.Comprador;
import com.mines.Comprador.Model.CompradorInput;
import com.mines.Comprador.Repository.CompradorRepository;
import com.mines.Comprador.Service.CompradorService;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompradorGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private CompradorRepository compradorRepo;

    @Autowired
    private CompradorService compradorServ;

    public Comprador comprador(Integer id){

        Comprador comprador = null;
        try {
            comprador = compradorServ.obterPorIdComprador(id);
        } catch (Exception e) {
            return comprador;
        }
        return comprador;
    }

    public List<Comprador> compradores(Integer entidade){

        List<Comprador> lista = compradorServ.obterTodosCompradores(entidade);
        return lista;

    }

    public Boolean deletarComprador(Integer id){

        Boolean ret = false;

        try{
            Comprador comprador = compradorRepo.obterPorIdComprador(id);
            ret = compradorRepo.deletarComprador(comprador);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Comprador salvarComprador(CompradorInput compradorInput) throws SQLException{

        Comprador comprador = new Comprador();

        PessoaFisica pessoa = new PessoaFisica();
        pessoa.setId(compradorInput.getPessoa());
        comprador.setPessoa(pessoa);

        comprador.setLimiteCompra(compradorInput.getLimiteCompra());
        comprador.setAtivo(compradorInput.getAtivo());

        if(compradorInput.getCompradorLider()!=null){
            PessoaFisica compradorLider = new PessoaFisica();
            compradorLider.setId(compradorInput.getCompradorLider());
            comprador.setCompradorLider(compradorLider);
        }

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(compradorInput.getEntidadeGestora());
        comprador.setEntidadeGestora(entidadeGestora);

        try{
            if(compradorInput.getId() == 0){
                comprador = compradorRepo.salvarComprador(comprador);
            }else{
                comprador.setId(compradorInput.getId());
                comprador = compradorRepo.atualizarComprador(comprador);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return comprador;

    }

}

