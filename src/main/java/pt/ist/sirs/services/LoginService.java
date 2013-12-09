package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.login.LoggedPerson;

public class LoginService extends MedDBService {

    private String username;
    private String password;

    public LoginService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        LoggedPerson.setLoggedPerson(root.getPersonByUsername(username), password);
    }
}
