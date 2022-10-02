package com.mines.TabelaPreco.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.TabelaPreco.Model.TabelaPreco;
import com.mines.TabelaPreco.Model.TabelaPrecoInput;
import com.mines.TabelaPreco.Repository.TabelaPrecoRepository;
import com.mines.TabelaPreco.Service.TabelaPrecoService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TabelaPrecoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private TabelaPrecoRepository tabelaPrecoRepo;

    @Autowired
    private TabelaPrecoService tabelaPrecoServ;

    public TabelaPreco tabelaPreco(Integer id){

        TabelaPreco tabelaPreco = null;
        try {
            tabelaPreco = tabelaPrecoServ.obterPorIdTabelaPreco(id);
        } catch (Exception e) {
            return tabelaPreco;
        }
        return tabelaPreco;
    }

    public List<TabelaPreco> tabelaPrecos(Integer entidade){

        List<TabelaPreco> lista = tabelaPrecoServ.obterTodosTabelasPreco(entidade);
        return lista;

    }

    public Boolean deletarTabelaPreco(Integer id){

        Boolean ret = false;

        try{
            TabelaPreco tabelaPreco = tabelaPrecoRepo.obterPorIdTabelaPreco(id);
            tabelaPrecoRepo.deletarTabelaPrecoFornecedor(tabelaPreco);
            ret = tabelaPrecoRepo.deletarTabelaPreco(tabelaPreco);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public TabelaPreco salvarTabelaPreco(TabelaPrecoInput tabelaPrecoInput) throws SQLException{

        TabelaPreco tabelaPreco = new TabelaPreco();

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(tabelaPrecoInput.getResponsavel());
        tabelaPreco.setResponsavel(responsavel);

        tabelaPreco.setPromocao(tabelaPrecoInput.getPromocao());
        tabelaPreco.setTitulo(tabelaPrecoInput.getTitulo());
        tabelaPreco.setInicioValidade(tabelaPrecoInput.getInicioValidade());
        tabelaPreco.setTerminoValidade(tabelaPrecoInput.getTerminoValidade());
        tabelaPreco.setAtivo(tabelaPrecoInput.getAtivo());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(tabelaPrecoInput.getEntidadeGestora());
        tabelaPreco.setEntidadeGestora(entidadeGestora);

        try{
            if(tabelaPrecoInput.getId() == 0){
                tabelaPreco = tabelaPrecoRepo.salvarTabelaPreco(tabelaPreco);

                if (tabelaPrecoInput.getFornecedores() != null) {
                    tabelaPrecoRepo.salvarTabelaPrecoFornecedor(
                        tabelaPreco.getId(), tabelaPrecoInput.getFornecedores());
                }
            }else{
                tabelaPreco.setId(tabelaPrecoInput.getId());
                tabelaPreco = tabelaPrecoRepo.atualizarTabelaPreco(tabelaPreco);
            }
        }catch(Exception e){
            deletarTabelaPreco(tabelaPreco.getId());
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return tabelaPreco;

    }

}


