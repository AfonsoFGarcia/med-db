package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.exceptions.OperacaoNaoPermitidaException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class ToggleAdminService extends MedDBService {

    private String username;
    private String estadoActual;

    public ToggleAdminService(String username) {
        this.username = username;

    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }

        if (LoggedPerson.getInstance().getLoggedPerson().getUsername().equals(username)) {
            throw new OperacaoNaoPermitidaException("alterar proprio status de admin", username);
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        Pessoa pessoa = root.getPersonByUsername(this.username);
        if (pessoa.getAdmin()) {
            pessoa.setAdmin(false);
            estadoActual = "falso";
        } else {
            pessoa.setAdmin(true);
            estadoActual = "verdadeiro";
        }
    }

    public String getEstadoActual() {
        return estadoActual;
    }

}
