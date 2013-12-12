package pt.ist.sirs.exceptions;

/**
 * 
 * @author José Góis (79261)
 */
public class EspecialidadeNaoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    public EspecialidadeNaoExisteException(Integer id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "A especialidade com o id " + id + " nao existe!";
    }

}
