package pt.ist.sirs.permissoes;

import java.util.Set;
import java.util.TreeSet;

import pt.ist.sirs.domain.Registo;

public abstract class PermissaoComposta extends Permissao {

    private static final long serialVersionUID = 1L;
    private Set<Permissao> permissoes;

    public PermissaoComposta(Registo r) {
        super(r);
        permissoes = new TreeSet<Permissao>();
    }

    public PermissaoComposta(Registo r, Set<Permissao> p) {
        super(r);
        permissoes = p;
    }

    public void addPermissao(Permissao p) {
        permissoes.add(p);
    }

    public void removePermissao(Permissao p) {
        permissoes.remove(p);
    }

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }
}
