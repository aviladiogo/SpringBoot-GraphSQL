package com.mines.Comprador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepository;
import com.mines.Comprador.Model.CompradorCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorCategoriaRepositoryImplWinthor implements CompradorCategoriaRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoriaRepository CategoriaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorCategoria obterPorIdCompradorCategoria(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<CompradorCategoria>() {
            @Override
            public CompradorCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorCategoria compradorCategoria = new CompradorCategoria();

                compradorCategoria.setComprador(rs.getInt("comprador"));
                compradorCategoria.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorCategoria.setAtivo(rs.getInt("ativo"));

                Integer categoriaId = rs.getInt("categoria");
                Categoria categoria = CategoriaRepo.obterPorIdCategoria(categoriaId);
                compradorCategoria.setCategoria(categoria);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorCategoria.setEntidadeGestora(entidadeGestora);

                return compradorCategoria;
            }
        });

    }

    public List<CompradorCategoria> obterTodosCompradoresCategoria(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CompradorCategoria>() {
            @Override
            public CompradorCategoria mapRow(ResultSet rs, int rownumber) throws SQLException {

                CompradorCategoria compradorCategoria = new CompradorCategoria();

                compradorCategoria.setComprador(rs.getInt("comprador"));
                compradorCategoria.setLimiteCompra(rs.getDouble("limitecompra"));
                compradorCategoria.setAtivo(rs.getInt("ativo"));

                Integer categoriaId = rs.getInt("categoria");
                Categoria categoria = CategoriaRepo.obterPorIdCategoria(categoriaId);
                compradorCategoria.setCategoria(categoria);

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                compradorCategoria.setEntidadeGestora(entidadeGestora);

                return compradorCategoria;
            }
        }, entidade);

    }

}
