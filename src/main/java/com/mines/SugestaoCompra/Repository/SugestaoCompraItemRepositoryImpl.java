package com.mines.SugestaoCompra.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SugestaoCompraItemRepositoryImpl implements SugestaoCompraItemRepository {

    private static String SELECT_ALL = " select * from dx_sugestaocompraitem where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_sugestaocompraitem where id = ? ";
    private static String INSERT = " insert into dx_sugestaocompraitem (id, filial, produto, curvaabcz, "
            + " estoqueatual, estoqueminimo, estoqueideal, girodia, estoquetransitocdloja, "
            + " estoquetransitoforncd, estoquetransitototal, qtdebrutaacomprar, estoquecd, multiplocompra, "
            + " qtdefinalacomprar, diasestoqueatual, diasestoqueposcompra, qtdevenda3ultmeses, "
            + " qtdevendamesatual, clientespositivados, variacaomediacp3ultmeses, qtdevendafuturames1, "
            + " qtdevendafuturames2, qtdevendafuturames3, entidadegestora)"
            + " values (nextval('dx_sugestaocompraitem_id_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_sugestaocompraitem set estoqueatual = ?, estoqueminimo = ?,"
            + " estoqueideal = ?, girodia = ?, estoquetransitocdloja = ?, estoquetransitoforncd = ?, "
            + " estoquetransitototal = ?, qtdebrutaacomprar = ?, estoquecd = ?, multiplocompra = ?, "
            + " qtdefinalacomprar = ?, diasestoqueatual = ?, diasestoqueposcompra = ?, qtdevenda3ultmeses = ?, "
            + " qtdevendamesatual = ?, clientespositivados = ?, variacaomediacp3ultmeses = ?, " 
            + " qtdevendafuturames1 = ?, qtdevendafuturames2 = ?, qtdevendafuturames3 = ? where id = ?";
    private static String DELETE = " delete from dx_sugestaocompraitem where id = ? ";
    
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
    
    public SugestaoCompraItem atualizarSugestaoCompraItem(Integer id, SugestaoCompraItem sugestaoCompraItem) {
       
        jdbcTemplate.update(UPDATE, new Object[] {sugestaoCompraItem.getEstoqueAtual(), 
            sugestaoCompraItem.getEstoqueMinimo(), sugestaoCompraItem.getEstoqueIdeal(), 
            sugestaoCompraItem.getGiroDia(), sugestaoCompraItem.getEstoqueTransitoCDLoja(), 
            sugestaoCompraItem.getEstoqueTransitoFornCD(), sugestaoCompraItem.getEstoqueTransitoTotal(), 
            sugestaoCompraItem.getQtdeBrutaAComprar(), sugestaoCompraItem.getEstoqueCD(), 
            sugestaoCompraItem.getMultiploCompra(), sugestaoCompraItem.getQtdeFinalAComprar(), 
            sugestaoCompraItem.getDiasEstoqueAtual(), sugestaoCompraItem.getDiasEstoquePosCompra(), 
            sugestaoCompraItem.getQtdeVenda3ULTMeses(), sugestaoCompraItem.getQtdeVendaMesAtual(), 
            sugestaoCompraItem.getClientesPositivados(), sugestaoCompraItem.getVariacaoMediaCP3ULTMeses(), 
            sugestaoCompraItem.getQtdeVendaFuturaMes1(), sugestaoCompraItem.getQtdeVendaFuturaMes2(), 
            sugestaoCompraItem.getQtdeVendaFuturaMes3(), id});

        return sugestaoCompraItem;
    }

    public Boolean deletarSugestaoCompraItem(Integer id) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {id});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public SugestaoCompraItem salvarSugestaoCompraItem(SugestaoCompraItem sugestaoCompraItem) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, sugestaoCompraItem.getFilial().getId());
                ps.setInt(2, sugestaoCompraItem.getProduto().getId());
                ps.setInt(3, sugestaoCompraItem.getCurvaABCZ().getId());
                ps.setInt(4, sugestaoCompraItem.getEstoqueAtual());
                ps.setInt(5, sugestaoCompraItem.getEstoqueMinimo());
                ps.setInt(6, sugestaoCompraItem.getEstoqueIdeal());
                ps.setDouble(7, sugestaoCompraItem.getGiroDia());
                ps.setInt(8, sugestaoCompraItem.getEstoqueTransitoCDLoja());
                ps.setInt(9, sugestaoCompraItem.getEstoqueTransitoFornCD());
                ps.setInt(10, sugestaoCompraItem.getEstoqueTransitoTotal());
                ps.setInt(11, sugestaoCompraItem.getQtdeBrutaAComprar());
                ps.setInt(12, sugestaoCompraItem.getEstoqueCD());
                ps.setInt(13, sugestaoCompraItem.getMultiploCompra());
                ps.setInt(14, sugestaoCompraItem.getQtdeFinalAComprar());
                ps.setInt(15, sugestaoCompraItem.getDiasEstoqueAtual());
                ps.setInt(16, sugestaoCompraItem.getDiasEstoquePosCompra());
                ps.setInt(17, sugestaoCompraItem.getQtdeVenda3ULTMeses());
                ps.setInt(18, sugestaoCompraItem.getQtdeVendaMesAtual());
                ps.setInt(19, sugestaoCompraItem.getClientesPositivados());
                ps.setInt(20, sugestaoCompraItem.getVariacaoMediaCP3ULTMeses());
                ps.setInt(21, sugestaoCompraItem.getQtdeVendaFuturaMes1());
                ps.setInt(22, sugestaoCompraItem.getQtdeVendaFuturaMes2());
                ps.setInt(23, sugestaoCompraItem.getQtdeVendaFuturaMes3());
                ps.setInt(24, sugestaoCompraItem.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);
            
        return sugestaoCompraItem;

    }
    
}

