package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.EstabelecimentoJaExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;

public class CreateEstabelecimentoService extends MedDBService {

    private String nome;

    public CreateEstabelecimentoService(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasEstabelecimento(nome)) {
            throw new EstabelecimentoJaExisteException(nome);
        } else {
            Estabelecimento e = new Estabelecimento();
            e.setNome(nome);
        }

    }

}
