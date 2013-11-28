package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoPacienteDoRegisto extends Permissao {
    private static final long serialVersionUID = 1L;

    public PermissaoPacienteDoRegisto(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return registo.getPaciente().getObjectId().equals(pessoa.getObjectId());
    }
}