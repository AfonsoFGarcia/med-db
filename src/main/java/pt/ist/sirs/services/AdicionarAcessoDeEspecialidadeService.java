package pt.ist.sirs.services;

import pt.ist.sirs.domain.PoliticaDeEspecialidade;
import pt.ist.sirs.exceptions.MedDBException;

public class AdicionarAcessoDeEspecialidadeService extends MedDBService {

    private Integer idAcessora;
    private Integer idAcedida;

    public AdicionarAcessoDeEspecialidadeService(Integer idAcessora, Integer idAcedida) {
        this.idAcessora = idAcessora;
        this.idAcedida = idAcedida;
    }

    @Override
    public void run() throws MedDBException {
        PoliticaDeEspecialidade politica = new PoliticaDeEspecialidade();
        politica.setAcedidaObjectID(idAcedida);
        politica.setAcessorObjectID(idAcessora);
    }

}
