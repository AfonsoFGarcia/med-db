package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>UsernameJaExisteException</b>.<br>
 * <br>
 * Representa a existencia de uma pessoa com o username especificado no sistema.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class UsernameJaExisteException extends MedDBException {

    private static final long serialVersionUID = 1L;

    private String username;

    public UsernameJaExisteException(String username) {
        this.username = username;
    }

    @Override
    public String getMessage() {
        return "O username " + this.username + " ja existe!";
    }

}
