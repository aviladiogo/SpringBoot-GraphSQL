package com.mines.EntidadeJuridica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaFisicaRepositoryImplWinthor implements PessoaFisicaRepositoryWinthor{
    
    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jbdcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jbdcTemplate = new JdbcTemplate(dataSource);
    }

    public PessoaFisica obterPorIdPessoaFisica(Integer id) {

        return jbdcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<PessoaFisica>() {
            @Override
            public PessoaFisica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setApelido(rs.getString("apelido"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setEmail(rs.getString("email"));

                Integer entidadeId = rs.getInt("entidade");
                Empresa entidade = empresaRepo.obterPorIdEmpresa(entidadeId);
                pessoaFisica.setEntidade(entidade);

                return pessoaFisica;
            }
        });

    }

    public List<PessoaFisica> obterTodosPessoasFisica(Integer entidade) {

        return jbdcTemplate.query(SELECT_ALL, new RowMapper<PessoaFisica>() {
            @Override
            public PessoaFisica mapRow(ResultSet rs, int rownumber) throws SQLException {

                PessoaFisica pessoaFisica = new PessoaFisica();

                pessoaFisica.setId(rs.getInt("id"));
                pessoaFisica.setNome(rs.getString("nome"));
                pessoaFisica.setApelido(rs.getString("apelido"));
                pessoaFisica.setCpf(rs.getString("cpf"));
                pessoaFisica.setEmail(rs.getString("email"));

                Integer entidadeId = rs.getInt("entidade");
                Empresa entidade = empresaRepo.obterPorIdEmpresa(entidadeId);
                pessoaFisica.setEntidade(entidade);

                return pessoaFisica;
            }
        }, entidade);
    }

}
