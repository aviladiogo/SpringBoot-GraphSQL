package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaMargemContribuicao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaMargemContribuicaoRepositoryImpl implements CurvaMargemContribuicaoRepository {

    private static String SELECT_ALL = " select * from dx_curvamargemcontribuicao where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_curvamargemcontribuicao where id = ? ";
    private static String INSERT = " insert into dx_curvamargemcontribuicao (id, margem, frequencia, "
            + " entidadegestora) values (nextval('dx_curvamargemcontribuicao_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_curvamargemcontribuicao set margem = ?, frequencia = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_curvamargemcontribuicao where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaMargemContribuicao atualizarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao) {
       
        jdbcTemplate.update(UPDATE, new Object[] {curvaMargemContribuicao.getMargem(), 
            curvaMargemContribuicao.getFrequencia(), curvaMargemContribuicao.getId()});

        return curvaMargemContribuicao;

    }

    
    public Boolean deletarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {curvaMargemContribuicao.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public CurvaMargemContribuicao obterPorIdCurvaMargemContribuicao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CurvaMargemContribuicao>() {
            @Override
            public CurvaMargemContribuicao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CurvaMargemContribuicao curvaMargemContribuicao = new CurvaMargemContribuicao();

                curvaMargemContribuicao.setId(rs.getInt("id"));
                curvaMargemContribuicao.setMargem(rs.getDouble("margem"));
                curvaMargemContribuicao.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaMargemContribuicao.setEntidadeGestora(entidadeGestora);
                
                return curvaMargemContribuicao;
            }
        });

    }

    public List<CurvaMargemContribuicao> obterTodosCurvasMargemContribuicao(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CurvaMargemContribuicao>() {
            @Override
            public CurvaMargemContribuicao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CurvaMargemContribuicao curvaMargemContribuicao = new CurvaMargemContribuicao();

                curvaMargemContribuicao.setId(rs.getInt("id"));
                curvaMargemContribuicao.setMargem(rs.getDouble("margem"));
                curvaMargemContribuicao.setFrequencia(rs.getDouble("frequencia"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaMargemContribuicao.setEntidadeGestora(entidadeGestora);
                
                return curvaMargemContribuicao;
            }
        }, entidade);

    }

    public CurvaMargemContribuicao salvarCurvaMargemContribuicao(CurvaMargemContribuicao curvaMargemContribuicao) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setDouble(1, curvaMargemContribuicao.getMargem());
                ps.setDouble(2, curvaMargemContribuicao.getFrequencia());
                ps.setInt(3, curvaMargemContribuicao.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        curvaMargemContribuicao.setId(id);
            
        return curvaMargemContribuicao;

    }
   
}