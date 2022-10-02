package com.mines.PrecoEOferta.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticaItemOfertaRepositoryImplWinthor implements PoliticaItemOfertaRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ONE = " ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PoliticaItemOferta obterPorIdPoliticaItemOferta(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<PoliticaItemOferta>() {
            @Override
            public PoliticaItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                PoliticaItemOferta politicaItemOferta = new PoliticaItemOferta();

                politicaItemOferta.setId(rs.getInt("id"));
                politicaItemOferta.setItemOferta(rs.getInt("itemoferta"));
                politicaItemOferta.setQuantidadeMinima(rs.getInt("quantidademinima"));
                politicaItemOferta.setQuantidadeMaxima(rs.getInt("quantidademaxima"));
                politicaItemOferta.setPrecoUnitario(rs.getDouble("precounitario"));
                politicaItemOferta.setPercentualDesconto(rs.getDouble("percentualdesconto"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                politicaItemOferta.setEntidadeGestora(entidadeGestora);

                return politicaItemOferta;
            }
        });

    }
    
    public List<PoliticaItemOferta> obterTodosPoliticaItensOferta(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<PoliticaItemOferta>() {
            @Override
            public PoliticaItemOferta mapRow(ResultSet rs, int rownumber) throws SQLException {

                PoliticaItemOferta politicaItemOferta = new PoliticaItemOferta();

                politicaItemOferta.setId(rs.getInt("id"));
                politicaItemOferta.setItemOferta(rs.getInt("itemoferta"));
                politicaItemOferta.setQuantidadeMinima(rs.getInt("quantidademinima"));
                politicaItemOferta.setQuantidadeMaxima(rs.getInt("quantidademaxima"));
                politicaItemOferta.setPrecoUnitario(rs.getDouble("precounitario"));
                politicaItemOferta.setPercentualDesconto(rs.getDouble("percentualdesconto"));

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                politicaItemOferta.setEntidadeGestora(entidadeGestora);

                return politicaItemOferta;
            }
        }, entidade);

    }
    
}

