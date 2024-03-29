package pt.ist.sirs.permissoes;

import java.util.List;

import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class PermissaoMedicoDaEspecialidade extends Permissao {
    private static final long serialVersionUID = 1L;

    public PermissaoMedicoDaEspecialidade(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        Medico medico = (Medico) pessoa;
        List<Especialidade> especialidades = medico.getEspecialidades();

        for (Especialidade especialidade : especialidades) {
            if (registo.getEspecialidade().getObjectId().equals(especialidade.getObjectId())) {
                return true && !super.medicoBanido(medico);
            }
        }

        return false;
    }

}
