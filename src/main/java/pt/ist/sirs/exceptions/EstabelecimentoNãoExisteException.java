package pt.ist.sirs.exceptions;

public class EstabelecimentoNãoExisteException extends MedDBException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    public EstabelecimentoNãoExisteException(Integer idEstabelecimento) {
        this.id = idEstabelecimento;
    }

    @Override
    public String getMessage() {
        return "O estabelecimento com o id" + id + " nao existe!";
    }

}
