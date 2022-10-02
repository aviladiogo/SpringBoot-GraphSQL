package com.mines.Colaborador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Colaborador.Model.Colaborador;
import com.mines.Colaborador.Model.Funcao;
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
public class ColaboradorRepositoryImpl implements ColaboradorRepository {

    private static String SELECT_ALL = " select * from dx_colaborador where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_colaborador where id = ? ";
    private static String INSERT = " insert into dx_colaborador (id, descricao, funcao, entidadegestora) "
            + " values (nextval('dx_colaborador_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_colaborador set descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_colaborador where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FuncaoRepository funcaoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Colaborador atualizarColaborador(Colaborador colaborador) {
       
        jdbcTemplate.update(UPDATE, new Object[] {colaborador.getDescricao(), colaborador.getId()});

        return colaborador;

    }

    
    public Boolean deletarColaborador(Colaborador colaborador) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {colaborador.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Colaborador obterPorIdColaborador(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Colaborador>() {
            @Override
            public Colaborador mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Colaborador colaborador = new Colaborador();

                colaborador.setId(rs.getInt("id"));
                colaborador.setDescricao(rs.getString("descricao"));

                Integer funcaoId = rs.getInt("funcao");
                Funcao funcao = funcaoRepo.obterPorIdFuncao(funcaoId);
                colaborador.setFuncao(funcao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                colaborador.setEntidadeGestora(entidadeGestora);

                return colaborador;
            }
        });

    }
    
    public List<Colaborador> obterTodosColaboradores(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Colaborador>() {
            @Override
            public Colaborador mapRow(ResultSet rs, int rownumber) throws SQLException {

                Colaborador colaborador = new Colaborador();

                colaborador.setId(rs.getInt("id"));
                colaborador.setDescricao(rs.getString("descricao"));

                Integer funcaoId = rs.getInt("funcao");
                Funcao funcao = funcaoRepo.obterPorIdFuncao(funcaoId);
                colaborador.setFuncao(funcao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                colaborador.setEntidadeGestora(entidadeGestora);

                return colaborador;
            }
        }, entidade);

    }

    public Colaborador salvarColaborador(Colaborador colaborador) {

        KeyHolder holder = new GeneratedKeyHolder();
        
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, colaborador.getDescricao());
                    ps.setInt(2, colaborador.getFuncao().getId());
                    ps.setInt(3, colaborador.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
    
            Integer id = (Integer) holder.getKeys().get("id");
            colaborador.setId(id);
    
            return colaborador;

    }

    
}



