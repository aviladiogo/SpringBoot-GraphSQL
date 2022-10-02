package com.mines.Estoque.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.AlmoxarifadoInput;
import com.mines.Estoque.Repository.AlmoxarifadoRepository;
import com.mines.Estoque.Service.AlmoxarifadoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlmoxarifadoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepo;

    @Autowired
    private AlmoxarifadoService almoxarifadoServ;

    public Almoxarifado almoxarifado(Integer id){

        Almoxarifado almoxarifado = null;
        try {
            almoxarifado = almoxarifadoServ.obterPorIdAlmoxarifado(id);
        } catch (Exception e) {
            return almoxarifado;
        }
        return almoxarifado;
    }

    public List<Almoxarifado> almoxarifados(Integer entidade){

        List<Almoxarifado> lista = almoxarifadoServ.obterTodosAlmoxarifados(entidade);
        return lista;

    }

    public Boolean deletarAlmoxarifado(Integer id){

        Boolean ret = false;

        try{
            Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(id);
            ret = almoxarifadoRepo.deletarAlmoxarifado(almoxarifado);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public Almoxarifado salvarAlmoxarifado(AlmoxarifadoInput almoxarifadoInput) throws SQLException{

        Almoxarifado almoxarifado = new Almoxarifado();

        Empresa entidadeEstoque = new Empresa();
        entidadeEstoque.setId(almoxarifadoInput.getEntidadeEstoque());
        almoxarifado.setEntidadeEstoque(entidadeEstoque);

        almoxarifado.setDescricao(almoxarifadoInput.getDescricao());

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(almoxarifadoInput.getResponsavel());
        almoxarifado.setResponsavel(responsavel);

        almoxarifado.setAtivo(almoxarifadoInput.getAtivo());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(almoxarifadoInput.getEntidadeGestora());
        almoxarifado.setEntidadeGestora(entidadeGestora);

        try{
            if(almoxarifadoInput.getId() == 0){
                almoxarifado = almoxarifadoRepo.salvarAlmoxarifado(almoxarifado);
            }else{
                almoxarifado.setId(almoxarifadoInput.getId());
                almoxarifado = almoxarifadoRepo.atualizarAlmoxarifado(almoxarifado);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return almoxarifado;

    }

}

