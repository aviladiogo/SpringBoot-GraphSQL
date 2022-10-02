package com.mines.ClassificacaoMercadologica.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Model.PessoaJuridica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.EntidadeJuridica.Repository.PessoaJuridicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SecaoRepositoryImplGCF implements SecaoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_SECTION = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepo;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Secao obterPorIdSecao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        });

    }

    public List<Secao> obterTodosSecoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        }, entidade);

    }
    
    public List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento) {
        
        return jdbcTemplate.query(SELECT_ALL_SECTION, new RowMapper<Secao>() {
            @Override
            public Secao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Secao secao = new Secao();

                secao.setId(rs.getInt("id"));
                secao.setDescricao(rs.getString("descricao"));
                secao.setRegistro(rs.getDate("registro"));

                Integer departamentoId = rs.getInt("departamento");
                Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                secao.setDepartamento(departamento);

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                secao.setUsuario(usuario);

                Integer entidadeId = rs.getInt("entidade");
                PessoaJuridica entidade = pessoaJuridicaRepo.obterPorIdPessoaJuridica(entidadeId);
                secao.setEntidade(entidade);

                return secao;
            }
        }, entidade, departamento);

    }
  
}

