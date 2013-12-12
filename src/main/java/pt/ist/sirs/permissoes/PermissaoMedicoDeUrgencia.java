package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PermissaoMedicoDeUrgencia extends Permissao {

    private static final long serialVersionUID = 1L;

    public PermissaoMedicoDeUrgencia(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        if (pessoa instanceof Medico) {
            return ((Medico) pessoa).getMedicoDeUrgencia() && !super.medicoBanido((Medico) pessoa);
        } else {
            return false;
        }
    }

}
