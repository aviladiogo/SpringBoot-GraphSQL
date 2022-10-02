package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Departamento;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepository;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepositoryGCF;
import com.mines.ClassificacaoMercadologica.Repository.DepartamentoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {
    
    @Autowired
    private DepartamentoRepository departamentoRepo;

    @Autowired 
    private DepartamentoRepositoryWinthor departamentoRepoWinthor;

    @Autowired
    private DepartamentoRepositoryGCF departamentoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Departamento> obterTodosDepartamentos(Integer entidade) {

        List<Departamento> departamento = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                departamento = departamentoRepo.obterTodosDepartamentos(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                departamento = departamentoRepoWinthor.obterTodosDepartamentos(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                departamento = departamentoRepoGCF.obterTodosDepartamentos(entidade);
                break;
            }
        }

        return departamento;
    }

    @Override
    public Departamento obterPorIdDepartamento(Integer id) {

        Departamento departamento = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                departamento = departamentoRepo.obterPorIdDepartamento(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                departamento = departamentoRepoWinthor.obterPorIdDepartamento(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                departamento = departamentoRepoGCF.obterPorIdDepartamento(id);
                break;
            }
        }

        return departamento;
    }
}

