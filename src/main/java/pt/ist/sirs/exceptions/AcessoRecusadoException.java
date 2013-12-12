package pt.ist.sirs.exceptions;

public class AcessoRecusadoException extends MedDBException {

    private static final long serialVersionUID = 1L;

    private Integer idRegisto;
    private String userPessoa;

    public AcessoRecusadoException(Integer idRegisto, String userPessoa) {
        this.idRegisto = idRegisto;
        this.userPessoa = userPessoa;
    }

    @Override
    public String getMessage() {
        return "O username " + userPessoa + " nao pode aceder ao registo " + idRegisto + "!";
    }
}
