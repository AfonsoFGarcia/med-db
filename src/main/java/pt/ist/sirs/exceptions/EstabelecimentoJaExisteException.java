package pt.ist.sirs.exceptions;

public class EstabelecimentoJaExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String nome;

    public EstabelecimentoJaExisteException(String nome) {
        this.nome = nome;
    }

    @Override
    public String getMessage() {
        return "O estabelecimento " + nome + " ja existe!";
    }

}