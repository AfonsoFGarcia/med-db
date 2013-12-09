package pt.ist.sirs.services;

import pt.ist.sirs.domain.Medico;

public class CreateMedicoService extends MedDBService {

    private String nome;
    private String password;
    private boolean medicoDeUrgencia;

    public CreateMedicoService(String nome, String password, boolean medicoDeUrgencia) {
        this.nome = nome;
        this.password = password;
        this.medicoDeUrgencia = medicoDeUrgencia;
    }

    @Override
    public void run() {
        Medico novoMedico = new Medico();
        novoMedico.setNome(this.nome);
        novoMedico.setPassword(this.password);
        novoMedico.setMedicoDeUrgencia(this.medicoDeUrgencia);
    }

}
