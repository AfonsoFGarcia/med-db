package pt.ist.sirs.exceptions;

public class PermissaoIncorrectaException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String perm;

    public PermissaoIncorrectaException(String perm) {
        this.perm = perm;
    }

    @Override
    public String getMessage() {
        return "A permissao '" + this.perm + "' esta mal formada!";
    }

}
