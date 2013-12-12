package pt.ist.sirs.exceptions;

/**
 * 
 * @author José Góis (79261)
 */
public class RegistoNaoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer id;

    public RegistoNaoExisteException(Integer id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "O registo com o id " + this.id + " nao existe!";
    }

}
