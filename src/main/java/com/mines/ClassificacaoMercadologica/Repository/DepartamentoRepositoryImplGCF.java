package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoRepositoryImplGCF implements DepartamentoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Departamento obterPorIdDepartamento(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Departamento>() {
            @Override
            public Departamento mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Departamento departamento = new Departamento();

                departamento.setId(rs.getInt("id"));
                departamento.setDescricao(rs.getString("descricao"));
                departamento.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                departamento.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                departamento.setEntidade(entidade);

                return departamento;
            }
        });

    }
    
    public List<Departamento> obterTodosDepartamentos(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Departamento>() {
            @Override
            public Departamento mapRow(ResultSet rs, int rownumber) throws SQLException {

                Departamento departamento = new Departamento();

                departamento.setId(rs.getInt("id"));
                departamento.setDescricao(rs.getString("descricao"));
                departamento.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                departamento.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                departamento.setEntidade(entidade);

                return departamento;
            }
        }, entidade);

    }
    
}

