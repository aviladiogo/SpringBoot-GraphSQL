package com.mines.SugestaoCompra.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Repository.CurvaAbczRepository;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Empresa.Repository.FilialRepository;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import com.mines.SugestaoCompra.Model.SugestaoCompraItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoCompraItemRepositoryImplGCF implements SugestaoCompraItemRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FilialRepository filialRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private CurvaAbczRepository curvaAbczRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public SugestaoCompraItem obterPorIdSugestaoCompraItem(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<SugestaoCompraItem>() {
            @Override
            public SugestaoCompraItem mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                SugestaoCompraItem sugestaoCompraItem = new SugestaoCompraItem();

                Integer filialId = rs.getInt("filial");
                Filial filial = filialRepo.obterPorIdFilial(filialId);
                sugestaoCompraItem.setFilial(filial);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                sugestaoCompraItem.setProduto(produto);

                Integer curvaAbczId = rs.getInt("curvaabcz");
                CurvaAbcz curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(curvaAbczId);
                sugestaoCompraItem.setCurvaABCZ(curvaAbcz);

                sugestaoCompraItem.setEstoqueAtual(rs.getInt("estoqueatual"));
                sugestaoCompraItem.setEstoqueMinimo(rs.getInt("estoqueminimo"));
                sugestaoCompraItem.setEstoqueIdeal(rs.getInt("estoqueideal"));
                sugestaoCompraItem.setGiroDia(rs.getDouble("girodia"));
                sugestaoCompraItem.setEstoqueTransitoCDLoja(rs.getInt("estoquetransitocdloja"));
                sugestaoCompraItem.setEstoqueTransitoFornCD(rs.getInt("estoquetransitoforncd"));
                sugestaoCompraItem.setEstoqueTransitoTotal(rs.getInt("estoquetransitototal"));
                sugestaoCompraItem.setQtdeBrutaAComprar(rs.getInt("qtdebrutaacomprar"));
                sugestaoCompraItem.setEstoqueCD(rs.getInt("estoquecd"));
                sugestaoCompraItem.setMultiploCompra(rs.getInt("multiplocompra"));
                sugestaoCompraItem.setQtdeFinalAComprar(rs.getInt("qtdefinalacomprar"));
                sugestaoCompraItem.setDiasEstoqueAtual(rs.getInt("diasestoqueatual"));
                sugestaoCompraItem.setDiasEstoquePosCompra(rs.getInt("diasestoqueposcompra"));
                sugestaoCompraItem.setQtdeVenda3ULTMeses(rs.getInt("qtdevenda3ultmeses"));
                sugestaoCompraItem.setQtdeVendaMesAtual(rs.getInt("qtdevendamesatual"));
                sugestaoCompraItem.setClientesPositivados(rs.getInt("clientespositivados"));
                sugestaoCompraItem.setVariacaoMediaCP3ULTMeses(rs.getInt("variacaomediacp3ultmeses"));
                sugestaoCompraItem.setQtdeVendaFuturaMes1(rs.getInt("qtdevendafuturames1"));
                sugestaoCompraItem.setQtdeVendaFuturaMes2(rs.getInt("qtdevendafuturames2"));
                sugestaoCompraItem.setQtdeVendaFuturaMes3(rs.getInt("qtdevendafuturames3"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sugestaoCompraItem.setEntidadeGestora(entidadeGestora);
                    
                return sugestaoCompraItem;
            }
        });

    }

    public List<SugestaoCompraItem> obterTodosSugestoesCompraItem(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<SugestaoCompraItem>() {
            @Override
            public SugestaoCompraItem mapRow(ResultSet rs, int rownumber) throws SQLException {

                SugestaoCompraItem sugestaoCompraItem = new SugestaoCompraItem();

                Integer filialId = rs.getInt("filial");
                Filial filial = filialRepo.obterPorIdFilial(filialId);
                sugestaoCompraItem.setFilial(filial);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                sugestaoCompraItem.setProduto(produto);

                Integer curvaAbczId = rs.getInt("curvaabcz");
                CurvaAbcz curvaAbcz = curvaAbczRepo.obterPorIdCurvaABCZ(curvaAbczId);
                sugestaoCompraItem.setCurvaABCZ(curvaAbcz);

                sugestaoCompraItem.setEstoqueAtual(rs.getInt("estoqueatual"));
                sugestaoCompraItem.setEstoqueMinimo(rs.getInt("estoqueminimo"));
                sugestaoCompraItem.setEstoqueIdeal(rs.getInt("estoqueideal"));
                sugestaoCompraItem.setGiroDia(rs.getDouble("girodia"));
                sugestaoCompraItem.setEstoqueTransitoCDLoja(rs.getInt("estoquetransitocdloja"));
                sugestaoCompraItem.setEstoqueTransitoFornCD(rs.getInt("estoquetransitoforncd"));
                sugestaoCompraItem.setEstoqueTransitoTotal(rs.getInt("estoquetransitototal"));
                sugestaoCompraItem.setQtdeBrutaAComprar(rs.getInt("qtdebrutaacomprar"));
                sugestaoCompraItem.setEstoqueCD(rs.getInt("estoquecd"));
                sugestaoCompraItem.setMultiploCompra(rs.getInt("multiplocompra"));
                sugestaoCompraItem.setQtdeFinalAComprar(rs.getInt("qtdefinalacomprar"));
                sugestaoCompraItem.setDiasEstoqueAtual(rs.getInt("diasestoqueatual"));
                sugestaoCompraItem.setDiasEstoquePosCompra(rs.getInt("diasestoqueposcompra"));
                sugestaoCompraItem.setQtdeVenda3ULTMeses(rs.getInt("qtdevenda3ultmeses"));
                sugestaoCompraItem.setQtdeVendaMesAtual(rs.getInt("qtdevendamesatual"));
                sugestaoCompraItem.setClientesPositivados(rs.getInt("clientespositivados"));
                sugestaoCompraItem.setVariacaoMediaCP3ULTMeses(rs.getInt("variacaomediacp3ultmeses"));
                sugestaoCompraItem.setQtdeVendaFuturaMes1(rs.getInt("qtdevendafuturames1"));
                sugestaoCompraItem.setQtdeVendaFuturaMes2(rs.getInt("qtdevendafuturames2"));
                sugestaoCompraItem.setQtdeVendaFuturaMes3(rs.getInt("qtdevendafuturames3"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sugestaoCompraItem.setEntidadeGestora(entidadeGestora);
                    
                return sugestaoCompraItem;
            }
        }, entidade);

    }
    
}

