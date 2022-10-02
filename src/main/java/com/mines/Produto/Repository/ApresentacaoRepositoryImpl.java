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
import com.mines.Produto.Model.Apresentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ApresentacaoRepositoryImpl implements ApresentacaoRepository {

    private static String SELECT_ALL = " select * from dx_apresentacao where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_apresentacao where id = ? ";
    private static String INSERT = " insert into dx_apresentacao (id, descricao, entidadegestora)"
            + " values (nextval('dx_apresentacao_id_seq'),?,?) ";
    private static String UPDATE = " update dx_apresentacao set descricao = ? "
            + "where id = ? ";
    private static String DELETE = " delete from dx_apresentacao where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Apresentacao atualizarApresentacao(Apresentacao apresentacao) {
       
        jdbcTemplate.update(UPDATE, new Object[] {apresentacao.getDescricao(), apresentacao.getId()});

        return apresentacao;

    }

    public Boolean deletarApresentacao(Apresentacao apresentacao) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {apresentacao.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    public Apresentacao obterPorIdApresentacao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Apresentacao>() {
            @Override
            public Apresentacao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Apresentacao apresentacao = new Apresentacao();

                apresentacao.setId(rs.getInt("id"));
                apresentacao.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                apresentacao.setEntidadeGestora(entidadeGestora);
                
                return apresentacao;
            }
        });

    }
    
    public List<Apresentacao> obterTodosApresentacoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Apresentacao>() {
            @Override
            public Apresentacao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Apresentacao apresentacao = new Apresentacao();

                apresentacao.setId(rs.getInt("id"));
                apresentacao.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                apresentacao.setEntidadeGestora(entidadeGestora);
                
                return apresentacao;
            }
        }, entidade);

    }

    public Apresentacao salvarApresentacao(Apresentacao apresentacao) {

            KeyHolder holder = new GeneratedKeyHolder();
        
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, apresentacao.getDescricao());
                    ps.setInt(2, apresentacao.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
    
            Integer id = (Integer) holder.getKeys().get("id");
            apresentacao.setId(id);
                
            return apresentacao;

    }
    
}
