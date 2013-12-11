package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.MedDBException;

public class AdicionarEspecialidadeAMedicoService extends MedDBService {

    private String usernameMedico;
    private Integer idEspecialidade;

    public AdicionarEspecialidadeAMedicoService(String usernameMedico, Integer idEspecialidade) {
        this.usernameMedico = usernameMedico;
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        Medico medico = (Medico) root.getPersonByUsername(this.usernameMedico);
        Especialidade especialidade = (Especialidade) root.getObjectByObjectID(this.idEspecialidade);
        medico.getEspecialidades().add(especialidade);

    }

}