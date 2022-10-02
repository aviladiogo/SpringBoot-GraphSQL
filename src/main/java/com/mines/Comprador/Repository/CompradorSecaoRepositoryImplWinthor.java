package com.mines.Comprador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepository;
import com.mines.Comprador.Model.CompradorSecao;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorSecaoRepositoryImplWinthor implements CompradorSecaoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SecaoRepository SecaoRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorSecao obterPorIdCompradorSecao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorSecao>() {
            @Override
            public CompradorSecao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSecao compradorSecao = new CompradorSecao();

                compradorSecao.setComprador(rs.getInt("comprador"));
                compradorSecao.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSecao.setAtivo(rs.getInt("ativo"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = SecaoRepo.obterPorIdSecao(secaoId);
                compradorSecao.setSecao(secao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSecao.setEntidadeGestora(entidadeGestora);

                return compradorSecao;
            }
        });

    }

    public List<CompradorSecao> obterTodosCompradoresSecao(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorSecao>() {
            @Override
            public CompradorSecao mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSecao compradorSecao = new CompradorSecao();

                compradorSecao.setComprador(rs.getInt("comprador"));
                compradorSecao.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSecao.setAtivo(rs.getInt("ativo"));

                Integer secaoId = rs.getInt("secao");
                Secao secao = SecaoRepo.obterPorIdSecao(secaoId);
                compradorSecao.setSecao(secao);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSecao.setEntidadeGestora(entidadeGestora);

                return compradorSecao;
            }
        }, entidade);

    }

}

