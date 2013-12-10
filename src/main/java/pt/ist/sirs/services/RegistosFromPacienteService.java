package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;

public class RegistosFromPacienteService extends MedDBService {

    private ArrayList<Registo> registos;
    private String pacienteUsername;

    public RegistosFromPacienteService(String pacienteUsername) {
        this.pacienteUsername = pacienteUsername;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.registos = root.getRegistosFromPaciente(pacienteUsername);
    }

    public ArrayList<Registo> getRegistos() {
        return this.registos;
    }
}
