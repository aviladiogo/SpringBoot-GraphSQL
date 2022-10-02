package com.mines.Empresa.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.Empresa.Model.Empresa;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class EmpresaRepositoryImpl implements EmpresaRepository {

    private static String SELECT_ALL = " select * from dx_empresa where entidadegestora = ? ";
    private static String SELECT_ONE = " select * from dx_empresa where id = ? ";
    private static String INSERT = " insert into dx_empresa (id, nomefantasia, razaosocial, cnpj, registro, "
            + " usuario, ativa, entidadegestora) "
            + " values (nextval('dx_empresa_id_seq'),?,?,?,?,?,?,?) ";
    private static String UPDATE = " update dx_empresa set nomefantasia = ?, razaosocial = ?, cnpj = ?, "
            + " ativa = ? where id = ? ";
    private static String DELETE = " delete from dx_empresa where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Lazy
    private PessoaFisicaRepository pessoaFisicaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public Empresa atualizarEmpresa(Empresa empresa) {
       
        jdbcTemplate.update(UPDATE, new Object[] {empresa.getNomeFantasia(), empresa.getRazaoSocial(),
            empresa.getCnpj(), empresa.getAtiva(), empresa.getId()});

        return empresa;

    }

    
    public Boolean deletarEmpresa(Empresa empresa) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {empresa.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public Empresa obterPorIdEmpresa(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<Empresa>() {
            @Override
            public Empresa mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("id"));
                empresa.setNomeFantasia(rs.getString("nomefantasia"));
                empresa.setRazaoSocial(rs.getString("razaosocial"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setAtiva(rs.getInt("ativa"));
                empresa.setRegistro(rs.getDate("registro"));
                empresa.setEntidadeGestora(rs.getInt("entidadegestora"));

                //Integer usuarioId = rs.getInt("usuario");
                //PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                //empresa.setUsuario(usuario);
                
                return empresa;
            }
        });

    }
    
    public List<Empresa> obterTodosEmpresas(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Empresa>() {
            @Override
            public Empresa mapRow(ResultSet rs, int rownumber) throws SQLException {

                Empresa empresa = new Empresa();

                empresa.setId(rs.getInt("id"));
                empresa.setNomeFantasia(rs.getString("nomefantasia"));
                empresa.setRazaoSocial(rs.getString("razaosocial"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setAtiva(rs.getInt("ativa"));
                empresa.setRegistro(rs.getDate("registro"));
                empresa.setEntidadeGestora(rs.getInt("entidadegestora"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                empresa.setUsuario(usuario);
                
                return empresa;
            }
        }, entidade);

    }

    public Empresa salvarEmpresa(Empresa empresa) {
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, empresa.getNomeFantasia());
                ps.setString(2, empresa.getRazaoSocial());
                ps.setString(3, empresa.getCnpj());
                ps.setDate(4, registroSQL);
                ps.setInt(5, empresa.getUsuario().getId());
                ps.setInt(6, empresa.getAtiva());
                ps.setInt(7, empresa.getEntidadeGestora());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        empresa.setId(id);
            
        return empresa;


    }

    
}
