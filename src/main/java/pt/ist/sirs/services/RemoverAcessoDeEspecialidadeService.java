package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.PoliticaDeEspecialidade;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class RemoverAcessoDeEspecialidadeService extends MedDBService {

    private Integer idAcessora;
    private Integer idAcedida;

    public RemoverAcessoDeEspecialidadeService(Integer idAcessora, Integer idAcedida) {
        this.idAcessora = idAcessora;
        this.idAcedida = idAcedida;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasEspecialidade(idAcedida)) {
            if (root.hasEspecialidade(idAcessora)) {
                for (PoliticaDeEspecialidade p : root.getPoliticaDeEspecialidade()) {
                    if (p.getAcedidaObjectID().equals(idAcedida) && p.getAcessorObjectID().equals(idAcessora)) {
                        root.removePoliticaDeEspecialidade(p);
                    }
                }
            } else {
                throw new EspecialidadeNaoExisteException(idAcessora);
            }
        } else {
            throw new EspecialidadeNaoExisteException(idAcedida);
        }
    }
}
