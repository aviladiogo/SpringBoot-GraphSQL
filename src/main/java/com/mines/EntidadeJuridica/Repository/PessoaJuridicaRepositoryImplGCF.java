package com.mines.EntidadeJuridica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaJuridicaRepositoryImplGCF implements PessoaJuridicaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private AtividadeComercialRepository atividadeComercialRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jbdcTemplate = new JdbcTemplate(dataSource);
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

}
