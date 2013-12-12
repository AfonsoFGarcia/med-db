package pt.ist.sirs.exceptions;

/**
 * 
 * @author José Góis (79261)
 */
public class MedicoNaoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nome;

    public MedicoNaoExisteException(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMessage() {
        return "O Medico " + this.nome + " nao existe!";
    }

}
