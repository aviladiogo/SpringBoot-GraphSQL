package com.mines.ClassificacaoMercadologica.Service;

import java.util.List;
import com.mines.ClassificacaoMercadologica.Model.Secao;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepository;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepositoryGCF;
import com.mines.ClassificacaoMercadologica.Repository.SecaoRepositoryWinthor;
import com.mines.util.ConstantesERP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SecaoServiceImpl implements SecaoService {
    
    @Autowired
    private SecaoRepository secaoRepo;

    @Autowired 
    private SecaoRepositoryWinthor secaoRepoWinthor;

    @Autowired
    private SecaoRepositoryGCF secaoRepoGCF;

    @Value("${sistema_erp}") public Integer sistema_erp;

    @Override
    public List<Secao> obterTodosSecoes(Integer entidade) {

        List<Secao> secoes = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                secoes = secaoRepo.obterTodosSecoes(entidade);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                secoes = secaoRepoWinthor.obterTodosSecoes(entidade);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                secoes = secaoRepoGCF.obterTodosSecoes(entidade);
                break;
            }
        }

        return secoes;
    }

    @Override
    public List<Secao> obterSecoesPorDepartamento(Integer entidade, Integer departamento) {

        List<Secao> secoes = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                secoes = secaoRepo.obterSecoesPorDepartamento(entidade, departamento);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                secoes = secaoRepoWinthor.obterSecoesPorDepartamento(entidade, departamento);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                secoes = secaoRepoGCF.obterSecoesPorDepartamento(entidade, departamento);
                break;
            }
        }

        return secoes;
    }

    @Override
    public Secao obterPorIdSecao(Integer id) {

        Secao secao = null;

        switch(sistema_erp){

            case ConstantesERP.k_minesSistemas:{
                secao = secaoRepo.obterPorIdSecao(id);
                break;
            }

            case ConstantesERP.k_winthorSistemas:{
                secao = secaoRepoWinthor.obterPorIdSecao(id);
                break;
            }

            case ConstantesERP.k_gcfSistemas:{
                secao = secaoRepoGCF.obterPorIdSecao(id);
                break;
            }
        }

        return secao;
    }
}

