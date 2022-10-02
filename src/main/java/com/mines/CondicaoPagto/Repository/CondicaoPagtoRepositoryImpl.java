package com.mines.CondicaoPagto.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CondicaoPagto.Model.CondicaoPagto;
import com.mines.CondicaoPagto.Model.PrazoParcela;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CondicaoPagtoRepositoryImpl implements CondicaoPagtoRepository {

    private static String SELECT_ALL = " select * from dx_condicaopagto where entidadegestora = ? "
            + " order by descricao";
    private static String SELECT_ONE = " select * from dx_condicaopagto where id = ? ";
    private static String INSERT = " insert into dx_condicaopagto (id, titulo, descricao, parcelas, "
            + " prazoparcelas, entidadegestora) "
            + " values (nextval('dx_condicaopagto_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_condicaopagto set titulo = ?, descricao = ?, parcelas = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_condicaopagto where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PrazoParcelaRepository prazoEntregaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public CondicaoPagto atualizarCondicaoPagto(CondicaoPagto condicaoPagto) {
       
        jdbcTemplate.update(UPDATE, new Object[] {condicaoPagto.getTitulo(),condicaoPagto.getDescricao(),
            condicaoPagto.getParcelas(), condicaoPagto.getId()});

        return condicaoPagto;

    }

    
    public Boolean deletarCondicaoPagto(CondicaoPagto condicaoPagto) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {condicaoPagto.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public CondicaoPagto obterPorIdCondicaoPagto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<CondicaoPagto>() {
            @Override
            public CondicaoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                CondicaoPagto condicaoPagto = new CondicaoPagto();

                condicaoPagto.setId(rs.getInt("id"));
                condicaoPagto.setTitulo(rs.getString("titulo"));
                condicaoPagto.setDescricao(rs.getString("descricao"));
                condicaoPagto.setParcelas(rs.getInt("parcelas"));

                Integer prazoParcelasId = rs.getInt("prazoparcelas");
                PrazoParcela prazoParcelas = prazoEntregaRepo.obterPorIdPrazoParcela(prazoParcelasId);
                condicaoPagto.setPrazoParcelas(prazoParcelas);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                condicaoPagto.setEntidadeGestora(entidadeGestora);

                return condicaoPagto;
            }
        });

    }
    
    public List<CondicaoPagto> obterTodosCondicoesPagto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CondicaoPagto>() {
            @Override
            public CondicaoPagto mapRow(ResultSet rs, int rownumber) throws SQLException {

                CondicaoPagto condicaoPagto = new CondicaoPagto();

                condicaoPagto.setId(rs.getInt("id"));
                condicaoPagto.setTitulo(rs.getString("titulo"));
                condicaoPagto.setDescricao(rs.getString("descricao"));
                condicaoPagto.setParcelas(rs.getInt("parcelas"));

                Integer prazoParcelasId = rs.getInt("prazoparcelas");
                PrazoParcela prazoParcelas = prazoEntregaRepo.obterPorIdPrazoParcela(prazoParcelasId);
                condicaoPagto.setPrazoParcelas(prazoParcelas);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                condicaoPagto.setEntidadeGestora(entidadeGestora);

                return condicaoPagto;
            }
        }, entidade);

    }

    public CondicaoPagto salvarCondicaoPagto(CondicaoPagto condicaoPagto) {

        KeyHolder holder = new GeneratedKeyHolder();
        
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, condicaoPagto.getTitulo());
                    ps.setString(2, condicaoPagto.getDescricao());
                    ps.setInt(3, condicaoPagto.getParcelas());
                    ps.setInt(4, condicaoPagto.getPrazoParcelas().getId());
                    ps.setInt(5, condicaoPagto.getEntidadeGestora().getId());
                    
                    return ps;
                }
            }, holder);
    
            Integer id = (Integer) holder.getKeys().get("id");
            condicaoPagto.setId(id);
    
            return condicaoPagto;

    }
    
}
