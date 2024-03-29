package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>NotAdminException</b>.<br>
 * <br>
 * Representa o utilizador com login feito nao ser admin.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class NotAdminException extends MedDBException {

    private static final long serialVersionUID = 1L;

    private String username;

    public NotAdminException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "A pessoa " + username + " nao tem poderes de administracao!";
    }

}
