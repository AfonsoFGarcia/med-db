package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>IncorrectPasswordException</b>.<br>
 * <br>
 * Representa um erro na password.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class IncorrectPasswordException extends MedDBException {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "A combinacao username / password e invalida!";
    }

}
