package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>EspecialidadeJaExisteException</b>.<br>
 * <br>
 * Representa a existencia da especialidade no sistema.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class EspecialidadeJaExisteException extends MedDBException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The nome. */
    private String nome;

    /**
     * Instantiates a new especialidade ja existe exception.
     * 
     * @param nome the nome
     */
    public EspecialidadeJaExisteException(String nome) {
        this.nome = nome;
    }

    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return "A especialidade " + nome + " ja existe!";
    }

}
