package com.mines.Seguranca.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Seguranca.Model.Modulo;
import com.mines.Seguranca.Model.Sistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ModuloRepositoryImplWinthor implements ModuloRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SistemaRepository sistemaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Modulo obterPorIdModulo(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Modulo>() {
            @Override
            public Modulo mapRow(ResultSet rs, int rownumber) throws SQLException {

                Modulo modulo = new Modulo();

                modulo.setId(rs.getInt("id"));
                modulo.setDescricao(rs.getString("descricao"));
                modulo.setAtivo(rs.getBoolean("ativo"));

                Integer sistemaId = rs.getInt("sistema");
                Sistema sistema = sistemaRepo.obterPorIdSistema(sistemaId);
                modulo.setSistema(sistema);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                modulo.setEntidadeGestora(entidadeGestora);

                return modulo;
            }
        });

    }

    public List<Modulo> obterTodosModulos(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Modulo>() {
            @Override
            public Modulo mapRow(ResultSet rs, int rownumber) throws SQLException {

                Modulo modulo = new Modulo();

                modulo.setId(rs.getInt("id"));
                modulo.setDescricao(rs.getString("descricao"));
                modulo.setAtivo(rs.getBoolean("ativo"));

                Integer sistemaId = rs.getInt("sistema");
                Sistema sistema = sistemaRepo.obterPorIdSistema(sistemaId);
                modulo.setSistema(sistema);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                modulo.setEntidadeGestora(entidadeGestora);

                return modulo;
            }
        }, entidade);

    }

}
