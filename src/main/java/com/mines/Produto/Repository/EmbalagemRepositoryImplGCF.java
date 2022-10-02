package com.mines.Produto.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Produto.Model.Embalagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmbalagemRepositoryImplGCF implements EmbalagemRepositoryGCF {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Embalagem obterPorIdEmbalagem(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Embalagem>() {
            @Override
            public Embalagem mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Embalagem embalagem = new Embalagem();

                embalagem.setId(rs.getInt("id"));
                embalagem.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                embalagem.setEntidadeGestora(entidadeGestora);
                
                return embalagem;
            }
        });

    }

    public List<Embalagem> obterTodosEmbalagens(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Embalagem>() {
            @Override
            public Embalagem mapRow(ResultSet rs, int rownumber) throws SQLException {

                Embalagem embalagem = new Embalagem();

                embalagem.setId(rs.getInt("id"));
                embalagem.setDescricao(rs.getString("descricao"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                embalagem.setEntidadeGestora(entidadeGestora);
                
                return embalagem;
            }
        }, entidade);

    }
    
}
