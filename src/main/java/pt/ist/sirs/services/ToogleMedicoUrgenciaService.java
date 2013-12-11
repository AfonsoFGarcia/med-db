package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.MedDBException;

public class ToogleMedicoUrgenciaService extends MedDBService {

    private String usernameMedico;

    public ToogleMedicoUrgenciaService(String usernameMedico) {
        this.usernameMedico = usernameMedico;

    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        Medico medico = (Medico) root.getPersonByUsername(this.usernameMedico);
        if (medico.getMedicoDeUrgencia()) {
            medico.setMedicoDeUrgencia(false);
        } else {
            medico.setMedicoDeUrgencia(true);
        }

    }

}
