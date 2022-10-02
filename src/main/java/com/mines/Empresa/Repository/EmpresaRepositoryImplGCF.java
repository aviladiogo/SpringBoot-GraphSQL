package com.mines.Empresa.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmpresaRepositoryImplGCF implements EmpresaRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Lazy
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Empresa obterPorIdEmpresa(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Empresa>() {
            @Override
            public Empresa mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("id"));
                empresa.setNomeFantasia(rs.getString("nomefantasia"));
                empresa.setRazaoSocial(rs.getString("razaosocial"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setAtiva(rs.getInt("ativa"));
                empresa.setRegistro(rs.getDate("registro"));
                empresa.setEntidadeGestora(rs.getInt("entidadegestora"));

                //Integer usuarioId = rs.getInt("usuario");
                //PessoaFisica usuario = pessoaFisicaRepo.obterPorId(usuarioId);
                //empresa.setUsuario(usuario);
                
                return empresa;
            }
        });

    }
    
    public List<Empresa> obterTodosEmpresas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Empresa>() {
            @Override
            public Empresa mapRow(ResultSet rs, int rownumber) throws SQLException {

                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("id"));
                empresa.setNomeFantasia(rs.getString("nomefantasia"));
                empresa.setRazaoSocial(rs.getString("razaosocial"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setAtiva(rs.getInt("ativa"));
                empresa.setRegistro(rs.getDate("registro"));
                empresa.setEntidadeGestora(rs.getInt("entidadegestora"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                empresa.setUsuario(usuario);
                
                return empresa;
            }
        }, entidade);

    }
    
}
