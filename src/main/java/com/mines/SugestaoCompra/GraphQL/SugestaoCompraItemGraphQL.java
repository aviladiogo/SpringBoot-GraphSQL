package com.mines.SugestaoCompra.GraphQL;

import java.sql.SQLException;
import java.util.List;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Produto.Model.Produto;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;
import com.mines.SugestaoCompra.Model.SugestaoCompraItemInput;
import com.mines.SugestaoCompra.Repository.SugestaoCompraItemRepository;
import com.mines.SugestaoCompra.Service.SugestaoCompraItemService;
import com.mines.util.exceptions.DomainException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SugestaoCompraItemGraphQL implements GraphQLQueryResolver, GraphQLMutationResolver{
    
    @Autowired
    private SugestaoCompraItemRepository sugestaoCompraItemRepo;

    @Autowired
    private SugestaoCompraItemService sugestaoCompraItemServ;

    public SugestaoCompraItem sugestaoCompraItem(Integer id){

        SugestaoCompraItem sugestaoCompraItem = null;
        try {
            sugestaoCompraItem = sugestaoCompraItemServ.obterPorIdSugestaoCompraItem(id);
        } catch (Exception e) {
            return sugestaoCompraItem;
        }
        return sugestaoCompraItem;
    }

    public List<SugestaoCompraItem> sugestoesComprasItens(Integer entidade){

        List<SugestaoCompraItem> lista = sugestaoCompraItemServ.obterTodosSugestoesCompraItem(entidade);
        return lista;
    }

    public Boolean deletarSugestaoCompraItem(Integer id){

        Boolean ret = false;

        try{
            if(sugestaoCompraItemRepo.obterPorIdSugestaoCompraItem(id) != null){
                ret = sugestaoCompraItemRepo.deletarSugestaoCompraItem(id);
            }
        }catch(Exception e){
            return ret;
        }
        return ret;    
    }
    
    public SugestaoCompraItem salvarSugestaoCompraItem(SugestaoCompraItemInput sugestaoCompraItemInput) throws SQLException{

        SugestaoCompraItem sugestaoCompraItem = new SugestaoCompraItem();

        try{
            Filial filial = new Filial();
            filial.setId(sugestaoCompraItemInput.getFilial());
            sugestaoCompraItem.setFilial(filial);

            Produto produto = new Produto();
            produto.setId(sugestaoCompraItemInput.getFilial());
            sugestaoCompraItem.setProduto(produto);

            CurvaAbcz curvaAbcz = new CurvaAbcz();
            curvaAbcz.setId(sugestaoCompraItemInput.getCurvaABCZ());
            sugestaoCompraItem.setCurvaABCZ(curvaAbcz);

            sugestaoCompraItem.setEstoqueAtual(sugestaoCompraItemInput.getEstoqueAtual());
            sugestaoCompraItem.setEstoqueMinimo(sugestaoCompraItemInput.getEstoqueMinimo());
            sugestaoCompraItem.setEstoqueIdeal(sugestaoCompraItemInput.getEstoqueIdeal());
            sugestaoCompraItem.setGiroDia(sugestaoCompraItemInput.getGiroDia());
            sugestaoCompraItem.setEstoqueTransitoCDLoja(sugestaoCompraItemInput.getEstoqueTransitoCDLoja());
            sugestaoCompraItem.setEstoqueTransitoFornCD(sugestaoCompraItemInput.getEstoqueTransitoFornCD());
            sugestaoCompraItem.setEstoqueTransitoTotal(sugestaoCompraItemInput.getEstoqueTransitoTotal());
            sugestaoCompraItem.setQtdeBrutaAComprar(sugestaoCompraItemInput.getQtdeBrutaAComprar());
            sugestaoCompraItem.setEstoqueCD(sugestaoCompraItemInput.getEstoqueCD());
            sugestaoCompraItem.setMultiploCompra(sugestaoCompraItemInput.getMultiploCompra());
            sugestaoCompraItem.setQtdeFinalAComprar(sugestaoCompraItemInput.getQtdeFinalAComprar());
            sugestaoCompraItem.setDiasEstoqueAtual(sugestaoCompraItemInput.getDiasEstoqueAtual());
            sugestaoCompraItem.setDiasEstoquePosCompra(sugestaoCompraItemInput.getDiasEstoquePosCompra());
            sugestaoCompraItem.setQtdeVenda3ULTMeses(sugestaoCompraItemInput.getQtdeVenda3ULTMeses());
            sugestaoCompraItem.setQtdeVendaMesAtual(sugestaoCompraItemInput.getQtdeVendaMesAtual());
            sugestaoCompraItem.setClientesPositivados(sugestaoCompraItemInput.getClientesPositivados());
            sugestaoCompraItem.setVariacaoMediaCP3ULTMeses(sugestaoCompraItemInput.getVariacaoMediaCP3ULTMeses());
            sugestaoCompraItem.setQtdeVendaFuturaMes1(sugestaoCompraItemInput.getQtdeVendaFuturaMes1());
            sugestaoCompraItem.setQtdeVendaFuturaMes2(sugestaoCompraItemInput.getQtdeVendaFuturaMes2());
            sugestaoCompraItem.setQtdeVendaFuturaMes3(sugestaoCompraItemInput.getQtdeVendaFuturaMes3());

            Empresa entidadeGestora = new Empresa();
            entidadeGestora.setId(sugestaoCompraItemInput.getEntidadeGestora());
            sugestaoCompraItem.setEntidadeGestora(entidadeGestora);

            sugestaoCompraItem = sugestaoCompraItemRepo.salvarSugestaoCompraItem(sugestaoCompraItem);

        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return sugestaoCompraItem;

    }

    public SugestaoCompraItem atualizarSugestaoCompraItem(Integer id, SugestaoCompraItemInput sugestaoCompraItemInput) throws SQLException{

        SugestaoCompraItem sugestaoCompraItem = new SugestaoCompraItem();

        try{
            if(sugestaoCompraItemRepo.obterPorIdSugestaoCompraItem(id) == null){
                return sugestaoCompraItem;
            }else{

                sugestaoCompraItem.setEstoqueAtual(sugestaoCompraItemInput.getEstoqueAtual());
                sugestaoCompraItem.setEstoqueMinimo(sugestaoCompraItemInput.getEstoqueMinimo());
                sugestaoCompraItem.setEstoqueIdeal(sugestaoCompraItemInput.getEstoqueIdeal());
                sugestaoCompraItem.setGiroDia(sugestaoCompraItemInput.getGiroDia());
                sugestaoCompraItem.setEstoqueTransitoCDLoja(sugestaoCompraItemInput.getEstoqueTransitoCDLoja());
                sugestaoCompraItem.setEstoqueTransitoFornCD(sugestaoCompraItemInput.getEstoqueTransitoFornCD());
                sugestaoCompraItem.setEstoqueTransitoTotal(sugestaoCompraItemInput.getEstoqueTransitoTotal());
                sugestaoCompraItem.setQtdeBrutaAComprar(sugestaoCompraItemInput.getQtdeBrutaAComprar());
                sugestaoCompraItem.setEstoqueCD(sugestaoCompraItemInput.getEstoqueCD());
                sugestaoCompraItem.setMultiploCompra(sugestaoCompraItemInput.getMultiploCompra());
                sugestaoCompraItem.setQtdeFinalAComprar(sugestaoCompraItemInput.getQtdeFinalAComprar());
                sugestaoCompraItem.setDiasEstoqueAtual(sugestaoCompraItemInput.getDiasEstoqueAtual());
                sugestaoCompraItem.setDiasEstoquePosCompra(sugestaoCompraItemInput.getDiasEstoquePosCompra());
                sugestaoCompraItem.setQtdeVenda3ULTMeses(sugestaoCompraItemInput.getQtdeVenda3ULTMeses());
                sugestaoCompraItem.setQtdeVendaMesAtual(sugestaoCompraItemInput.getQtdeVendaMesAtual());
                sugestaoCompraItem.setClientesPositivados(sugestaoCompraItemInput.getClientesPositivados());
                sugestaoCompraItem.setVariacaoMediaCP3ULTMeses(sugestaoCompraItemInput.getVariacaoMediaCP3ULTMeses());
                sugestaoCompraItem.setQtdeVendaFuturaMes1(sugestaoCompraItemInput.getQtdeVendaFuturaMes1());
                sugestaoCompraItem.setQtdeVendaFuturaMes2(sugestaoCompraItemInput.getQtdeVendaFuturaMes2());
                sugestaoCompraItem.setQtdeVendaFuturaMes3(sugestaoCompraItemInput.getQtdeVendaFuturaMes3());

                sugestaoCompraItem = sugestaoCompraItemRepo.atualizarSugestaoCompraItem(id, sugestaoCompraItem);
            }
        }catch(Exception e){
            throw new DomainException("Erro base de dados: " + e.getMessage());
        }

        return sugestaoCompraItem;

    }

}


