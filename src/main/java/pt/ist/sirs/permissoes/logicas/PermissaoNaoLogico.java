package pt.ist.sirs.permissoes.logicas;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;

public class PermissaoNaoLogico extends Permissao {

    private static final long serialVersionUID = 1L;
    private Permissao permissao;

    public PermissaoNaoLogico(Registo r, Permissao p) {
        super(r);
        permissao = p;
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return !permissao.isAllowed(pessoa);
    }

}
