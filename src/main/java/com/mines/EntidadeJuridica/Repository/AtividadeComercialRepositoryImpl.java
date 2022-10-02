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
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
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
public class AtividadeComercialRepositoryImpl implements AtividadeComercialRepository {

    private static String SELECT_ALL = " select * from dx_atividadecomercial where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ALL_RAMO_ATIVIDADE = " select * from dx_atividadecomercial where entidadegestora = ? "
            + " and ramoatividade = ? ";
    private static String SELECT_ONE = " select * from dx_atividadecomercial where id = ? ";
    private static String INSERT = " insert into dx_atividadecomercial (id, descricao, ramoatividade, "
            + " registro, usuario, entidadegestora) "
            + " values (nextval('dx_atividadecomercial_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_atividadecomercial set descricao = ? where id = ? ";
    private static String DELETE = " delete from dx_atividadecomercial where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RamoAtividadeRepository ramoAtividadeRepo;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public AtividadeComercial atualizarAtividadeComercial(AtividadeComercial atividadeComercial) {

        jdbcTemplate.update(UPDATE, new Object[] { atividadeComercial.getDescricao(), atividadeComercial.getId() });

        return atividadeComercial;

    }

    public Boolean deletarAtividadeComercial(AtividadeComercial atividadeComercial) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { atividadeComercial.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public AtividadeComercial obterPorIdAtividadeComercial(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<AtividadeComercial>() {
            @Override
            public AtividadeComercial mapRow(ResultSet rs, int rownumber) throws SQLException {

                AtividadeComercial atividadeComercial = new AtividadeComercial();

                atividadeComercial.setId(rs.getInt("id"));
                atividadeComercial.setDescricao(rs.getString("descricao"));
                atividadeComercial.setRegistro(rs.getDate("registro"));

                Integer ramoAtividadeId = rs.getInt("ramoatividade");
                RamoAtividade ramoAtividade = ramoAtividadeRepo.obterPorIdRamoAtividade(ramoAtividadeId);
                atividadeComercial.setRamoAtividade(ramoAtividade);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                atividadeComercial.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                atividadeComercial.setEntidadeGestora(entidadeGestora);

                return atividadeComercial;
            }
        });

    }

    public List<AtividadeComercial> obterTodosAtividadesComercial(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<AtividadeComercial>() {
            @Override
            public AtividadeComercial mapRow(ResultSet rs, int rownumber) throws SQLException {

                AtividadeComercial atividadeComercial = new AtividadeComercial();

                atividadeComercial.setId(rs.getInt("id"));
                atividadeComercial.setDescricao(rs.getString("descricao"));
                atividadeComercial.setRegistro(rs.getDate("registro"));

                Integer ramoAtividadeId = rs.getInt("ramoatividade");
                RamoAtividade ramoAtividade = ramoAtividadeRepo.obterPorIdRamoAtividade(ramoAtividadeId);
                atividadeComercial.setRamoAtividade(ramoAtividade);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                atividadeComercial.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                atividadeComercial.setEntidadeGestora(entidadeGestora);

                return atividadeComercial;
            }
        }, entidade);

    }

    public List<AtividadeComercial> obterTodosAtividadesComercialPorRamoAtividade(Integer entidade,
            Integer ramoAtividade) {

        return jdbcTemplate.query(SELECT_ALL_RAMO_ATIVIDADE, new RowMapper<AtividadeComercial>() {
            @Override
            public AtividadeComercial mapRow(ResultSet rs, int rownumber) throws SQLException {

                AtividadeComercial atividadeComercial = new AtividadeComercial();

                atividadeComercial.setId(rs.getInt("id"));
                atividadeComercial.setDescricao(rs.getString("descricao"));
                atividadeComercial.setRegistro(rs.getDate("registro"));

                Integer ramoAtividadeId = rs.getInt("ramoatividade");
                RamoAtividade ramoAtividade = ramoAtividadeRepo.obterPorIdRamoAtividade(ramoAtividadeId);
                atividadeComercial.setRamoAtividade(ramoAtividade);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                atividadeComercial.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                atividadeComercial.setEntidadeGestora(entidadeGestora);

                return atividadeComercial;
            }
        }, entidade, ramoAtividade);

    }

    public AtividadeComercial salvarAtividadeComercial(AtividadeComercial atividadeComercial) {

        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, atividadeComercial.getDescricao());
                ps.setInt(2, atividadeComercial.getRamoAtividade().getId());
                ps.setDate(3, registroSQL);
                ps.setInt(4, atividadeComercial.getUsuario().getId());
                ps.setInt(5, atividadeComercial.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        atividadeComercial.setId(id);

        return atividadeComercial;

    }

}
