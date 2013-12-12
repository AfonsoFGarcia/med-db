package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>OperacaoNaoPermitidaException</b>.<br>
 * <br>
 * Representa a impossibilidade do utilizador realizar a operacao.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class OperacaoNaoPermitidaException extends MedDBException {

    private static final long serialVersionUID = 1L;

    private String username;
    private String operacao;

    public OperacaoNaoPermitidaException(String operacao, String username) {
        this.username = username;
        this.operacao = operacao;
    }

    @Override
    public String getMessage() {
        return "A operacao '" + this.operacao + "' nao e permitida para o utilizador " + this.username + "!";
    }

}
