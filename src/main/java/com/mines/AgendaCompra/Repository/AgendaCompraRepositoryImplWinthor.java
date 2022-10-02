package com.mines.AgendaCompra.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import com.mines.AgendaCompra.Model.AgendaCompra;
import com.mines.AgendaCompra.Model.FiltroAgendaCompraInput;
import com.mines.ClassificacaoMercadologica.Model.Categoria;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Model.SubCategoria;
import com.mines.ClassificacaoMercadologica.Repository.CategoriaRepository;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepository;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepository;
import com.mines.ClassificacaoMercadologica.Repository.SubCategoriaRepository;
import com.mines.Comprador.Model.Comprador;
import com.mines.Comprador.Repository.CompradorRepository;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Fornecedor.Model.Fornecedor;
import com.mines.Fornecedor.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AgendaCompraRepositoryImplWinthor implements AgendaCompraRepositoryWinthor {

    private static String SELECT_ALL = " ";
    private static String SELECT_ALL_FORNECEDOR = " ";
    private static String SELECT_ALL_POR_FILTRO = " ";
    private static String SELECT_ONE = " ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CompradorRepository compradorRepo;

    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired
    private SecaoRepository secaoRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private SubCategoriaRepository subCategoriaRepo;

    @Autowired
    private FornecedorRepository fornecedorRepo;

    @Autowired
    private EmpresaRepository empresaRepo;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public AgendaCompra obterPorIdAgendaCompra(Integer id) {

        return jdbcTemplate.queryForObject(SELECT_ONE, new Object[] { id }, new RowMapper<AgendaCompra>() {
            @Override
            public AgendaCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                AgendaCompra agendaCompra = new AgendaCompra();

                agendaCompra.setId(rs.getInt("id"));
                agendaCompra.setTitulo(rs.getString("titulo"));
                agendaCompra.setFrequencia(rs.getInt("frequencia"));
                agendaCompra.setInicioAgendaCompra(rs.getDate("inicioagendacompra"));
                agendaCompra.setTerminoAgendaCompra(rs.getDate("terminoagendacompra"));
                agendaCompra.setDomingo(rs.getInt("domingo"));
                agendaCompra.setSegunda(rs.getInt("segunda"));
                agendaCompra.setTerca(rs.getInt("terca"));
                agendaCompra.setQuarta(rs.getInt("quarta"));
                agendaCompra.setQuinta(rs.getInt("quinta"));
                agendaCompra.setSexta(rs.getInt("sexta"));
                agendaCompra.setSabado(rs.getInt("sabado"));
                agendaCompra.setAtivo(rs.getInt("ativo"));
                if (obterPorIdAgendaCompraFornecedor(agendaCompra.getId()) != null) {
                    agendaCompra.setFornecedores(obterPorIdAgendaCompraFornecedor(agendaCompra.getId()));
                }

                Integer compradorId = rs.getInt("comprador");
                Comprador comprador = compradorRepo.obterPorIdComprador(compradorId);
                agendaCompra.setComprador(comprador);

                Integer departamentoId = rs.getInt("departamento");
                if(departamentoId!=0){
                    Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                agendaCompra.setDepartamento(departamento);
                }

                Integer secaoId = rs.getInt("secao");
                if(secaoId!=0){
                    Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                    agendaCompra.setSecao(secao);
                }

                Integer categoriaId = rs.getInt("categoria");
                if(categoriaId!=0){
                    Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                    agendaCompra.setCategoria(categoria);
                }

                Integer subCategoriaId = rs.getInt("subcategoria");
                if(subCategoriaId!=0){
                    SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                    agendaCompra.setSubCategoria(subCategoria);
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                agendaCompra.setEntidadeGestora(entidadeGestora);

                return agendaCompra;
            }
        });

    }

    public List<AgendaCompra> obterTodosAgendaCompra(Integer entidade) {

        return jdbcTemplate.query(SELECT_ALL, new RowMapper<AgendaCompra>() {
            @Override
            public AgendaCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                AgendaCompra agendaCompra = new AgendaCompra();

                agendaCompra.setId(rs.getInt("id"));
                agendaCompra.setTitulo(rs.getString("titulo"));
                agendaCompra.setFrequencia(rs.getInt("frequencia"));
                agendaCompra.setInicioAgendaCompra(rs.getDate("inicioagendacompra"));
                agendaCompra.setTerminoAgendaCompra(rs.getDate("terminoagendacompra"));
                agendaCompra.setId(rs.getInt("id"));
                agendaCompra.setTitulo(rs.getString("titulo"));
                agendaCompra.setFrequencia(rs.getInt("frequencia"));
                agendaCompra.setInicioAgendaCompra(rs.getDate("inicioagendacompra"));
                agendaCompra.setTerminoAgendaCompra(rs.getDate("terminoagendacompra"));
                agendaCompra.setDomingo(rs.getInt("domingo"));
                agendaCompra.setSegunda(rs.getInt("segunda"));
                agendaCompra.setTerca(rs.getInt("terca"));
                agendaCompra.setQuarta(rs.getInt("quarta"));
                agendaCompra.setQuinta(rs.getInt("quinta"));
                agendaCompra.setSexta(rs.getInt("sexta"));
                agendaCompra.setSabado(rs.getInt("sabado"));
                agendaCompra.setAtivo(rs.getInt("ativo"));
                if (obterPorIdAgendaCompraFornecedor(agendaCompra.getId()) != null) {
                    agendaCompra.setFornecedores(obterPorIdAgendaCompraFornecedor(agendaCompra.getId()));
                }

                Integer compradorId = rs.getInt("comprador");
                Comprador comprador = compradorRepo.obterPorIdComprador(compradorId);
                agendaCompra.setComprador(comprador);

                Integer departamentoId = rs.getInt("departamento");
                if(departamentoId!=0){
                    Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                agendaCompra.setDepartamento(departamento);
                }

                Integer secaoId = rs.getInt("secao");
                if(secaoId!=0){
                    Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                    agendaCompra.setSecao(secao);
                }

                Integer categoriaId = rs.getInt("categoria");
                if(categoriaId!=0){
                    Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                    agendaCompra.setCategoria(categoria);
                }

                Integer subCategoriaId = rs.getInt("subcategoria");
                if(subCategoriaId!=0){
                    SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                    agendaCompra.setSubCategoria(subCategoria);
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                agendaCompra.setEntidadeGestora(entidadeGestora);

                return agendaCompra;
            }
        }, entidade);

    }

    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) {

        String SELECT = SELECT_ALL_POR_FILTRO;
        String listaFornecedores = " ";
        String comprador = " ";
        String dataInicio = " ";
        String dataFim = " ";
        String entidadeGestora = " ";

        entidadeGestora = " where entidadegestora = " + filtroAgendaCompraInput.getEntidadeGestora();
        SELECT = SELECT + entidadeGestora;
        if (filtroAgendaCompraInput.getComprador() != null) {

            comprador = " and comprador = " + filtroAgendaCompraInput.getComprador();
            SELECT = SELECT + comprador;
        }
        if (filtroAgendaCompraInput.getFornecedores() != null) {

            listaFornecedores = filtroAgendaCompraInput.getFornecedores().toString().replace("[", "").replace("]", "");
            SELECT = SELECT + " and fornecedor in (" + listaFornecedores + ") ";
        }
        if (filtroAgendaCompraInput.getDataInicio() != null) {

            dataInicio = " and inicioagendacompra between '" + filtroAgendaCompraInput.getDataInicio() + "'"
                    + " and '2100-12-31' ";
            SELECT = SELECT + dataInicio;
        }
        if (filtroAgendaCompraInput.getDataFim() != null) {

            dataFim = " and terminoagendacompra between '1900-01-01' and '" + filtroAgendaCompraInput.getDataFim()
                    + "' ";
            SELECT = SELECT + dataFim;
        }

        return jdbcTemplate.query(SELECT, new RowMapper<AgendaCompra>() {
            @Override
            public AgendaCompra mapRow(ResultSet rs, int rownumber) throws SQLException {

                AgendaCompra agendaCompra = new AgendaCompra();

                agendaCompra.setId(rs.getInt("id"));
                agendaCompra.setTitulo(rs.getString("titulo"));
                agendaCompra.setFrequencia(rs.getInt("frequencia"));
                agendaCompra.setInicioAgendaCompra(rs.getDate("inicioagendacompra"));
                agendaCompra.setTerminoAgendaCompra(rs.getDate("terminoagendacompra"));
                agendaCompra.setId(rs.getInt("id"));
                agendaCompra.setTitulo(rs.getString("titulo"));
                agendaCompra.setFrequencia(rs.getInt("frequencia"));
                agendaCompra.setInicioAgendaCompra(rs.getDate("inicioagendacompra"));
                agendaCompra.setTerminoAgendaCompra(rs.getDate("terminoagendacompra"));
                agendaCompra.setDomingo(rs.getInt("domingo"));
                agendaCompra.setSegunda(rs.getInt("segunda"));
                agendaCompra.setTerca(rs.getInt("terca"));
                agendaCompra.setQuarta(rs.getInt("quarta"));
                agendaCompra.setQuinta(rs.getInt("quinta"));
                agendaCompra.setSexta(rs.getInt("sexta"));
                agendaCompra.setSabado(rs.getInt("sabado"));
                agendaCompra.setAtivo(rs.getInt("ativo"));
                if (obterPorIdAgendaCompraFornecedor(agendaCompra.getId()) != null) {
                    agendaCompra.setFornecedores(obterPorIdAgendaCompraFornecedor(agendaCompra.getId()));
                }

                Integer compradorId = rs.getInt("comprador");
                Comprador comprador = compradorRepo.obterPorIdComprador(compradorId);
                agendaCompra.setComprador(comprador);

                Integer departamentoId = rs.getInt("departamento");
                if (departamentoId != 0) {
                    Departamento departamento = departamentoRepo.obterPorIdDepartamento(departamentoId);
                    agendaCompra.setDepartamento(departamento);
                }

                Integer secaoId = rs.getInt("secao");
                if (secaoId != 0) {
                    Secao secao = secaoRepo.obterPorIdSecao(secaoId);
                    agendaCompra.setSecao(secao);
                }

                Integer categoriaId = rs.getInt("categoria");
                if (categoriaId != 0) {
                    Categoria categoria = categoriaRepo.obterPorIdCategoria(categoriaId);
                    agendaCompra.setCategoria(categoria);
                }

                Integer subCategoriaId = rs.getInt("subcategoria");
                if (subCategoriaId != 0) {
                    SubCategoria subCategoria = subCategoriaRepo.obterPorIdSubCategoria(subCategoriaId);
                    agendaCompra.setSubCategoria(subCategoria);
                }

                Integer entidadeGestoraId = rs.getInt("entidadegestora");
                Empresa entidadeGestora = empresaRepo.obterPorIdEmpresa(entidadeGestoraId);
                agendaCompra.setEntidadeGestora(entidadeGestora);

                return agendaCompra;
            }
        });

    }

    public List<Fornecedor> obterPorIdAgendaCompraFornecedor(Integer agendaCompra) {

        return jdbcTemplate.query(SELECT_ALL_FORNECEDOR, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);

                return fornecedor;
            }
        }, agendaCompra);

    }

}
