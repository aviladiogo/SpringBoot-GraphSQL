package com.mines.Fornecedor.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import com.mines.Fornecedor.Model.TipoFrete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TipoFreteRepositoryImpl implements TipoFreteRepository {

    private static String SELECT_ALL = " select * from dx_tipofrete where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_tipofrete where id = ? ";
    private static String INSERT = " insert into dx_tipofrete (id, descricao, registro, usuario, "
            + " entidadegestora)  values (nextval('dx_tipofrete_id_seq'),?,?,?,?) ";
    private static String UPDATE = " update dx_tipofrete set descricao = ? where id = ? ";
    private static String DELETE = " delete from dx_tipofrete where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public TipoFrete atualizarTipoFrete(TipoFrete tipoFrete) {
       
        jdbcTemplate.update(UPDATE, new Object[] {tipoFrete.getDescricao(),tipoFrete.getId()});

        return tipoFrete;

    }

    
    public Boolean deletarTipoFrete(TipoFrete tipoFrete) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {tipoFrete.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
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

    public TipoFrete salvarTipoFrete(TipoFrete tipoFrete) {
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tipoFrete.getDescricao());
                ps.setDate(2, registroSQL);
                ps.setInt(3, tipoFrete.getUsuario().getId());
                ps.setInt(4, tipoFrete.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        tipoFrete.setId(id);

        return tipoFrete;

    }
    
}
