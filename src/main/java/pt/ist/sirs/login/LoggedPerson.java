package pt.ist.sirs.login;

import pt.ist.sirs.domain.Pessoa;

public class LoggedPerson {

    private static Pessoa loggedPerson;

    public static void setLoggedPerson(Pessoa p) {
        loggedPerson = p;
    }

    public static Pessoa getLoggedPerson() {
        return loggedPerson;
    }

}
