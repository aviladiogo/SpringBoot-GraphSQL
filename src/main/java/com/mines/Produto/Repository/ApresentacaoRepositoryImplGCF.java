package com.mines.Produto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Apresentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ApresentacaoRepositoryImplGCF implements ApresentacaoRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Apresentacao obterPorIdApresentacao(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Apresentacao>() {
            @Override
            public Apresentacao mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Apresentacao apresentacao = new Apresentacao();

                apresentacao.setId(rs.getInt("id"));
                apresentacao.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                apresentacao.setEntidadeGestora(entidadeGestora);
                
                return apresentacao;
            }
        });

    }
    
    public List<Apresentacao> obterTodosApresentacoes(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Apresentacao>() {
            @Override
            public Apresentacao mapRow(ResultSet rs, int rownumber) throws SQLException {

                Apresentacao apresentacao = new Apresentacao();

                apresentacao.setId(rs.getInt("id"));
                apresentacao.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                apresentacao.setEntidadeGestora(entidadeGestora);
                
                return apresentacao;
            }
        }, entidade);

    }
    
}
