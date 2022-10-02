package com.mines.Comprador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.Comprador.Model.CompradorSubCategoria;
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
public class CompradorSubCategoriaRepositoryImpl implements CompradorSubCategoriaRepository {

    private static String SELECT_ALL = " select * from dx_compradorsubcategoria where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_compradorsubcategoria where comprador = ? ";
    private static String INSERT = " insert into dx_compradorsubcategoria (comprador, limiteCompra, ativo, subcategoria, "
            + " entidadegestora) values (?,?,?,?,?) ";
    private static String UPDATE = " update dx_compradorsubcategoria set limitecompra = ?, ativo = ? "
            + " where comprador = ? ";
    private static String DELETE = " delete from dx_compradorsubcategoria where comprador = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SubCategoriaRepository SubCategoriaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CompradorSubCategoria atualizarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria) {

        jdbcTemplate.update(UPDATE,
                new Object[] { compradorSubCategoria.getLimiteCompra(), compradorSubCategoria.getAtivo(), compradorSubCategoria.getComprador() });

        return compradorSubCategoria;

    }

    public Boolean deletarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { compradorSubCategoria.getComprador() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

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

    public CompradorSubCategoria salvarCompradorSubCategoria(CompradorSubCategoria compradorSubCategoria) {

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, compradorSubCategoria.getComprador());
                ps.setDouble(2, compradorSubCategoria.getLimiteCompra());
                ps.setInt(3, compradorSubCategoria.getAtivo());
                ps.setInt(4, compradorSubCategoria.getSubCategoria().getId());
                ps.setInt(5, compradorSubCategoria.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        //Integer comprador = (Integer) holder.getKeys().get("comprador");
        //compradorSubCategoria.setComprador(comprador);

        return compradorSubCategoria;

    }

}
