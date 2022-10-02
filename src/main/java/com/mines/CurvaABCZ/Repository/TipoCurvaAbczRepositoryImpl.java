package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
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
public class TipoCurvaAbczRepositoryImpl implements TipoCurvaAbczRepository {

    private static String SELECT_ALL = " select * from dx_tipocurvaabcz where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_tipocurvaabcz where id = ? ";
    private static String INSERT = " insert into dx_tipocurvaabcz (id, entidadeestoque, descricao, tipocurva, "
            + " entidadegestora) "
            + " values (nextval('dx_tipocurvaabcz_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_tipocurvaabcz set descricao = ?, tipocurva = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_tipocurvaabcz where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoCurvaAbcz atualizarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz) {
       
        jdbcTemplate.update(UPDATE, new Object[] {tipoCurvaAbcz.getDescricao(), tipoCurvaAbcz.getTipoCurva(), 
            tipoCurvaAbcz.getId()});

        return tipoCurvaAbcz;

    }

    
    public Boolean deletarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {tipoCurvaAbcz.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public TipoCurvaAbcz obterPorIdTipoCurvaAbcz(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoCurvaAbcz>() {
            @Override
            public TipoCurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();

                tipoCurvaAbcz.setId(rs.getInt("id"));
                tipoCurvaAbcz.setDescricao(rs.getString("descricao"));
                tipoCurvaAbcz.setTipoCurva(rs.getString("tipocurva"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                tipoCurvaAbcz.setEntidadeEstoque(entidadeEstoque);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoCurvaAbcz.setEntidadeGestora(entidadeGestora);

                return tipoCurvaAbcz;
            }
        });

    }
    
    public List<TipoCurvaAbcz> obterTodosTiposCurvaAbcz(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoCurvaAbcz>() {
            @Override
            public TipoCurvaAbcz mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoCurvaAbcz tipoCurvaAbcz = new TipoCurvaAbcz();

                tipoCurvaAbcz.setId(rs.getInt("id"));
                tipoCurvaAbcz.setDescricao(rs.getString("descricao"));
                tipoCurvaAbcz.setTipoCurva(rs.getString("tipocurva"));

                Integer entidadeEstoqueId = rs.getInt("entidadeestoque");
                Empresa entidadeEstoque = empresaRepo.obterPorIdEmpresa(entidadeEstoqueId);
                tipoCurvaAbcz.setEntidadeEstoque(entidadeEstoque);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoCurvaAbcz.setEntidadeGestora(entidadeGestora);

                return tipoCurvaAbcz;
            }
        }, entidade);

    }

    public TipoCurvaAbcz salvarTipoCurvaAbcz(TipoCurvaAbcz tipoCurvaAbcz) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, tipoCurvaAbcz.getEntidadeEstoque().getId());
                ps.setString(2, tipoCurvaAbcz.getDescricao());
                ps.setString(3, tipoCurvaAbcz.getTipoCurva());
                ps.setInt(4, tipoCurvaAbcz.getEntidadeGestora().getId());
                
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tipoCurvaAbcz.setId(id);
            
        return tipoCurvaAbcz;

    }

    
}

