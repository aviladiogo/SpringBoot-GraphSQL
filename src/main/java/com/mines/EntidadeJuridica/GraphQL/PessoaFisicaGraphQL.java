package com.mines.EntidadeJuridica.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaFisicaInput;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Service.PessoaFisicaService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaFisicaGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaFisicaService pessoaFisicaServ;

    public PessoaFisica pessoaFisica(Integer id){

        PessoaFisica pessoaFisica = null;
        try {
            pessoaFisica = pessoaFisicaServ.obterPorIdPessoaFisica(id);
        } catch (Exception e) {
            return pessoaFisica;
        }
        return pessoaFisica;
    }

    public List<PessoaFisica> pessoasFisicas(Integer entidade){

        List<PessoaFisica> lista = pessoaFisicaServ.obterTodosPessoasFisica(entidade);
        return lista;

    }

    public Boolean deletarPessoaFisica(Integer id){

        Boolean ret = false;

        try{
            PessoaFisica pessoaFisica = pessoaFisicaRepo.obterPorIdPessoaFisica(id);
            ret = pessoaFisicaRepo.deletarPessoaFisica(pessoaFisica);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public PessoaFisica salvarPessoaFisica(PessoaFisicaInput pessoaFisicaInput) throws SQLException{

        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome(pessoaFisicaInput.getNome());
        pessoaFisica.setApelido(pessoaFisicaInput.getApelido());
        pessoaFisica.setCpf(pessoaFisicaInput.getCpf());
        pessoaFisica.setEmail(pessoaFisicaInput.getEmail());
    
        Empresa entidade = new Empresa();
        entidade.setId(pessoaFisicaInput.getEntidade());
        pessoaFisica.setEntidade(entidade);

        try{
            if(pessoaFisicaInput.getId() == 0){
                pessoaFisica = pessoaFisicaRepo.salvarPessoaFisica(pessoaFisica);
            }else{
                pessoaFisica.setId(pessoaFisicaInput.getId());
                pessoaFisica = pessoaFisicaRepo.atualizarPessoaFisica(pessoaFisica);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return pessoaFisica;

    }
}
