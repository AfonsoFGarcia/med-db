package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public abstract class Permissao {

    Registo _registo;

    Permissao(Registo registo) {
        _registo = registo;
    }

    public abstract boolean isAllowed(Pessoa pessoa);

    @Override
    public abstract String toString();

}
