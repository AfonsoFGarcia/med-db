package pt.ist.sirs.exceptions;

/**
 * 
 * @author José Góis (79261)
 */
public class RegistoJaExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RegistoJaExisteException() {

    }

    @Override
    public String getMessage() {
        return "O registo que pretende criar ja existe!";
    }

}
