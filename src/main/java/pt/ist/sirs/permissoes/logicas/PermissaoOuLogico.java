package pt.ist.sirs.permissoes.logicas;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoComposta;

public class PermissaoOuLogico extends PermissaoComposta {

    private static final long serialVersionUID = 1L;

    public PermissaoOuLogico(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        for (Permissao permissao : super.getPermissoes()) {
            if (permissao.isAllowed(pessoa)) {
                return true;
            }
        }
        return false;
    }

}
