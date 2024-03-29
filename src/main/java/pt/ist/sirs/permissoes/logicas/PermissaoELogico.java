package pt.ist.sirs.permissoes.logicas;

import java.util.List;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoComposta;

/**
 * Classe <b>PermissaoELogico</b>.<br>
 * <br>
 * Permissão composta por outras permissões que implementa o e lógico.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Permissao
 * @see Registo
 */
public class PermissaoELogico extends PermissaoComposta {

    private static final long serialVersionUID = 1L;

    /**
     * Cria um objecto PermissaoELogico.
     * 
     * @param r Registo associado à permissão.
     */
    public PermissaoELogico(Registo r) {
        super(r);
    }

    /**
     * Cria um objecto PermissaoELogico.
     * 
     * @param r Registo associado à permissão.
     * @param p Set de permissões.
     */
    public PermissaoELogico(Registo r, List<Permissao> p) {
        super(r, p);
    }
    
    /**
     * Cria um objecto PermissaoELogico.
     *
     * @param p Set de permissões.
     */
    public PermissaoELogico(List<Permissao> p) {
        super(p);
    }

    /* (non-Javadoc)
     * @see pt.ist.sirs.permissoes.Permissao#isAllowed(pt.ist.sirs.domain.Pessoa)
     */
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
