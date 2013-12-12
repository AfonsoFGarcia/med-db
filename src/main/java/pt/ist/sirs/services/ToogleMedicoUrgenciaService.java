package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class ToogleMedicoUrgenciaService extends MedDBService {

    private String usernameMedico;
    private String estadoActual;

    public ToogleMedicoUrgenciaService(String usernameMedico) {
        this.usernameMedico = usernameMedico;

    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        Medico medico = (Medico) root.getPersonByUsername(this.usernameMedico);
        if (medico.getMedicoDeUrgencia()) {
            medico.setMedicoDeUrgencia(false);
            estadoActual = "falso";
        } else {
            medico.setMedicoDeUrgencia(true);
            estadoActual = "verdadeiro";
        }
    }

    public String getEstadoActual() {
        return estadoActual;
    }

}
