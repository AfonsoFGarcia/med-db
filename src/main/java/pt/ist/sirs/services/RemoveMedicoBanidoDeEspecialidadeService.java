package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.MedicoBanidoDeEspecialidade;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.MedicoNaoExisteException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;

public class RemoveMedicoBanidoDeEspecialidadeService extends MedDBService {

    private String userMedico;
    private Integer idEspecialidade;

    public RemoveMedicoBanidoDeEspecialidadeService(String userMedico, Integer idEspecialidade) {
        this.userMedico = userMedico;
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasPerson(userMedico)) {
            if (root.hasEspecialidade(idEspecialidade)) {
                Medico med = (Medico) root.getPersonByUsername(userMedico);
                for (MedicoBanidoDeEspecialidade m : root.getMedicoBanidoDeEspecialidade()) {
                    if (m.getEspecialidadeObjectID().equals(idEspecialidade) && m.getMedicoObjectID().equals(med.getObjectId())) {
                        root.removeMedicoBanidoDeEspecialidade(m);
                    }
                }
            } else {
                throw new EspecialidadeNaoExisteException(idEspecialidade);
            }
        } else {
            throw new MedicoNaoExisteException(userMedico);
        }
    }
}
