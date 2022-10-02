package com.mines.Comprador.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Comprador.Model.Comprador;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorRepositoryImpl implements CompradorRepository {

    private static String SELECT_ALL = " select * from dx_comprador where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_comprador where id = ? ";
    private static String INSERT_NOT_NULL = " insert into dx_comprador (id, pessoa, limiteCompra, ativo, compradorlider, "
            + " entidadegestora) values (nextval('dx_comprador_id_seq'),?,?,?,?,?) ";
    private static String INSERT_NULL = " insert into dx_comprador (id, pessoa, limiteCompra, ativo, entidadegestora) "
            + " values (nextval('dx_comprador_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_comprador set limitecompra = ?, ativo = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_comprador where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Comprador atualizarComprador(Comprador comprador) {

        jdbcTemplate.update(UPDATE,
                new Object[] { comprador.getLimiteCompra(), comprador.getAtivo(), comprador.getId() });

        return comprador;

    }

    public Boolean deletarComprador(Comprador comprador) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { comprador.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Comprador obterPorIdComprador(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<Comprador>() {
            @Override
            public Comprador mapRow(ResultSet rs, int rownumber) throws SQLException {

                Comprador comprador = new Comprador();

                comprador.setId(rs.getInt("id"));
                comprador.setLimiteCompra(rs.getDouble("limitecompra"));
                comprador.setAtivo(rs.getInt("ativo"));

                Integer pessoaId = rs.getInt("pessoa");
                PessoaFisica pessoa = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaId);
                comprador.setPessoa(pessoa);

                Integer compradorLiderId = rs.getInt("compradorlider");
                if(compradorLiderId!=0){
                    PessoaFisica compradorLider = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorLiderId);
                    comprador.setCompradorLider(compradorLider);
                }

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                comprador.setEntidadeGestora(entidadeGestora);

                return comprador;
            }
        });

    }

    public List<Comprador> obterTodosCompradores(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Comprador>() {
            @Override
            public Comprador mapRow(ResultSet rs, int rownumber) throws SQLException {

                Comprador comprador = new Comprador();

                comprador.setId(rs.getInt("id"));
                comprador.setLimiteCompra(rs.getDouble("limitecompra"));
                comprador.setAtivo(rs.getInt("ativo"));

                Integer pessoaId = rs.getInt("pessoa");
                PessoaFisica pessoa = pessoaFisicaRepo.obterPorIdPessoaFisica(pessoaId);
                comprador.setPessoa(pessoa);

                Integer compradorLiderId = rs.getInt("compradorlider");
                if(compradorLiderId!=0){
                    PessoaFisica compradorLider = pessoaFisicaRepo.obterPorIdPessoaFisica(compradorLiderId);
                    comprador.setCompradorLider(compradorLider);
                }

                Integer entidadeId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeId);
                comprador.setEntidadeGestora(entidadeGestora);

                return comprador;
            }
        }, entidade);

    }

    public Comprador salvarComprador(Comprador comprador) {

        KeyHolder holder = new GeneratedKeyHolder();

        if(comprador.getCompradorLider()!=null){
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_NOT_NULL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, comprador.getPessoa().getId());
                    ps.setDouble(2, comprador.getLimiteCompra());
                    ps.setInt(3, comprador.getAtivo());
                    ps.setInt(4, comprador.getCompradorLider().getId());
                    ps.setInt(5, comprador.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
        }else{
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT_NULL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, comprador.getPessoa().getId());
                    ps.setDouble(2, comprador.getLimiteCompra());
                    ps.setInt(3, comprador.getAtivo());
                    ps.setInt(4, comprador.getEntidadeGestora().getId());
                    return ps;
                }
            }, holder);
        }

        Integer id = (Integer) holder.getKeys().get("id");
        comprador.setId(id);

        return comprador;

    }

}
