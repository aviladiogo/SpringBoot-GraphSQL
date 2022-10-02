package com.mines.EntidadeJuridica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.AtividadeComercial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.RamoAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AtividadeComercialRepositoryImplGCF implements AtividadeComercialRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_RAMO_ATIVIDADE = " ";
    private static String SELECT_ONE = " ";

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

}
