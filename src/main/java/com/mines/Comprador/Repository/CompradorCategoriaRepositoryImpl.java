package com.mines.Comprador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepository;
import com.mines.Comprador.Model.CompradorCategoria;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorCategoriaRepositoryImpl implements CompradorCategoriaRepository {

    private static String SELECT_ALL = " select * from dx_compradorcategoria where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_compradorcategoria where comprador = ? ";
    private static String INSERT = " insert into dx_compradorcategoria (comprador, limiteCompra, ativo, categoria, "
            + " entidadegestora) values (?,?,?,?,?) ";
    private static String UPDATE = " update dx_compradorcategoria set limitecompra = ?, ativo = ? "
            + " where comprador = ? ";
    private static String DELETE = " delete from dx_compradorcategoria where comprador = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CategoriaRepository CategoriaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorCategoria atualizarCompradorCategoria(CompradorCategoria compradorCategoria) {

        jdbcTemplate.update(UPDATE,
                new Object[] { compradorCategoria.getLimiteCompra(), compradorCategoria.getAtivo(), compradorCategoria.getComprador() });

        return compradorCategoria;

    }

    public Boolean deletarCompradorCategoria(CompradorCategoria compradorCategoria) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { compradorCategoria.getComprador() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

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

    public CompradorCategoria salvarCompradorCategoria(CompradorCategoria compradorCategoria) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, compradorCategoria.getComprador());
                ps.setDouble(2, compradorCategoria.getLimiteCompra());
                ps.setInt(3, compradorCategoria.getAtivo());
                ps.setInt(4, compradorCategoria.getCategoria().getId());
                ps.setInt(5, compradorCategoria.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        //Integer comprador = (Integer) holder.getKeys().get("comprador");
        //compradorCategoria.setComprador(comprador);

        return compradorCategoria;

    }

}
