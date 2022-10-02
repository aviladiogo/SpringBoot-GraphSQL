package com.mines.Estoque.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.EstoqueHistorico;
import com.mines.Produto.Model.Produto;
import com.mines.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EstoqueHistoricoRepositoryImpl implements EstoqueHistoricoRepository {

    private static String SELECT_ALL = " select * from dx_estoquehistorico where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_estoquehistorico where id = ? ";
    private static String INSERT = " insert into dx_estoquehistorico (id, dataestoque, entidadeestoque, "
            + " almoxarifado, responsavel, produto, quantidade, girovendadia, entidadegestora) "
            + " values (nextval('dx_estoquehistorico_id_seq'),?,?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_estoquehistorico set quantidade = ?, girovendadia = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_estoquehistorico where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private AlmoxarifadoRepository almoxarifadoRepo;

    @Autowired
    private ProdutoRepository produtoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public EstoqueHistorico atualizarEstoqueHistorico(EstoqueHistorico estoqueHistorico) {
       
        jdbcTemplate.update(UPDATE, new Object[] {estoqueHistorico.getQuantidade(),
            estoqueHistorico.getGiroVendaDia(), estoqueHistorico.getId()});

        return estoqueHistorico;

    }

    
    public Boolean deletarEstoqueHistorico(EstoqueHistorico estoqueHistorico) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {estoqueHistorico.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public EstoqueHistorico obterPorIdEstoqueHistorico(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<EstoqueHistorico>() {
            @Override
            public EstoqueHistorico mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                EstoqueHistorico estoqueHistorico = new EstoqueHistorico();

                estoqueHistorico.setId(rs.getInt("id"));
                estoqueHistorico.setDataEstoque(rs.getDate("dataestoque"));
                estoqueHistorico.setQuantidade(rs.getDouble("quantidade"));
                estoqueHistorico.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoqueHistorico.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoqueHistorico.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoqueHistorico.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoqueHistorico.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoqueHistorico.setEntidadeGestora(entidadeGestora);

                return estoqueHistorico;
            }
        });

    }
    
    public List<EstoqueHistorico> obterTodosEstoquesHistorico(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<EstoqueHistorico>() {
            @Override
            public EstoqueHistorico mapRow(ResultSet rs, int rownumber) throws SQLException {

                EstoqueHistorico estoqueHistorico = new EstoqueHistorico();

                estoqueHistorico.setId(rs.getInt("id"));
                estoqueHistorico.setDataEstoque(rs.getDate("dataestoque"));
                estoqueHistorico.setQuantidade(rs.getDouble("quantidade"));
                estoqueHistorico.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoqueHistorico.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoqueHistorico.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoqueHistorico.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoqueHistorico.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoqueHistorico.setEntidadeGestora(entidadeGestora);

                return estoqueHistorico;
            }
        }, entidade);

    }

    public EstoqueHistorico salvarEstoqueHistorico(EstoqueHistorico estoqueHistorico) {

        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setDate(1, registroSQL);
                ps.setInt(2, estoqueHistorico.getEntidadeEstoque().getId());
                ps.setInt(3, estoqueHistorico.getAlmoxarifado().getId());
                ps.setInt(4, estoqueHistorico.getResponsavel().getId());
                ps.setInt(5, estoqueHistorico.getProduto().getId());
                ps.setDouble(6, estoqueHistorico.getQuantidade());
                ps.setDouble(7, estoqueHistorico.getGiroVendaDia());
                ps.setInt(8, estoqueHistorico.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        estoqueHistorico.setId(id);
            
        return estoqueHistorico;

    }
    
}

