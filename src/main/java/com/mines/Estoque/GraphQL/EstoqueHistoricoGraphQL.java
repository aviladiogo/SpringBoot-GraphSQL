package com.mines.Estoque.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.EstoqueHistorico;
import com.mines.Estoque.Model.EstoqueHistoricoInput;
import com.mines.Estoque.Repository.EstoqueHistoricoRepository;
import com.mines.Estoque.Service.EstoqueHistoricoService;
import com.mines.Produto.Model.Produto;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueHistoricoGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private EstoqueHistoricoRepository estoqueHistoricoRepo;

    @Autowired
    private EstoqueHistoricoService estoqueHistoricoServ;

    public EstoqueHistorico estoqueHistorico(Integer id){

        EstoqueHistorico estoqueHistorico = null;
        try {
            estoqueHistorico = estoqueHistoricoServ.obterPorIdEstoqueHistorico(id);
        } catch (Exception e) {
            return estoqueHistorico;
        }
        return estoqueHistorico;
    }

    public List<EstoqueHistorico> estoquesHistorico(Integer entidade){

        List<EstoqueHistorico> lista = estoqueHistoricoServ.obterTodosEstoquesHistorico(entidade);
        return lista;

    }

    public Boolean deletarEstoqueHistorico(Integer id){

        Boolean ret = false;

        try{
            EstoqueHistorico estoqueHistorico = estoqueHistoricoRepo.obterPorIdEstoqueHistorico(id);
            ret = estoqueHistoricoRepo.deletarEstoqueHistorico(estoqueHistorico);
        }catch(Exception e){
            return ret;
        }

        return ret;
        
    }   

    public EstoqueHistorico salvarEstoqueHistorico(EstoqueHistoricoInput EstoqueHistoricoInput) throws SQLException{

        EstoqueHistorico estoqueHistorico = new EstoqueHistorico();

        Empresa entidadeEstoque = new Empresa();
        entidadeEstoque.setId(EstoqueHistoricoInput.getEntidadeEstoque());
        estoqueHistorico.setEntidadeEstoque(entidadeEstoque);

        Almoxarifado almoxarifado = new Almoxarifado();
        almoxarifado.setId(EstoqueHistoricoInput.getAlmoxarifado());
        estoqueHistorico.setAlmoxarifado(almoxarifado);

        PessoaFisica responsavel = new PessoaFisica();
        responsavel.setId(EstoqueHistoricoInput.getResponsavel());
        estoqueHistorico.setResponsavel(responsavel);

        Produto produto = new Produto();
        produto.setId(EstoqueHistoricoInput.getProduto());
        estoqueHistorico.setProduto(produto);

        estoqueHistorico.setQuantidade(EstoqueHistoricoInput.getQuantidade());
        estoqueHistorico.setGiroVendaDia(EstoqueHistoricoInput.getGiroVendaDia());

        Empresa entidadeGestora = new Empresa();
        entidadeGestora.setId(EstoqueHistoricoInput.getEntidadeGestora());
        estoqueHistorico.setEntidadeGestora(entidadeGestora);

        try{
            if(EstoqueHistoricoInput.getId() == 0){
                estoqueHistorico = estoqueHistoricoRepo.salvarEstoqueHistorico(estoqueHistorico);
            }else{
                estoqueHistorico.setId(EstoqueHistoricoInput.getId());
                estoqueHistorico = estoqueHistoricoRepo.atualizarEstoqueHistorico(estoqueHistorico);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return estoqueHistorico;

    }

}


