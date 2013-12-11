package pt.ist.sirs.services;

import pt.ist.sirs.domain.PoliticaDeEspecialidade;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;

public class AdicionarAcessoDeEspecialidadeService extends MedDBService {

    private Integer idAcessora;
    private Integer idAcedida;

    public AdicionarAcessoDeEspecialidadeService(Integer idAcessora, Integer idAcedida) {
        this.idAcessora = idAcessora;
        this.idAcedida = idAcedida;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        PoliticaDeEspecialidade politica = new PoliticaDeEspecialidade();
        politica.setAcedidaObjectID(idAcedida);
        politica.setAcessorObjectID(idAcessora);
    }

}
