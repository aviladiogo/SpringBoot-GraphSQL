package com.mines.Empresa.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.Filial;
import com.mines.Empresa.Model.GrupoCompra;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.Empresa.Model.TipoFilial;
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
public class FilialRepositoryImpl implements FilialRepository {

    private static String SELECT_ALL = " select * from dx_filial where entidadegestora = ? ";
    private static String SELECT_ALL_POR_GRUPO_COMPRA_INTERNO = " select * from dx_filial where "
            + " entidadegestora = ? and grupocomprainterno = ?";
    private static String SELECT_ONE = " select * from dx_filial where id = ? ";
    private static String INSERT = " insert into dx_filial (id, responsavel, supervisor, ativo, dataabertura, uf, "
            + " grupofilial, grupocomprainterno, grupocompraexterno, tipofilial, observacao, entidadegestora) "
            + " values (nextval('dx_filial_id_seq'),?,?,?,?,?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_filial set ativo = ?, uf = ?, observacao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_filial where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private GrupoFilialRepository grupoFilialRepo;

    @Autowired
    private GrupoCompraRepository grupoCompraRepo;

    @Autowired
    private TipoFilialRepository tipoFilialRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Filial atualizarFilial(Filial filial) {

        jdbcTemplate.update(UPDATE,
                new Object[] { filial.getAtivo(), filial.getUf(), filial.getObservacao(), filial.getId() });

        return filial;
    }

    public Boolean deletarFilial(Filial filial) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { filial.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Filial obterPorIdFilial(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {

                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("Grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        });

    }

    public List<Filial> obterTodosFiliais(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {

                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        }, entidade);

    }

    public List<Filial> obterTodosFiliaisPorGrupoCompraInterno(Integer entidade, Integer grupoCompraInterno) {

        return jdbcTemplate.query(SELECT_ALL_POR_GRUPO_COMPRA_INTERNO, new RowMapper<Filial>() {
            @Override
            public Filial mapRow(ResultSet rs, int rownumber) throws SQLException {

                Filial filial = new Filial();

                filial.setId(rs.getInt("id"));
                filial.setAtivo(rs.getBoolean("ativo"));
                filial.setObservacao(rs.getString("observacao"));
                filial.setDataAbertura(rs.getDate("dataabertura"));
                filial.setUf(rs.getString("uf"));

                Integer responsavelId = rs.getInt("responsavel");
                PessoaFisica responsavel = pessoaFisicaRepo.obterPorIdPessoaFisica(responsavelId);
                filial.setResponsavel(responsavel);

                Integer supervisorId = rs.getInt("supervisor");
                PessoaFisica supervisor = pessoaFisicaRepo.obterPorIdPessoaFisica(supervisorId);
                filial.setSupervisor(supervisor);

                Integer grupoFilialId = rs.getInt("grupofilial");
                GrupoFilial GrupoFilial = grupoFilialRepo.obterPorIdGrupoFilial(grupoFilialId);
                filial.setGrupoFilial(GrupoFilial);

                Integer grupoCompraInternoId = rs.getInt("grupocomprainterno");
                GrupoCompra grupoCompraInterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraInternoId);
                filial.setGrupoCompraInterno(grupoCompraInterno);

                Integer grupoCompraExternoId = rs.getInt("grupocompraexterno");
                GrupoCompra grupoCompraExterno = grupoCompraRepo.obterPorIdGrupoCompra(grupoCompraExternoId);
                filial.setGrupoCompraExterno(grupoCompraExterno);

                Integer tipoFilialId = rs.getInt("tipofilial");
                TipoFilial tipoFilial = tipoFilialRepo.obterPorIdTipoFilial(tipoFilialId);
                filial.setTipoFilial(tipoFilial);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                filial.setEntidadeGestora(entidadeGestora);

                return filial;
            }
        }, entidade, grupoCompraInterno);

    }

    public Filial salvarFilial(Filial filial) {

        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, filial.getResponsavel().getId());
                ps.setInt(2, filial.getSupervisor().getId());
                ps.setBoolean(3, filial.getAtivo());
                ps.setDate(4, registroSQL);
                ps.setString(5, filial.getUf());
                ps.setInt(6, filial.getGrupoFilial().getId());
                ps.setInt(7, filial.getGrupoCompraInterno().getId());
                ps.setInt(8, filial.getGrupoCompraExterno().getId());
                ps.setInt(9, filial.getTipoFilial().getId());
                ps.setString(10, filial.getObservacao());
                ps.setInt(11, filial.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        filial.setId(id);

        return filial;
    }

}
