package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.EspecialidadeJaExisteException;
import pt.ist.sirs.exceptions.MedDBException;

public class CreateEspecialidadeService extends MedDBService {

    private String nome;

    public CreateEspecialidadeService(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasEspecialidade(nome)) {
            throw new EspecialidadeJaExisteException(nome);
        } else {
            Especialidade e = new Especialidade();
            e.setNome(nome);
        }

    }

}
