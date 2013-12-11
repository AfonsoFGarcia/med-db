package pt.ist.sirs.exceptions;

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
