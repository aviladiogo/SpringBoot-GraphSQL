package com.mines.CondicaoPagto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.TipoPagto;
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
public class TipoPagtoRepositoryImpl implements TipoPagtoRepository {

    private static String SELECT_ALL = " select * from dx_tipopagto where entidadegestora=?";
    private static String SELECT_ONE = " select * from dx_tipopagto where id = ? ";
    private static String INSERT = " insert into dx_tipopagto (id, titulo, entidadegestora) "
            + " values (nextval('dx_tipopagto_id_seq'),?,?) ";
    private static String UPDATE = " update dx_tipopagto set titulo = ? where id = ? ";
    private static String DELETE = " delete from dx_tipopagto where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoPagto atualizarTipoPagto(TipoPagto tipoPagto) {
       
        jdbcTemplate.update(UPDATE, new Object[] {tipoPagto.getTitulo(),tipoPagto.getId()});

        return tipoPagto;

    }

    
    public Boolean deletarTipoPagto(TipoPagto tipoPagto) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {tipoPagto.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public TipoPagto obterPorIdTipoPagto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoPagto>() {
            @Override
            public TipoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoPagto tipoPagto = new TipoPagto();

                tipoPagto.setId(rs.getInt("id"));
                tipoPagto.setTitulo(rs.getString("titulo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoPagto.setEntidadeGestora(entidadeGestora);

                return tipoPagto;
            }
        });

    }
    
    public List<TipoPagto> obterTodosTiposPagto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoPagto>() {
            @Override
            public TipoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoPagto tipoPagto = new TipoPagto();

                tipoPagto.setId(rs.getInt("id"));
                tipoPagto.setTitulo(rs.getString("titulo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoPagto.setEntidadeGestora(entidadeGestora);

                return tipoPagto;
            }
        }, entidade);

    }

    public TipoPagto salvarTipoPagto(TipoPagto tipoPagto) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tipoPagto.getTitulo());
                ps.setInt(2, tipoPagto.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tipoPagto.setId(id);
            
        return tipoPagto;

    }
    
}
