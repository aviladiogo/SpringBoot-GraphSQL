package com.mines.Fornecedor.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Model.FornecedorFilial;
import com.mines.Fornecedor.Model.FornecedorFilialInput;
import com.mines.Fornecedor.Repository.FornecedorFilialRepository;
import com.mines.Fornecedor.Service.FornecedorFilialService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FornecedorFilialGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private FornecedorFilialRepository fornecedorFilialRepo;

    @Autowired
    private FornecedorFilialService fornecedorFilialServ;

    public FornecedorFilial fornecedorFilial(Integer fornecedor, Integer filial){

        FornecedorFilial fornecedorFilial = null;
        try {
            fornecedorFilial = fornecedorFilialServ.obterPorIdFornecedorFilial(fornecedor, filial);
        } catch (Exception e) {
            return fornecedorFilial;
        }
        return fornecedorFilial;
    }


    public List<FornecedorFilial> fornecedoresFilial(Integer entidade){

        List<FornecedorFilial> lista = fornecedorFilialServ.obterTodosFornecedoresFilial(entidade);
        return lista;

    }

    public Boolean deletarFornecedorFilial(Integer fornecedor, Integer filial){

        Boolean ret = false;

        try{
            FornecedorFilial fornecedorFilial = fornecedorFilialRepo.obterPorIdFornecedorFilial(fornecedor, filial);
            ret = fornecedorFilialRepo.deletarFornecedorFilial(fornecedorFilial);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public FornecedorFilial salvarFornecedorFilial(FornecedorFilialInput fornecedorFilialInput) throws SQLException{

        FornecedorFilial fornecedorFilial = new FornecedorFilial();

        try{
            Fornecedor fornecedor  = new Fornecedor();
            fornecedor.setId(fornecedorFilialInput.getFornecedor());
            fornecedorFilial.setFornecedor(fornecedor);

            Filial filial = new Filial();
            filial.setId(fornecedorFilialInput.getFilial());
            fornecedorFilial.setFilial(filial);

            fornecedorFilial.setPrazoEntrega(fornecedorFilialInput.getPrazoEntrega());
            fornecedorFilial.setPercentualIcms(fornecedorFilialInput.getPercentualIcms());
            fornecedorFilial.setPercentualCofins(fornecedorFilialInput.getPercentualCofins());

            PessoaFisica compradorPadrao = new PessoaFisica();
            compradorPadrao.setId(fornecedorFilialInput.getCompradorPadrao());
            fornecedorFilial.setCompradorPadrao(compradorPadrao);

            Empresa entidadeGestora = new Empresa();
            entidadeGestora.setId(fornecedorFilialInput.getEntidadeGestora());
            fornecedorFilial.setEntidadeGestora(entidadeGestora);

            fornecedorFilial = fornecedorFilialRepo.salvarFornecedorFilial(fornecedorFilial);

        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return fornecedorFilial;

    }

    public FornecedorFilial atualizarFornecedorFilial(FornecedorFilialInput fornecedorFilialInput) throws SQLException{

        FornecedorFilial fornecedorFilial = new FornecedorFilial();

        try{
            if(fornecedorFilialRepo.obterPorIdFornecedorFilial(fornecedorFilialInput.getFornecedor(), 
            fornecedorFilialInput.getFilial()) == null){
                return fornecedorFilial;
            }else{

                Fornecedor fornecedor  = new Fornecedor();
                fornecedor.setId(fornecedorFilialInput.getFornecedor());
                fornecedorFilial.setFornecedor(fornecedor);

                Filial filial = new Filial();
                filial.setId(fornecedorFilialInput.getFilial());
                fornecedorFilial.setFilial(filial);

                fornecedorFilial.setPrazoEntrega(fornecedorFilialInput.getPrazoEntrega());
                fornecedorFilial.setPercentualIcms(fornecedorFilialInput.getPercentualIcms());
                fornecedorFilial.setPercentualCofins(fornecedorFilialInput.getPercentualCofins());

                fornecedorFilial = fornecedorFilialRepo.atualizarFornecedorFilial(fornecedorFilial);

            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return fornecedorFilial;

    }

}

