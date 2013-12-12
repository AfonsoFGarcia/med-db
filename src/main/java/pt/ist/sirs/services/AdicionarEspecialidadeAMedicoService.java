package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.MedicoNaoExisteException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class AdicionarEspecialidadeAMedicoService extends MedDBService {

    private String usernameMedico;
    private Integer idEspecialidade;

    public AdicionarEspecialidadeAMedicoService(String usernameMedico, Integer idEspecialidade) {
        this.usernameMedico = usernameMedico;
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasPerson(usernameMedico)) {
            Medico medico = (Medico) root.getPersonByUsername(this.usernameMedico);
            if (root.hasEspecialidade(idEspecialidade)) {
                Especialidade especialidade = (Especialidade) root.getObjectByObjectID(this.idEspecialidade);
                medico.getEspecialidades().add(especialidade);
            } else {
                throw new EspecialidadeNaoExisteException(idEspecialidade);
            }
        } else {
            throw new MedicoNaoExisteException(usernameMedico);
        }
    }

}
