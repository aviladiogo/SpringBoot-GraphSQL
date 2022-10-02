package com.mines.EntidadeJuridica.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaJuridicaRepositoryImpl implements PessoaJuridicaRepository {

    private static String SELECT_ALL = " select * from dx_pessoajuridica where entidadegestora = ?";
    private static String SELECT_ONE = " select * from dx_pessoajuridica where id = ? ";
    private static String INSERT = " insert into dx_pessoajuridica (id, nomefantasia, razaosocial, cnpj, "
            + " atividadecomercial, entidadegestora) "
            + " values (nextval('dx_pessoajuridica_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_pessoajuridica set nomefantasia = ?, razaosocial = ?, cnpj = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_pessoajuridica where id = ? ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private AtividadeComercialRepository atividadeComercialRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public PessoaJuridica atualizarPessoaJuridica(PessoaJuridica pessoaJuridica) {

        jbdcTemplate.update(UPDATE, new Object[] { pessoaJuridica.getNomeFantasia(),
                pessoaJuridica.getRazaoSocial(), pessoaJuridica.getCnpj(), pessoaJuridica.getId() });

        return pessoaJuridica;
    }

    public Boolean deletarPessoaJuridica(PessoaJuridica pessoaJuridica) {

        Boolean ret = false;

        Integer rows = jbdcTemplate.update(DELETE, new Object[] { pessoaJuridica.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public PessoaJuridica obterPorIdPessoaJuridica(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<PessoaJuridica>() {
            @Override
            public PessoaJuridica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setId(rs.getInt("id"));
                pessoaJuridica.setNomeFantasia(rs.getString("nomeFantasia"));
                pessoaJuridica.setRazaoSocial(rs.getString("razaoSocial"));
                pessoaJuridica.setCnpj(rs.getString("cnpj"));

                Integer atividadeComercialId = rs.getInt("atividadecomercial");
                AtividadeComercial atividadeComercial = atividadeComercialRepo
                        .obterPorIdAtividadeComercial(atividadeComercialId);
                pessoaJuridica.setAtividadeComercial(atividadeComercial);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                pessoaJuridica.setEntidadeGestora(entidadeGestora);

                return pessoaJuridica;
            }
        });

    }

    public List<PessoaJuridica> obterTodosPessoasJuridica(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<PessoaJuridica>() {
            @Override
            public PessoaJuridica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaJuridica pessoaJuridica = new PessoaJuridica();
                pessoaJuridica.setId(rs.getInt("id"));
                pessoaJuridica.setNomeFantasia(rs.getString("nomeFantasia"));
                pessoaJuridica.setRazaoSocial(rs.getString("razaoSocial"));
                pessoaJuridica.setCnpj(rs.getString("cnpj"));

                Integer atividadeComercialId = rs.getInt("atividadecomercial");
                AtividadeComercial atividadeComercial = atividadeComercialRepo
                        .obterPorIdAtividadeComercial(atividadeComercialId);
                pessoaJuridica.setAtividadeComercial(atividadeComercial);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                pessoaJuridica.setEntidadeGestora(entidadeGestora);

                return pessoaJuridica;
            }
        }, entidade);
    }

    public PessoaJuridica salvarPessoaJuridica(PessoaJuridica pessoaJuridica) {

        KeyHolder holder = new GeneratedKeyHolder();

        jbdcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, pessoaJuridica.getNomeFantasia());
                ps.setString(2, pessoaJuridica.getRazaoSocial());
                ps.setString(3, pessoaJuridica.getCnpj());
                ps.setInt(4, pessoaJuridica.getAtividadeComercial().getId());
                ps.setInt(5, pessoaJuridica.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        pessoaJuridica.setId(id);

        return pessoaJuridica;
    }
}
