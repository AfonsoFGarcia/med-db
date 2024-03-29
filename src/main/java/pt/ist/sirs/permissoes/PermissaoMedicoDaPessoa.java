package pt.ist.sirs.permissoes;

import java.util.List;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class PermissaoMedicoDaPessoa extends Permissao {
    private static final long serialVersionUID = 1L;

    public PermissaoMedicoDaPessoa(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        List<Medico> medicos = registo.getPaciente().getMedicos();

        for (Medico medico : medicos) {
            if (registo.getMedico().getObjectId().equals(medico.getObjectId())) {
                return true && !super.medicoBanido(medico);
            }
        }

        return false;
    }

}
