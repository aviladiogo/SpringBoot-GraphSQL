package com.mines.PrecoEOferta.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.PrecoEOferta.Model.PoliticaItemOferta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticaItemOfertaRepositoryImpl implements PoliticaItemOfertaRepository {

    private static String SELECT_ALL = " select * from dx_politicaitemoferta where entidadegestora=?";
    private static String SELECT_ONE = " select * from dx_politicaitemoferta where id = ? ";
    private static String INSERT = " insert into dx_politicaitemoferta (id, itemoferta, quantidademinima, "
            + " quantidademaxima, precounitario, percentualdesconto, entidadegestora) "
            + " values (nextval('dx_politicaitemoferta_id_seq'),?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_politicaitemoferta set itemoferta = ?, quantidademinima = ?, "
            + " quantidademaxima = ?, precounitario = ?, percentualdesconto = ? where id = ? ";
    private static String DELETE = " delete from dx_politicaitemoferta where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public PoliticaItemOferta atualizarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta) {
       
        jdbcTemplate.update(UPDATE, new Object[] {politicaItemOferta.getItemOferta(), 
            politicaItemOferta.getQuantidadeMinima(), politicaItemOferta.getQuantidadeMaxima(), 
            politicaItemOferta.getPrecoUnitario(), politicaItemOferta.getPercentualDesconto(), 
            politicaItemOferta.getId()});

        return politicaItemOferta;

    }

    
    public Boolean deletarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {politicaItemOferta.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public PoliticaItemOferta salvarPoliticaItemOferta(PoliticaItemOferta politicaItemOferta) {

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, politicaItemOferta.getItemOferta());
                ps.setInt(2, politicaItemOferta.getQuantidadeMinima());
                ps.setInt(3, politicaItemOferta.getQuantidadeMaxima());
                ps.setDouble(4, politicaItemOferta.getPrecoUnitario());
                ps.setDouble(5, politicaItemOferta.getPercentualDesconto());
                ps.setInt(6, politicaItemOferta.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        politicaItemOferta.setId(id);
            
        return politicaItemOferta;

    }
    
}

