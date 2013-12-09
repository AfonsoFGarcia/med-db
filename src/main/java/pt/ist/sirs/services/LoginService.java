package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.login.LoggedPerson;

public class LoginService extends MedDBService {

    private String username;
    private String password;
    private String nome;

    public LoginService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        LoggedPerson.getInstance().setLoggedPerson(root.getPersonByUsername(username), password);
        this.nome = LoggedPerson.getInstance().getLoggedPerson().getNome();
    }

    public String getNome() {
        return this.nome;
    }
}
