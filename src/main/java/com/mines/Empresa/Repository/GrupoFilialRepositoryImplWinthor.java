package com.mines.Empresa.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Model.GrupoFilial;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GrupoFilialRepositoryImplWinthor implements GrupoFilialRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    
}
