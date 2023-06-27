
package br.com.vitavault.DAO;

import br.com.vitavault.controller.FuncionarioInterface;
import br.com.vitavault.model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements FuncionarioInterface {
    List<Funcionario> funcionarios = new ArrayList();

    @Override
    public void salvarFuncionario(Funcionario oFuncionario) {
        funcionarios.add(oFuncionario);
    }

    @Override
    public List<Funcionario> buscarFuncionarios() {
        return funcionarios;
    }
    
}
