package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoPublica extends Permissao {

    public PermissaoPublica(Registo r) {
        super(r);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return true;
    }
}
