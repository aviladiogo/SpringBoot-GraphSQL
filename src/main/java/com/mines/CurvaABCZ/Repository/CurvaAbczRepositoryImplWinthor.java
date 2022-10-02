package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.CurvaAbcz;
import com.mines.CurvaABCZ.Model.TipoCurvaAbcz;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CurvaAbczRepositoryImplWinthor implements CurvaAbczRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_ID = " ";
    private static String SELECT_ONE = " select * from dx_curvaabcz where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired
    private TipoCurvaAbczRepository tipoCurvaAbczRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    
}
