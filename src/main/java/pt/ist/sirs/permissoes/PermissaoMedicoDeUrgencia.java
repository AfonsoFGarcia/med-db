package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoMedicoDeUrgencia extends Permissao {

    private static final long serialVersionUID = 1L;

    public PermissaoMedicoDeUrgencia(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return ((Medico) pessoa).getMedicoDeUrgencia();
    }

}