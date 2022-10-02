package com.mines.CurvaABCZ.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.ClasseProduto;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ClasseProdutoRepositoryImplWinthor implements ClasseProdutoRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public ClasseProduto obterPorIdClasseProduto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<ClasseProduto>() {
            @Override
            public ClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                ClasseProduto classeProduto = new ClasseProduto();

                classeProduto.setId(rs.getInt("id"));
                classeProduto.setTitulo(rs.getString("titulo"));
                classeProduto.setDescricao(rs.getString("descricao"));
                classeProduto.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                classeProduto.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                classeProduto.setEntidadeGestora(entidadeGestora);

                return classeProduto;
            }
        });

    }
    
    public List<ClasseProduto> obterTodosClasseProduto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ClasseProduto>() {
            @Override
            public ClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {

                ClasseProduto classeProduto = new ClasseProduto();

                classeProduto.setId(rs.getInt("id"));
                classeProduto.setTitulo(rs.getString("titulo"));
                classeProduto.setDescricao(rs.getString("descricao"));
                classeProduto.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                classeProduto.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                classeProduto.setEntidadeGestora(entidadeGestora);

                return classeProduto;
            }
        }, entidade);

    }
    
}

