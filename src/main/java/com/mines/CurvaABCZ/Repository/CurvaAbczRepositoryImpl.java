package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
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
public class CurvaAbczRepositoryImpl implements CurvaAbczRepository {

    private static String SELECT_ALL = " select * from dx_curvaabcz where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ALL_ID = " select id from dx_curvaabcz";
    private static String SELECT_ONE = " select * from dx_curvaabcz where id = ? ";
    private static String INSERT = " insert into dx_curvaabcz (id, descricao, tipocurvaabcz, curva, "
            + " entidadegestora) values (nextval('dx_curvaabcz_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_curvaabcz set descricao = ?, curva = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_curvaabcz where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CurvaAbcz atualizarCurvaABCZ(CurvaAbcz curvaAbcz) {
       
        jdbcTemplate.update(UPDATE, new Object[] {curvaAbcz.getDescricao(), curvaAbcz.getCurva(),
            curvaAbcz.getId()});

        return curvaAbcz;

    }

    
    public Boolean deletarCurvaABCZ(CurvaAbcz curvaAbcz) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {curvaAbcz.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public CurvaAbcz obterPorIdCurvaABCZ(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CurvaAbcz>() {
            @Override
            public CurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CurvaAbcz curvaAbcz = new CurvaAbcz();

                curvaAbcz.setId(rs.getInt("id"));
                curvaAbcz.setDescricao(rs.getString("descricao"));
                curvaAbcz.setCurva(rs.getString("curva"));

                Integer tipoCurvaAbczId = rs.getInt("tipocurvaabcz");
                TipoCurvaAbcz tipoCurvaAbcz = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(tipoCurvaAbczId);
                curvaAbcz.setTipoCurvaAbcz(tipoCurvaAbcz);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaAbcz.setEntidadeGestora(entidadeGestora);

                return curvaAbcz;
            }
        });

    }
    
    public List<CurvaAbcz> obterTodosCurvasABCZ(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CurvaAbcz>() {
            @Override
            public CurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {

                CurvaAbcz curvaAbcz = new CurvaAbcz();

                curvaAbcz.setId(rs.getInt("id"));
                curvaAbcz.setDescricao(rs.getString("descricao"));
                curvaAbcz.setCurva(rs.getString("curva"));

                Integer tipoCurvaAbczId = rs.getInt("tipocurvaabcz");
                TipoCurvaAbcz tipoCurvaAbcz = tipoCurvaAbczRepo.obterPorIdTipoCurvaAbcz(tipoCurvaAbczId);
                curvaAbcz.setTipoCurvaAbcz(tipoCurvaAbcz);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                curvaAbcz.setEntidadeGestora(entidadeGestora);

                return curvaAbcz;
            }
        }, entidade);

    }

    public List<Integer> obterTodosIDsCurvaABCZ() {

        return jdbcTemplate.query(SELECT_ALL_ID, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {

                return rs.getInt("id");
            }
        });
    }

    public CurvaAbcz salvarCurvaABCZ(CurvaAbcz curvaAbcz) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, curvaAbcz.getDescricao());
                ps.setInt(2, curvaAbcz.getTipoCurvaAbcz().getId());
                ps.setString(3, curvaAbcz.getCurva());
                ps.setInt(4, curvaAbcz.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        curvaAbcz.setId(id);
            
        return curvaAbcz;

    }

    
}
