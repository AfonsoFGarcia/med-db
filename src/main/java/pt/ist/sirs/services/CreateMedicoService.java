package pt.ist.sirs.services;

import pt.ist.sirs.domain.Medico;

public class CreateMedicoService extends MedDBService {

    private String nome;
    private boolean medicoDeUrgencia;

    public CreateMedicoService(String nome, boolean medicoDeUrgencia) {
        this.nome = nome;
        this.medicoDeUrgencia = medicoDeUrgencia;
    }

    @Override
    public void run() {
        Medico novoMedico = new Medico();
        novoMedico.setNome(this.nome);
        novoMedico.setMedicoDeUrgencia(this.medicoDeUrgencia);
    }

}
