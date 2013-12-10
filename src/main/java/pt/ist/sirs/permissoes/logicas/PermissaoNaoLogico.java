package pt.ist.sirs.permissoes.logicas;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;

/**
 * Classe <b>PermissaoNaoLogico</b>.<br>
 * <br>
 * Permissão que implementa o não lógico.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Permissao
 * @see Registo
 */
public class PermissaoNaoLogico extends Permissao {

    private static final long serialVersionUID = 1L;
    private Permissao permissao;

    /**
     * Cria um objecto PermissaoNaoLogico.
     * 
     * @param r Registo associado à permissão.
     * @param p Permissao a negar.
     */
    public PermissaoNaoLogico(Registo r, Permissao p) {
        super(r);
        permissao = p;
    }

    /* (non-Javadoc)
     * @see pt.ist.sirs.permissoes.Permissao#isAllowed(pt.ist.sirs.domain.Pessoa)
     */
    @Override
    public boolean isAllowed(Pessoa pessoa) {
        return !permissao.isAllowed(pessoa) && !super.medicoBanido((Medico) pessoa);
    }

}
