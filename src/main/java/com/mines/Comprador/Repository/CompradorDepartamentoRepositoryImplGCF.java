package com.mines.Comprador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepository;
import com.mines.Comprador.Model.CompradorDepartamento;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorDepartamentoRepositoryImplGCF implements CompradorDepartamentoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorDepartamento obterPorIdCompradorDepartamento(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorDepartamento>() {
            @Override
            public CompradorDepartamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

                compradorDepartamento.setComprador(rs.getInt("comprador"));
                compradorDepartamento.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorDepartamento.setAtivo(rs.getInt("ativo"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                compradorDepartamento.setDepartamento(departamento);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorDepartamento.setEntidadeGestora(entidadeGestora);

                return compradorDepartamento;
            }
        });

    }

    public List<CompradorDepartamento> obterTodosCompradoresDepartamento(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorDepartamento>() {
            @Override
            public CompradorDepartamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorDepartamento compradorDepartamento = new CompradorDepartamento();

                compradorDepartamento.setComprador(rs.getInt("comprador"));
                compradorDepartamento.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorDepartamento.setAtivo(rs.getInt("ativo"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                compradorDepartamento.setDepartamento(departamento);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorDepartamento.setEntidadeGestora(entidadeGestora);

                return compradorDepartamento;
            }
        }, entidade);

    }

}

