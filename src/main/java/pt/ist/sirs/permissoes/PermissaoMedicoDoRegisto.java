package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoMedicoDoRegisto extends Permissao {

    private static final long serialVersionUID = 1L;

    public PermissaoMedicoDoRegisto(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return registo.getMedico().getObjectId().equals(pessoa.getObjectId()) && !super.medicoBanido((Medico) pessoa);
    }

}
