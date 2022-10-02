package com.mines.Estoque.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Estoque.Model.Almoxarifado;
import com.mines.Estoque.Model.Estoque;
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
public class EstoqueRepositoryImpl implements EstoqueRepository {

    private static String SELECT_ALL = " select * from dx_estoque where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_estoque where id = ? ";
    private static String INSERT = " insert into dx_estoque (id, entidadeestoque, almoxarifado, responsavel, "
            + " produto, quantidade, girovendadia, entidadegestora) "
            + " values (nextval('dx_estoque_id_seq'),?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_estoque set quantidade = ?, girovendadia = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_estoque where id = ? ";
    
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
    
    public Estoque atualizarEstoque(Estoque estoque) {
       
        jdbcTemplate.update(UPDATE, new Object[] {estoque.getQuantidade(),estoque.getGiroVendaDia(), 
            estoque.getId()});

        return estoque;

    }

    
    public Boolean deletarEstoque(Estoque estoque) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {estoque.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Estoque obterPorIdEstoque(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Estoque>() {
            @Override
            public Estoque mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Estoque estoque = new Estoque();

                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getDouble("quantidade"));
                estoque.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoque.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoque.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoque.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoque.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoque.setEntidadeGestora(entidadeGestora);

                return estoque;
            }
        });

    }
    
    public List<Estoque> obterTodosEstoques(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Estoque>() {
            @Override
            public Estoque mapRow(ResultSet rs, int rownumber) throws SQLException {

                Estoque estoque = new Estoque();

                estoque.setId(rs.getInt("id"));
                estoque.setQuantidade(rs.getDouble("quantidade"));
                estoque.setGiroVendaDia(rs.getDouble("girovendadia"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                estoque.setEntidadeEstoque(entidadeEstoque);

                Integer almoxarifadoId = rs.getInt("almoxarifado");
                Almoxarifado almoxarifado = almoxarifadoRepo.obterPorIdAlmoxarifado(almoxarifadoId);
                estoque.setAlmoxarifado(almoxarifado);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                estoque.setResponsavel(responsavel);

                Integer produtoId = rs.getInt("produto");
                Produto produto = produtoRepo.obterPorIdProduto(produtoId);
                estoque.setProduto(produto);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                estoque.setEntidadeGestora(entidadeGestora);

                return estoque;
            }
        }, entidade);

    }

    public Estoque salvarEstoque(Estoque estoque) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, estoque.getEntidadeEstoque().getId());
                ps.setInt(2, estoque.getAlmoxarifado().getId());
                ps.setInt(3, estoque.getResponsavel().getId());
                ps.setInt(4, estoque.getProduto().getId());
                ps.setDouble(5, estoque.getQuantidade());
                ps.setDouble(6, estoque.getGiroVendaDia());
                ps.setInt(7, estoque.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        estoque.setId(id);
            
        return estoque;

    }
    
}
