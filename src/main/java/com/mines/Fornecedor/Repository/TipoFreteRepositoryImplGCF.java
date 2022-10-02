package com.mines.Fornecedor.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Fornecedor.Model.TipoFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFreteRepositoryImplGCF implements TipoFreteRepositoryGCF {

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
    
    public TipoFrete obterPorIdTipoFrete(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<TipoFrete>() {
            @Override
            public TipoFrete mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                TipoFrete tipoFrete = new TipoFrete();

                tipoFrete.setId(rs.getInt("id"));
                tipoFrete.setRegistro(rs.getDate("registro"));
                tipoFrete.setDescricao(rs.getString("descricao"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                tipoFrete.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoFrete.setEntidadeGestora(entidadeGestora);

                return tipoFrete;
            }
        });

    }
    
    public List<TipoFrete> obterTodosTiposFrete(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<TipoFrete>() {
            @Override
            public TipoFrete mapRow(ResultSet rs, int rownumber) throws SQLException {

                TipoFrete tipoFrete = new TipoFrete();

                tipoFrete.setId(rs.getInt("id"));
                tipoFrete.setRegistro(rs.getDate("registro"));
                tipoFrete.setDescricao(rs.getString("descricao"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                tipoFrete.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                tipoFrete.setEntidadeGestora(entidadeGestora);

                return tipoFrete;
            }
        }, entidade);

    }
    
}

