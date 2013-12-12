package pt.ist.sirs.exceptions;

/**
 * Excepcao <b>EstabelecimentoNaoExisteException</b>.<br>
 * <br>
 * Representa a nao existencia do estabelecimento no sistema.
 * 
 * @author José Góis (79261)
 */
public class EstabelecimentoNaoExisteException extends MedDBException {

    private static final long serialVersionUID = 1L;

    private Integer id;

    public EstabelecimentoNaoExisteException(Integer idEstabelecimento) {
        this.id = idEstabelecimento;
    }

    @Override
    public String getMessage() {
        return "O estabelecimento com o id" + id + " nao existe!";
    }

}
