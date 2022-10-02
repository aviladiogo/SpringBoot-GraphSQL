package com.mines.Comprador.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Comprador.Model.Comprador;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CompradorRepositoryImplWinthor implements CompradorRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

}
