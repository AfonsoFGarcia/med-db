package pt.ist.sirs.permissoes.logicas;

import java.util.Set;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoComposta;

public class PermissaoELogico extends PermissaoComposta {

    private static final long serialVersionUID = 1L;

    public PermissaoELogico(Registo r) {
        super(r);
    }

    public PermissaoELogico(Registo r, Set<Permissao> p) {
        super(r, p);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        for (Permissao permissao : super.getPermissoes()) {
            if (!permissao.isAllowed(pessoa)) {
                return false;
            }
        }
        return true;
    }
}
