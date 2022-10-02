package com.mines.EntidadeJuridica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Model.PessoaJuridicaInput;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import com.mines.EntidadeJuridica.Service.PessoaJuridicaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaJuridicaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaServ;

    public PessoaJuridica pessoaJuridica(Integer id){

        PessoaJuridica pessoaJuridica = null;
        try {
            pessoaJuridica = pessoaJuridicaServ.obterPorIdPessoaJuridica(id);
        } catch (Exception e) {
            return pessoaJuridica;
        }
        return pessoaJuridica;
    }

    public List<PessoaJuridica> pessoasJuridicas(Integer entidade){

        List<PessoaJuridica> lista = pessoaJuridicaServ.obterTodosPessoasJuridica(entidade);
        return lista;

    }

    public Boolean deletarPessoaJuridica(Integer id){

        Boolean ret = false;

        try{
            PessoaJuridica pessoaJuridica = pessoaJuridicaRepo.obterPorIdPessoaJuridica(id);
            ret = pessoaJuridicaRepo.deletarPessoaJuridica(pessoaJuridica);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridicaInput pessoaJuridicaInput) throws SQLException{

        PessoaJuridica pessoaJuridica = new PessoaJuridica();

        pessoaJuridica.setNomeFantasia(pessoaJuridicaInput.getNomeFantasia());
        pessoaJuridica.setRazaoSocial(pessoaJuridicaInput.getRazaoSocial());
        pessoaJuridica.setCnpj(pessoaJuridicaInput.getCnpj());

        AtividadeComercial atividadeComercial = new AtividadeComercial();
        atividadeComercial.setId(pessoaJuridicaInput.getAtividadeComercial());
        pessoaJuridica.setAtividadeComercial(atividadeComercial);

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(pessoaJuridicaInput.getEntidadeGestora());
        pessoaJuridica.setEntidadeGestora(entidadeGestora);

        try{
            if(pessoaJuridicaInput.getId() == 0){
                pessoaJuridica = pessoaJuridicaRepo.salvarPessoaJuridica(pessoaJuridica);
            }else{
                pessoaJuridica.setId(pessoaJuridicaInput.getId());
                pessoaJuridica = pessoaJuridicaRepo.atualizarPessoaJuridica(pessoaJuridica);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return pessoaJuridica;

    }
}

