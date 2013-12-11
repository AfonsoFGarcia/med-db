package pt.ist.sirs.exceptions;

public class IncorrectPasswordException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "A combinacao username / password e invalida!";
    }

}
