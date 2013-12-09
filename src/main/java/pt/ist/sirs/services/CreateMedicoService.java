package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;

public class CreateMedicoService extends MedDBService {

    private String nome;
    private String password;
    private String username;
    private boolean medicoDeUrgencia;

    public CreateMedicoService(String username, String password, String nome, boolean medicoDeUrgencia) {
        this.nome = nome;
        this.password = password;
        this.username = username;
        this.medicoDeUrgencia = medicoDeUrgencia;
    }

    @Override
    public void run() {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        if (!root.hasPerson(this.username)) {
            Medico novoMedico = new Medico();
            novoMedico.setNome(this.nome);
            novoMedico.setUsername(this.username);
            novoMedico.setPassword(this.password);
            novoMedico.setMedicoDeUrgencia(this.medicoDeUrgencia);
        } else {
            // UsernameJaExisteException
        }
    }
}
