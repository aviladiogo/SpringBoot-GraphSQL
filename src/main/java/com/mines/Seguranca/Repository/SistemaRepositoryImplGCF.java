package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Seguranca.Model.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SistemaRepositoryImplGCF implements SistemaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Sistema obterPorIdSistema(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Sistema>() {
            @Override
            public Sistema mapRow(ResultSet rs, int rownumber) throws SQLException {

                Sistema sistema = new Sistema();

                sistema.setId(rs.getInt("id"));
                sistema.setDescricao(rs.getString("descricao"));
                sistema.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sistema.setEntidadeGestora(entidadeGestora);

                return sistema;
            }
        });

    }

    public List<Sistema> obterTodosSistemas(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Sistema>() {
            @Override
            public Sistema mapRow(ResultSet rs, int rownumber) throws SQLException {

                Sistema sistema = new Sistema();

                sistema.setId(rs.getInt("id"));
                sistema.setDescricao(rs.getString("descricao"));
                sistema.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                sistema.setEntidadeGestora(entidadeGestora);

                return sistema;
            }
        }, entidade);

    }

}
