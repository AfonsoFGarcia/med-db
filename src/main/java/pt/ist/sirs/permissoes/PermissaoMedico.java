package pt.ist.sirs.permissoes;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PermissaoMedico extends Permissao {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Medico m;

    protected PermissaoMedico(Registo r) {
        super(r);
    }

    public PermissaoMedico(Registo r, Medico m) {
        super(r);
        this.m = m;
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        if (pessoa instanceof Medico) {
            return pessoa.getUsername().equals(m.getUsername()) && !super.medicoBanido((Medico) pessoa);
        }
        return false;
    }

    public Medico getMedico() {
        return m;
    }

    public void setMedico(Medico m) {
        this.m = m;
    }

}
