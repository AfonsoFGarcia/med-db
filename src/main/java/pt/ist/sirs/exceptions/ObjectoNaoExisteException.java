package pt.ist.sirs.exceptions;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class ObjectoNaoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer objectID;

    public ObjectoNaoExisteException(Integer objectID) {
        this.objectID = objectID;
    }

    @Override
    public String getMessage() {
        return "O objecto " + this.objectID + " nao existe!";
    }
}
