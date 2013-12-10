package pt.ist.sirs.login;

import org.apache.commons.codec.digest.DigestUtils;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.IncorrectPasswordException;

public class LoggedPerson {

    private Pessoa loggedPerson;
    private static LoggedPerson me = null;

    private LoggedPerson() {
    }

    public static LoggedPerson getInstance() {
        if (me == null) {
            me = new LoggedPerson();
        }
        return me;
    }

    public void setLoggedPerson(Pessoa p, String password) throws IncorrectPasswordException {
        String salt = p.getSalt();
        String saltedPass = new String(DigestUtils.sha1(password + salt));

        if (p.getPassword().equals(saltedPass)) {
            this.loggedPerson = p;
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public Pessoa getLoggedPerson() {
        return this.loggedPerson;
    }

    public void removeLoggedPerson() {
        this.loggedPerson = null;

    }

}
