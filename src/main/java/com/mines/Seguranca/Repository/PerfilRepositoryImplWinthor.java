package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Seguranca.Model.Perfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PerfilRepositoryImplWinthor implements PerfilRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Perfil obterPorIdPerfil(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Perfil>() {
            @Override
            public Perfil mapRow(ResultSet rs, int rownumber) throws SQLException {

                Perfil perfil = new Perfil();

                perfil.setId(rs.getInt("id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                perfil.setEntidadeGestora(entidadeGestora);

                return perfil;
            }
        });

    }

    public List<Perfil> obterTodosPerfis(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Perfil>() {
            @Override
            public Perfil mapRow(ResultSet rs, int rownumber) throws SQLException {

                Perfil perfil = new Perfil();

                perfil.setId(rs.getInt("id"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setAtivo(rs.getBoolean("ativo"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                perfil.setEntidadeGestora(entidadeGestora);

                return perfil;
            }
        }, entidade);

    }

}
