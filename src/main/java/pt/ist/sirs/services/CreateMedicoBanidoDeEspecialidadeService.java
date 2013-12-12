package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.MedicoBanidoDeEspecialidade;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.MedicoNaoExisteException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.exceptions.PessoaNaoExisteException;
import pt.ist.sirs.login.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class CreateMedicoBanidoDeEspecialidadeService extends MedDBService {

    Integer medicoObjectID;
    String usernameMedico;
    Integer especialidadeObjectID;

    public CreateMedicoBanidoDeEspecialidadeService(String usernameMedico, Integer especialidadeObjectID) {
        this.usernameMedico = usernameMedico;
        this.especialidadeObjectID = especialidadeObjectID;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        if (root.hasPerson(this.usernameMedico)) {
            if (root.hasEspecialidade(especialidadeObjectID)) {
                try {
                    this.medicoObjectID = root.getPersonByUsername(usernameMedico).getObjectId();

                    MedicoBanidoDeEspecialidade medicoBanido = new MedicoBanidoDeEspecialidade();
                    medicoBanido.setEspecialidadeObjectID(especialidadeObjectID);
                    medicoBanido.setMedicoObjectID(medicoObjectID);

                } catch (PessoaNaoExisteException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                throw new EspecialidadeNaoExisteException(especialidadeObjectID);
            }
        } else {
            throw new MedicoNaoExisteException(this.usernameMedico);
        }
    }
}
