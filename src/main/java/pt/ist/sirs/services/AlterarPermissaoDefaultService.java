package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.exceptions.PermissaoIncorrectaException;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoBuilderParser;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class AlterarPermissaoDefaultService extends MedDBService {

    private String newPerm;

    public AlterarPermissaoDefaultService(String newPerm) {
        this.newPerm = newPerm;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        Permissao p;

        try {
            p = PermissaoBuilderParser.getPermissao(newPerm);
        } catch (Exception e) {
            throw new PermissaoIncorrectaException(newPerm);
        }

        if (p == null) {
            throw new PermissaoIncorrectaException(newPerm);
        }

        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        root.setDefaultPermission(newPerm);
    }
}
