package com.mines.Produto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Unidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UnidadeRepositoryImplWinthor implements UnidadeRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Unidade obterPorIdUnidade(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Unidade>() {
            @Override
            public Unidade mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Unidade unidade = new Unidade();

                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                unidade.setEntidadeGestora(entidadeGestora);
                    
                return unidade;
            }
        });

    }
    
    public List<Unidade> obterTodosUnidades(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Unidade>() {
            @Override
            public Unidade mapRow(ResultSet rs, int rownumber) throws SQLException {

                Unidade unidade = new Unidade();

                unidade.setId(rs.getInt("id"));
                unidade.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                unidade.setEntidadeGestora(entidadeGestora);
                    
                return unidade;
            }
        }, entidade);

    }
    
}
