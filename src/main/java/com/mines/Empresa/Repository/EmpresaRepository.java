package com.mines.Empresa.Repository;

import java.util.List;
import com.mines.Empresa.Model.Empresa;

public interface EmpresaRepository {
    
    List<Empresa> obterTodosEmpresas(Integer entidade);
    
    Empresa obterPorIdEmpresa(Integer id);

    Empresa salvarEmpresa(Empresa empresa);

    Empresa atualizarEmpresa(Empresa empresa);

    Boolean deletarEmpresa(Empresa empresa);
}
