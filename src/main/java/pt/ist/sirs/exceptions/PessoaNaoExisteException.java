package pt.ist.sirs.exceptions;

public class PessoaNaoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nome;

    public PessoaNaoExisteException(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMessage() {
        return "A pessoa " + this.nome + " nao existe!";
    }

}
