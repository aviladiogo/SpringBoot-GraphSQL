package com.mines.CurvaABCZ.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import com.mines.CurvaABCZ.Model.ClasseProduto;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.EntidadeJuridica.Model.PessoaFisica;
import com.mines.EntidadeJuridica.Repository.PessoaFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ClasseProdutoRepositoryImpl implements ClasseProdutoRepository {

    private static String SELECT_ALL = " select * from dx_classeproduto where entidadegestora = ? "
            + " order by descricao ";
    private static String SELECT_ONE = " select * from dx_classeproduto where id = ? ";
    private static String INSERT = " insert into dx_classeproduto (id, titulo, descricao, registro, usuario, "
            + " entidadegestora) values (nextval('dx_classeproduto_id_seq'),?,?,?,?,?) ";
    private static String UPDATE = " update dx_classeproduto set titulo = ?, descricao = ? "
            + " where id = ? ";
    private static String DELETE = " delete from dx_classeproduto where id = ? ";
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public ClasseProduto atualizarClasseProduto(ClasseProduto classeProduto) {
       
        jdbcTemplate.update(UPDATE, new Object[] {classeProduto.getTitulo(), classeProduto.getDescricao(),
            classeProduto.getId()});

        return classeProduto;

    }

    
    public Boolean deletarClasseProduto(ClasseProduto classeProduto) {

        Boolean ret = false;
        
        Integer rows = jdbcTemplate.update(DELETE, new Object[] {classeProduto.getId()});

        if(rows > 0){
            ret = true;
        }

        return ret;
        
    }

    
    public ClasseProduto obterPorIdClasseProduto(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] {id}, new RowMapper<ClasseProduto>() {
            @Override
            public ClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {
                
                ClasseProduto classeProduto = new ClasseProduto();

                classeProduto.setId(rs.getInt("id"));
                classeProduto.setTitulo(rs.getString("titulo"));
                classeProduto.setDescricao(rs.getString("descricao"));
                classeProduto.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                classeProduto.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                classeProduto.setEntidadeGestora(entidadeGestora);

                return classeProduto;
            }
        });

    }
    
    public List<ClasseProduto> obterTodosClasseProduto(Integer entidade) {
        
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<ClasseProduto>() {
            @Override
            public ClasseProduto mapRow(ResultSet rs, int rownumber) throws SQLException {

                ClasseProduto classeProduto = new ClasseProduto();

                classeProduto.setId(rs.getInt("id"));
                classeProduto.setTitulo(rs.getString("titulo"));
                classeProduto.setDescricao(rs.getString("descricao"));
                classeProduto.setRegistro(rs.getDate("registro"));

                Integer usuarioId = rs.getInt("usuario");
                PessoaFisica usuario = pessoaFisicaRepo.obterPorIdPessoaFisica(usuarioId);
                classeProduto.setUsuario(usuario);

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                classeProduto.setEntidadeGestora(entidadeGestora);

                return classeProduto;
            }
        }, entidade);

    }

    public ClasseProduto salvarClasseProduto(ClasseProduto classeProduto) {
        
        Date registro = new Date();
        java.sql.Date registroSQL = new java.sql.Date(registro.getTime());

        KeyHolder holder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, classeProduto.getTitulo());
                ps.setString(2, classeProduto.getDescricao());
                ps.setDate(3, registroSQL);
                ps.setInt(4, classeProduto.getUsuario().getId());
                ps.setInt(5, classeProduto.getEntidadeGestora().getId());
                
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        classeProduto.setId(id);
            
        return classeProduto;

    }

    
}

