package com.mines.Empresa.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoFilialRepositoryImpl implements GrupoFilialRepository {

    private static String SELECT_ALL = " select * from dx_grupofilial where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_grupofilial where id = ? ";
    private static String INSERT = " insert into dx_grupofilial (id, descricao, responsavel, entidadegestora)"
            + " values (nextval('dx_grupofilial_id_seq'),?,?,?) ";
    private static String UPDATE = " update dx_grupofilial set descricao = ?"
            + "where id = ? ";
    private static String DELETE = " delete from dx_grupofilial where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public GrupoFilial atualizarGrupoFilial(GrupoFilial grupoFilial) {
       
        jdbcTemplate.update(UPDATE, new Object[] {grupoFilial.getDescricao(), grupoFilial.getId()});

        return grupoFilial;

    }

    public Boolean deletarGrupoFilial(GrupoFilial grupoFilial) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {grupoFilial.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public GrupoFilial obterPorIdGrupoFilial(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<GrupoFilial>() {
            @Override
            public GrupoFilial mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                GrupoFilial grupoFilial = new GrupoFilial();

                grupoFilial.setId(rs.getInt("id"));
                grupoFilial.setDescricao(rs.getString("descricao"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);

                grupoFilial.setResponsavel(responsavel);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);

                grupoFilial.setEntidadeGestora(entidadeGestora);

                return grupoFilial;
            }
        });

    }
    
    public List<GrupoFilial> obterTodosGrupoFiliais(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<GrupoFilial>() {
            @Override
            public GrupoFilial mapRow(ResultSet rs, int rownumber) throws SQLException {

                GrupoFilial grupoFilial = new GrupoFilial();

                grupoFilial.setId(rs.getInt("id"));
                grupoFilial.setDescricao(rs.getString("descricao"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);

                grupoFilial.setResponsavel(responsavel);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);

                grupoFilial.setEntidadeGestora(entidadeGestora);

                return grupoFilial;
            }
        }, entidade);

    }

    public GrupoFilial salvarGrupoFilial(GrupoFilial tipoFilial) {
        
        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tipoFilial.getDescricao());
                ps.setInt(2, tipoFilial.getResponsavel().getId());
                ps.setInt(3, tipoFilial.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tipoFilial.setId(id);
            
        return tipoFilial;

    }
    
}
