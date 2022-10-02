package com.mines.Comprador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.Comprador.Model.CompradorSubCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorSubCategoriaRepositoryImplWinthor implements CompradorSubCategoriaRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SubCategoriaRepository SubCategoriaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorSubCategoria obterPorIdCompradorSubCategoria(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorSubCategoria>() {
            @Override
            public CompradorSubCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSubCategoria compradorSubCategoria = new CompradorSubCategoria();

                compradorSubCategoria.setComprador(rs.getInt("comprador"));
                compradorSubCategoria.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSubCategoria.setAtivo(rs.getInt("ativo"));

                Integer subCategoriaId = rs.getInt("subcategoria");
                SubCategoria subCategoria = SubCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                compradorSubCategoria.setSubCategoria(subCategoria);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSubCategoria.setEntidadeGestora(entidadeGestora);

                return compradorSubCategoria;
            }
        });

    }

    public List<CompradorSubCategoria> obterTodosCompradoresSubCategoria(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorSubCategoria>() {
            @Override
            public CompradorSubCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorSubCategoria compradorSubCategoria = new CompradorSubCategoria();

                compradorSubCategoria.setComprador(rs.getInt("comprador"));
                compradorSubCategoria.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorSubCategoria.setAtivo(rs.getInt("ativo"));

                Integer subCategoriaId = rs.getInt("subcategoria");
                SubCategoria subCategoria = SubCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                compradorSubCategoria.setSubCategoria(subCategoria);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorSubCategoria.setEntidadeGestora(entidadeGestora);

                return compradorSubCategoria;
            }
        }, entidade);

    }

}
