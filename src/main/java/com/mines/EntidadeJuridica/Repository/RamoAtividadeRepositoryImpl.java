package com.mines.EntidadeJuridica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RamoAtividadeRepositoryImpl implements RamoAtividadeRepository {

    private static String SELECT_ALL = " select * from dx_ramoatividade where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_ramoatividade where id = ? ";
    private static String INSERT = " insert into dx_ramoatividade (id, descricao, registro, usuario, "
            + " entidadegestora) values (nextval('dx_ramoatividade_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_ramoatividade set descricao = ? where id = ? ";
    private static String DELETE = " delete from dx_ramoatividade where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public RamoAtividade atualizarRamoAtividade(RamoAtividade ramoAtividade) {
       
        jdbcTemplate.update(UPDATE, new Object[] {ramoAtividade.getDescricao(),ramoAtividade.getId()});

        return ramoAtividade;

    }

    
    public Boolean deletarRamoAtividade(RamoAtividade ramoAtividade) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {ramoAtividade.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public RamoAtividade obterPorIdRamoAtividade(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<RamoAtividade>() {
            @Override
            public RamoAtividade mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                RamoAtividade ramoAtividade = new RamoAtividade();

                ramoAtividade.setId(rs.getInt("id"));
                ramoAtividade.setDescricao(rs.getString("descricao"));
                ramoAtividade.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                ramoAtividade.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                ramoAtividade.setEntidadeGestora(entidadeGestora);

                return ramoAtividade;
            }
        });

    }
    
    public List<RamoAtividade> obterTodosRamoAtividades(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<RamoAtividade>() {
            @Override
            public RamoAtividade mapRow(ResultSet rs, int rownumber) throws SQLException {

                RamoAtividade ramoAtividade = new RamoAtividade();

                ramoAtividade.setId(rs.getInt("id"));
                ramoAtividade.setDescricao(rs.getString("descricao"));
                ramoAtividade.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                ramoAtividade.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                ramoAtividade.setEntidadeGestora(entidadeGestora);

                return ramoAtividade;
            }
        }, entidade);

    }

    public RamoAtividade salvarRamoAtividade(RamoAtividade ramoAtividade) {
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, ramoAtividade.getDescricao());
                ps.setDate(2, registroSQL);
                ps.setInt(3, ramoAtividade.getUsuario().getId());
                ps.setInt(4, ramoAtividade.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        ramoAtividade.setId(id);
            
        return ramoAtividade;

    }
    
}
