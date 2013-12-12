package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.IncorrectPasswordException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
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
        Pessoa p = new Pessoa();
        try {
            p = root.getPersonByUsername(username);
        } catch (Exception e) {
            throw new IncorrectPasswordException();
        }
        LoggedPerson.getInstance().setLoggedPerson(p, password);
        this.nome = LoggedPerson.getInstance().getLoggedPerson().getNome();
    }

    public String getNome() {
        return this.nome;
    }
}
