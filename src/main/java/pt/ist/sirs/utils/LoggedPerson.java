package pt.ist.sirs.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.IncorrectPasswordException;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class LoggedPerson {

    private Pessoa loggedPerson;
    private static LoggedPerson me = null;
    private boolean admin;

    private LoggedPerson() {
        admin = false;
    }

    public static LoggedPerson getInstance() {
        if (me == null) {
            me = new LoggedPerson();
        }
        return me;
    }

    public void setLoggedPerson(Pessoa p, String password) throws IncorrectPasswordException {
        String salt = p.getSalt();
        String saltedPass = Base64.encodeBase64String(DigestUtils.sha1(password + salt));

        if (p.getPassword().equals(saltedPass)) {
            this.loggedPerson = p;
            this.admin = p.getAdmin();
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public Pessoa getLoggedPerson() {
        return this.loggedPerson;
    }

    public void removeLoggedPerson() {
        this.loggedPerson = null;
        this.admin = false;
    }

    public boolean loggedPersonIsAdmin() {
        return this.admin;
    }
}
