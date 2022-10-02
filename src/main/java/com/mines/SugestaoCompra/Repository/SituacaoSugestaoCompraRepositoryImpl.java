package com.mines.SugestaoCompra.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.SugestaoCompra.Model.SituacaoSugestaoCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class SituacaoSugestaoCompraRepositoryImpl implements SituacaoSugestaoCompraRepository {

    private static String SELECT_ALL = " select * from dx_situacaosugestaocompra where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_situacaosugestaocompra where id = ? ";
    private static String INSERT = " insert into dx_situacaosugestaocompra (id, descricao, permiteeditar, "
            + " entidadegestora) values (nextval('dx_situacaosugestaocompra_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_situacaosugestaocompra set descricao = ?, permiteeditar = ?"
            + " where id = ? ";
    private static String DELETE = " delete from dx_situacaosugestaocompra where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public SituacaoSugestaoCompra atualizarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra) {
       
        jdbcTemplate.update(UPDATE, new Object[] {situacaoSugestaoCompra.getDescricao(), 
            situacaoSugestaoCompra.getPermiteEditar(), situacaoSugestaoCompra.getId()});

        return situacaoSugestaoCompra;
    }

    public Boolean deletarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {situacaoSugestaoCompra.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }


    public SituacaoSugestaoCompra obterPorIdSituacaoSugestaoCompra(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<SituacaoSugestaoCompra>() {
            @Override
            public SituacaoSugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                SituacaoSugestaoCompra situacaoSugestaoCompra = new SituacaoSugestaoCompra();

                situacaoSugestaoCompra.setId(rs.getInt("id"));
                situacaoSugestaoCompra.setDescricao(rs.getString("descricao"));
                situacaoSugestaoCompra.setPermiteEditar(rs.getInt("permiteeditar"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                situacaoSugestaoCompra.setEntidadeGestora(entidadeGestora);

                return situacaoSugestaoCompra;
            }
        });

    }
    
    public List<SituacaoSugestaoCompra> obterTodosSituacoesSugestaoCompras(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<SituacaoSugestaoCompra>() {
            @Override
            public SituacaoSugestaoCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                SituacaoSugestaoCompra situacaoSugestaoCompra = new SituacaoSugestaoCompra();

                situacaoSugestaoCompra.setId(rs.getInt("id"));
                situacaoSugestaoCompra.setDescricao(rs.getString("descricao"));
                situacaoSugestaoCompra.setPermiteEditar(rs.getInt("permiteeditar"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                situacaoSugestaoCompra.setEntidadeGestora(entidadeGestora);

                return situacaoSugestaoCompra;
            }
        }, entidade);

    }

    public SituacaoSugestaoCompra salvarSituacaoSugestaoCompra(SituacaoSugestaoCompra situacaoSugestaoCompra) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, situacaoSugestaoCompra.getDescricao());
                ps.setInt(2, situacaoSugestaoCompra.getPermiteEditar());
                ps.setInt(3, situacaoSugestaoCompra.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        situacaoSugestaoCompra.setId(id);
            
        return situacaoSugestaoCompra;

    }
    
}

