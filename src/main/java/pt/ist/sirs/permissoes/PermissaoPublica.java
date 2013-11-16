package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * Classe <b>PermissaoPublica</b>.<br>
 * <br>
 * Permite o acesso de qualquer pessoa a um registo.
 * 
 * @author Afonso F. Garcia (70001)
 * @see {@link Registo}, {@link Pessoa}
 */
public class PermissaoPublica extends Permissao {

    private static final long serialVersionUID = 1L;

    /**
     * Cria um objecto PermissaoPublica.
     * 
     * @param r Registo a associar à permissão.
     */
    public PermissaoPublica(Registo r) {
        super(r);
    }

    /* (non-Javadoc)
     * @see pt.ist.sirs.permissoes.Permissao#isAllowed(pt.ist.sirs.domain.Pessoa)
     */
    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return true;
    }
}
