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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AlmoxarifadoRepositoryImpl implements AlmoxarifadoRepository {

    private static String SELECT_ALL = " select * from dx_almoxarifado where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_almoxarifado where id = ? ";
    private static String INSERT = " insert into dx_almoxarifado (id, entidadeestoque, descricao, responsavel, "
            + " ativo, entidadegestora) "
            + " values (nextval('dx_almoxarifado_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_almoxarifado set descricao = ?, ativo = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_almoxarifado where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Almoxarifado atualizarAlmoxarifado(Almoxarifado almoxarifado) {
       
        jdbcTemplate.update(UPDATE, new Object[] {almoxarifado.getDescricao(),almoxarifado.getAtivo(), 
            almoxarifado.getId()});

        return almoxarifado;

    }

    
    public Boolean deletarAlmoxarifado(Almoxarifado almoxarifado) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {almoxarifado.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Almoxarifado obterPorIdAlmoxarifado(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Almoxarifado>() {
            @Override
            public Almoxarifado mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Almoxarifado almoxarifado = new Almoxarifado();

                almoxarifado.setId(rs.getInt("id"));
                almoxarifado.setDescricao(rs.getString("descricao"));
                almoxarifado.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                almoxarifado.setEntidadeEstoque(entidadeEstoque);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                almoxarifado.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                almoxarifado.setEntidadeGestora(entidadeGestora);

                return almoxarifado;
            }
        });

    }
    
    public List<Almoxarifado> obterTodosAlmoxarifados(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Almoxarifado>() {
            @Override
            public Almoxarifado mapRow(ResultSet rs, int rownumber) throws SQLException {

                Almoxarifado almoxarifado = new Almoxarifado();

                almoxarifado.setId(rs.getInt("id"));
                almoxarifado.setDescricao(rs.getString("descricao"));
                almoxarifado.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                almoxarifado.setEntidadeEstoque(entidadeEstoque);

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                almoxarifado.setResponsavel(responsavel);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                almoxarifado.setEntidadeGestora(entidadeGestora);

                return almoxarifado;
            }
        }, entidade);

    }

    public Almoxarifado salvarAlmoxarifado(Almoxarifado almoxarifado) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, almoxarifado.getEntidadeEstoque().getId());
                ps.setString(2, almoxarifado.getDescricao());
                ps.setInt(3, almoxarifado.getResponsavel().getId());
                ps.setBoolean(4, almoxarifado.getAtivo());
                ps.setInt(5, almoxarifado.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        almoxarifado.setId(id);
            
        return almoxarifado;

    }
    
}