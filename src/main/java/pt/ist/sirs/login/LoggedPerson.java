package pt.ist.sirs.login;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.IncorrectPasswordException;

public class LoggedPerson {

    private static Pessoa loggedPerson;

    public static void setLoggedPerson(Pessoa p, String password) throws IncorrectPasswordException {
        if (p.getPassword().equals(password)) {
            loggedPerson = p;
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public static Pessoa getLoggedPerson() {
        return loggedPerson;
    }

    public static void removeLoggedPerson() {
        loggedPerson = null;

    }

}
