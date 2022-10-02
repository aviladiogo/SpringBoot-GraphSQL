package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.Empresa;

public interface EmpresaRepositoryGCF {
    
    List<Empresa> obterTodosEmpresas(Integer entidade);
    
    Empresa obterPorIdEmpresa(Integer id);

}
