package com.mines.Empresa.Service;

import java.util.List;
import com.mines.Empresa.Model.Empresa;
import com.mines.Empresa.Repository.EmpresaRepository;
import com.mines.Empresa.Repository.EmpresaRepositoryGCF;
import com.mines.Empresa.Repository.EmpresaRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService{
    
    @Autowired
    private EmpresaRepository empresaRepo;

    @Autowired 
    private EmpresaRepositoryWinthor empresaRepoWinthor;

    @Autowired
    private EmpresaRepositoryGCF empresaRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Empresa> obterTodosEmpresas(Integer entidade) {

        List<Empresa> empresa = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                empresa = empresaRepo.obterTodosEmpresas(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                empresa = empresaRepoWinthor.obterTodosEmpresas(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                empresa = empresaRepoGCF.obterTodosEmpresas(entidade);
                break;
            }
        }

        return empresa;
    }

    @Override
    public Empresa obterPorIdEmpresa(Integer id) {

        Empresa empresa = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                empresa = empresaRepo.obterPorIdEmpresa(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                empresa = empresaRepoWinthor.obterPorIdEmpresa(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                empresa = empresaRepoGCF.obterPorIdEmpresa(id);
                break;
            }
        }

        return empresa;
    }
}

