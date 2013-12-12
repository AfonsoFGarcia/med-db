package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.IncorrectPasswordException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class AdminLoginService extends MedDBService {

    private String username;
    private String password;
    private String nome;

    public AdminLoginService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        Pessoa person = new Pessoa();
        try {
            person = root.getPersonByUsername(username);
        } catch (Exception e) {
            throw new IncorrectPasswordException();

        }

        if (person.getAdmin()) {
            LoggedPerson.getInstance().setLoggedPerson(root.getPersonByUsername(username), password);
            this.nome = LoggedPerson.getInstance().getLoggedPerson().getNome();
        } else {
            throw new NotAdminException(username);
        }
    }

    public String getNome() {
        return this.nome;
    }
}
