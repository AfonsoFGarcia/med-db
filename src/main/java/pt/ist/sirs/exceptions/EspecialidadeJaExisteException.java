package pt.ist.sirs.exceptions;

public class EspecialidadeJaExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nome;

    public EspecialidadeJaExisteException(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMessage() {
        return "A especialidade " + nome + " ja existe!";
    }

}
