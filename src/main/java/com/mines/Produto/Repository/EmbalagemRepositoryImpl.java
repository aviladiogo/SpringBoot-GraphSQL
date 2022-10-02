package com.mines.Produto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Embalagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmbalagemRepositoryImpl implements EmbalagemRepository {

    private static String SELECT_ALL = " select * from dx_embalagem where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_embalagem where id = ? ";
    private static String INSERT = " insert into dx_embalagem (id, descricao, entidadegestora)"
            + " values (nextval('dx_embalagem_id_seq'),?,?) ";
    private static String UPDATE = " update dx_embalagem set descricao = ? "
            + "where id = ? ";
    private static String DELETE = " delete from dx_embalagem where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Embalagem atualizarEmbalagem(Embalagem embalagem) {
       
        jdbcTemplate.update(UPDATE, new Object[] {embalagem.getDescricao(), embalagem.getId()});

        return embalagem;

    }

    public Boolean deletarEmbalagem(Embalagem embalagem) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {embalagem.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    public Embalagem obterPorIdEmbalagem(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Embalagem>() {
            @Override
            public Embalagem mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Embalagem embalagem = new Embalagem();

                embalagem.setId(rs.getInt("id"));
                embalagem.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                embalagem.setEntidadeGestora(entidadeGestora);
                
                return embalagem;
            }
        });

    }

    public List<Embalagem> obterTodosEmbalagens(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Embalagem>() {
            @Override
            public Embalagem mapRow(ResultSet rs, int rownumber) throws SQLException {

                Embalagem embalagem = new Embalagem();

                embalagem.setId(rs.getInt("id"));
                embalagem.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                embalagem.setEntidadeGestora(entidadeGestora);
                
                return embalagem;
            }
        }, entidade);

    }

    public Embalagem salvarEmbalagem(Embalagem embalagem) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, embalagem.getDescricao());
                    ps.setInt(2, embalagem.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
    
            Integer id = (Integer) holder.getKeys().get("id");
            embalagem.setId(id);
                
            return embalagem;

    }
    
}
