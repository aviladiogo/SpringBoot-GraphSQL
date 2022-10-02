package com.mines.AgendaCompra.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AgendaCompraRepositoryImpl implements AgendaCompraRepository {

    private static String SELECT_ALL = " select * from dx_agendacompra where entidadegestora = ?";
    private static String SELECT_ALL_POR_FILTRO = " select * from dx_agendacompra as dx_ac ";
    private static String SELECT_ALL_FORNECEDOR_POR_AGENDA_COMPRA = " select * from dx_agendacompra_fornecedor "
            + " where agendacompra = ?";
    private static String SELECT_ALL_FORNECEDOR_POR_FILTRO = " select distinct dx_acf.agendacompra from dx_agendacompra as dx_ac "
            + " inner join dx_agendacompra_fornecedor as dx_acf on dx_ac.id = dx_acf.agendacompra ";
    private static String SELECT_ONE = " select * from dx_agendacompra where id = ? ";
    private static String INSERT = " insert into dx_agendacompra (id, titulo, comprador, frequencia, inicioagendacompra, "
            + " terminoagendacompra, domingo, segunda, terca, quarta, quinta, sexta, sabado, ativo, departamento, "
            + " secao, categoria, subcategoria, entidadegestora) "
            + " values (nextval('dx_agendacompra_id_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    private static String INSERT_FORNECEDOR = " insert into dx_agendacompra_fornecedor (agendacompra, "
            + " fornecedor) values (?,?)";
    private static String UPDATE = " update dx_agendacompra set titulo = ?, frequencia = ?, domingo = ?, segunda = ?, "
            + " terca = ?, quarta = ?, quinta = ?, sexta = ?, sabado = ?, ativo = ? where id = ? ";
    private static String DELETE = " delete from dx_agendacompra where id = ? ";
    private static String DELETE_FORNECEDOR = "delete from dx_agendacompra_fornecedor where agendacompra = ?";

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

    public AgendaCompra atualizarAgendaCompra(AgendaCompra agendaCompra) {

        jdbcTemplate.update(UPDATE,
                new Object[] { agendaCompra.getTitulo(), agendaCompra.getFrequencia(), agendaCompra.getDomingo(),
                        agendaCompra.getSegunda(), agendaCompra.getTerca(), agendaCompra.getQuarta(),
                        agendaCompra.getQuinta(), agendaCompra.getSexta(), agendaCompra.getSabado(),
                        agendaCompra.getAtivo(), agendaCompra.getId() });

        return agendaCompra;
    }

    public Boolean deletarAgendaCompra(AgendaCompra agendaCompra) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE, new Object[] { agendaCompra.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

    }

    public Boolean deletarAgendaCompraFornecedor(AgendaCompra agendaCompra) {

        Boolean ret = false;

        Integer rows = jdbcTemplate.update(DELETE_FORNECEDOR, new Object[] { agendaCompra.getId() });

        if (rows > 0) {
            ret = true;
        }

        return ret;

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
        }, entidade);

    }

    public List<AgendaCompra> obterTodosAgendaCompraPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput)
            throws ParseException {

        String SELECT = SELECT_ALL_POR_FILTRO;
        String listaFornecedores = " ";
        String comprador = " ";
        String listaDatas = " ";
        String entidadeGestora = " ";

        entidadeGestora = " where entidadegestora = " + filtroAgendaCompraInput.getEntidadeGestora();
        SELECT = SELECT + entidadeGestora;
        List<AgendaCompra> datasAgendaCompraPorId = obterTodosAgendaCompra(
                filtroAgendaCompraInput.getEntidadeGestora());
        listaDatas = listaDatas
                + obterDatasPorIdAgendaCompra(datasAgendaCompraPorId, filtroAgendaCompraInput);

        if (filtroAgendaCompraInput.getComprador() != null) {

            comprador = " and comprador = " + filtroAgendaCompraInput.getComprador();
            SELECT = SELECT + comprador;
        }
        if (filtroAgendaCompraInput.getFornecedores() != null) {

            List<Integer> listaFornecedoresAux = obterTodosAgendaCompraFornecedorPorFiltro(filtroAgendaCompraInput);
            listaFornecedores = listaFornecedoresAux.toString().replace("[", "").replace("]", "");
            if (listaFornecedores.equals("")) {
                SELECT = SELECT + " and id  = 0";
            } else {
                SELECT = SELECT + " and id in (" + listaFornecedores + ") ";
            }
        }

        SELECT = SELECT + listaDatas;

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

        return jdbcTemplate.query(SELECT_ALL_FORNECEDOR_POR_AGENDA_COMPRA, new RowMapper<Fornecedor>() {
            @Override
            public Fornecedor mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer fornecedorId = rs.getInt("fornecedor");
                Fornecedor fornecedor = fornecedorRepo.obterPorIdFornecedor(fornecedorId);

                return fornecedor;
            }
        }, agendaCompra);

    }

    public List<Integer> obterTodosAgendaCompraFornecedorPorFiltro(FiltroAgendaCompraInput filtroAgendaCompraInput) {

        String SELECT = SELECT_ALL_FORNECEDOR_POR_FILTRO;
        String listaFornecedores = " ";

        listaFornecedores = filtroAgendaCompraInput.getFornecedores().toString().replace("[", "").replace("]", "");
        SELECT = SELECT + " and fornecedor in (" + listaFornecedores + ") order by dx_acf.agendacompra ";

        return jdbcTemplate.query(SELECT, new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet rs, int rownumber) throws SQLException {

                Integer agendaCompra = rs.getInt("agendacompra");
                return agendaCompra;
            }
        });

    }

    public String obterDatasPorIdAgendaCompra(List<AgendaCompra> datasAgendaCompraPorId,
            FiltroAgendaCompraInput filtroAgendaCompraInput) throws ParseException {

        String SELECT = " ";
        String listaDatasPorIdAgendaCompra = " ";
        List<Integer> listaAux = new ArrayList<Integer>();

        if (filtroAgendaCompraInput.getDataInicio() != null && filtroAgendaCompraInput.getDataFim() != null) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = dateFormat.parse(filtroAgendaCompraInput.getDataInicio());
            Date dataFim = dateFormat.parse(filtroAgendaCompraInput.getDataFim());

            for (int i = 0; i < datasAgendaCompraPorId.size(); i++) {
                if (dataInicio.after(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataInicio.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    if (dataFim.after(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                            && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                        listaAux.add(datasAgendaCompraPorId.get(i).getId());

                    } else if (dataFim.before(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                            && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                        listaAux.add(datasAgendaCompraPorId.get(i).getId());
                    }
                } else if (dataInicio.before(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataInicio.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    if (dataFim.after(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                            && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                        listaAux.add(datasAgendaCompraPorId.get(i).getId());
                    }

                    else if (dataFim.before(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                            && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                        listaAux.add(datasAgendaCompraPorId.get(i).getId());
                    }
                }
            }

            listaDatasPorIdAgendaCompra = listaAux.toString().replace("[", "").replace("]", "");
            if (listaDatasPorIdAgendaCompra.equals("")) {
                SELECT = SELECT + " and id = 0 ";
            } else {
                SELECT = SELECT + " and id in (" + listaDatasPorIdAgendaCompra + ") ";
            }
        }

        else if (filtroAgendaCompraInput.getDataInicio() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataInicio = dateFormat.parse(filtroAgendaCompraInput.getDataInicio());

            for (int i = 0; i < datasAgendaCompraPorId.size(); i++) {
                if (dataInicio.after(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataInicio.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    listaAux.add(datasAgendaCompraPorId.get(i).getId());

                } else if (dataInicio.before(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataInicio.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    listaAux.add(datasAgendaCompraPorId.get(i).getId());

                }
            }

            listaDatasPorIdAgendaCompra = listaAux.toString().replace("[", "").replace("]", "");
            if (listaDatasPorIdAgendaCompra.equals("")) {
                SELECT = SELECT + " and id = 0 ";
            } else {
                SELECT = SELECT + " and id in (" + listaDatasPorIdAgendaCompra + ") ";
            }
        }

        else if (filtroAgendaCompraInput.getDataFim() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataFim = dateFormat.parse(filtroAgendaCompraInput.getDataFim());

            for (int i = 0; i < datasAgendaCompraPorId.size(); i++) {
                if (dataFim.after(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    listaAux.add(datasAgendaCompraPorId.get(i).getId());

                } else if (dataFim.before(datasAgendaCompraPorId.get(i).getInicioAgendaCompra())
                        && dataFim.before(datasAgendaCompraPorId.get(i).getTerminoAgendaCompra())) {

                    listaAux.add(datasAgendaCompraPorId.get(i).getId());
                }
            }

            listaDatasPorIdAgendaCompra = listaAux.toString().replace("[", "").replace("]", "");
            if (listaDatasPorIdAgendaCompra.equals("")) {
                SELECT = SELECT + " and id = 0 ";
            } else {
                SELECT = SELECT + " and id in (" + listaDatasPorIdAgendaCompra + ") ";
            }
        }

        return SELECT;

    }

    public AgendaCompra salvarAgendaCompra(AgendaCompra agendaCompra) {

        KeyHolder holder = new GeneratedKeyHolder();

        agendaCompra.getInicioAgendaCompra().setDate(agendaCompra.getInicioAgendaCompra().getDate() + 1);
        if (agendaCompra.getTerminoAgendaCompra() != null) {
            agendaCompra.getTerminoAgendaCompra().setDate(agendaCompra.getTerminoAgendaCompra().getDate() + 1);
        }

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, agendaCompra.getTitulo());
                ps.setInt(2, agendaCompra.getComprador().getId());
                ps.setInt(3, agendaCompra.getFrequencia());
                java.sql.Date inicioAgendaCompra = new java.sql.Date(agendaCompra.getInicioAgendaCompra().getTime());
                ps.setDate(4, inicioAgendaCompra);
                if (agendaCompra.getTerminoAgendaCompra() != null) {
                    java.sql.Date terminoAgendaCompra = new java.sql.Date(
                            agendaCompra.getTerminoAgendaCompra().getTime());
                    ps.setDate(5, terminoAgendaCompra);
                }else{
                    ps.setDate(5, null);
                }
                ps.setInt(6, agendaCompra.getDomingo());
                ps.setInt(7, agendaCompra.getSegunda());
                ps.setInt(8, agendaCompra.getTerca());
                ps.setInt(9, agendaCompra.getQuarta());
                ps.setInt(10, agendaCompra.getQuinta());
                ps.setInt(11, agendaCompra.getSexta());
                ps.setInt(12, agendaCompra.getSabado());
                ps.setInt(13, agendaCompra.getAtivo());
                ps.setInt(14, agendaCompra.getDepartamento().getId());
                ps.setInt(15, agendaCompra.getSecao().getId());
                ps.setInt(16, agendaCompra.getCategoria().getId());
                ps.setInt(16, agendaCompra.getCategoria().getId());
                ps.setInt(17, agendaCompra.getSubCategoria().getId());
                ps.setInt(18, agendaCompra.getEntidadeGestora().getId());
                return ps;
            }
        }, holder);

        Integer id = (Integer) holder.getKeys().get("id");
        agendaCompra.setId(id);

        return agendaCompra;
    }

    public void salvarAgendaCompraFornecedor(Integer agendaCompra, List<Integer> listaFornecedores) {

        for (int i = 0; i < listaFornecedores.size(); i++) {
            jdbcTemplate.update(INSERT_FORNECEDOR, new Object[] { agendaCompra, listaFornecedores.get(i) });
        }

    }

}
