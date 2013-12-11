package pt.ist.sirs.permissoes.logicas;

import java.util.List;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoComposta;

/**
 * Classe <b>PermissaoOuLogico</b>.<br>
 * <br>
 * Permissão composta por outras permissões que implementa o ou lógico.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Permissao
 * @see Registo
 */
public class PermissaoOuLogico extends PermissaoComposta {

    private static final long serialVersionUID = 1L;

    /**
     * Cria um objecto PermissaoOuLogico.
     * 
     * @param r Registo associado à permissão.
     */
    public PermissaoOuLogico(Registo r) {
        super(r);
    }

    /**
     * Cria um objecto PermissaoOuLogico.
     * 
     * @param r Registo associado à permissão.
     * @param p Set de permissões.
     */
    public PermissaoOuLogico(Registo r, List<Permissao> p) {
        super(r, p);
    }
    
    /**
     * Cria um objecto PermissaoOuLogico.
     *
     * @param p Set de permissões.
     */
    public PermissaoOuLogico(List<Permissao> p) {
        super(p);
    }

    /* (non-Javadoc)
     * @see pt.ist.sirs.permissoes.Permissao#isAllowed(pt.ist.sirs.domain.Pessoa)
     */
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
