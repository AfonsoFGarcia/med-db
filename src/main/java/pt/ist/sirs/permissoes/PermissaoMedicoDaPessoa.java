package pt.ist.sirs.permissoes;

import java.util.List;

import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoMedicoDaPessoa extends Permissao {
    private static final long serialVersionUID = 1L;

    protected PermissaoMedicoDaPessoa(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        List<Medico> medicos = pessoa.getMedicos();

        for (Medico medico : medicos) {
            if (registo.getMedico().getObjectId().equals(medico.getObjectId())) {
                return true;
            }
        }

        return false;
    }

}
