package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.MedicoNaoExisteException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.exceptions.RegistoNaoExisteException;
import pt.ist.sirs.login.LoggedPerson;
import pt.ist.sirs.permissoes.PermissaoMedico;
import pt.ist.sirs.permissoes.logicas.PermissaoELogico;
import pt.ist.sirs.permissoes.logicas.PermissaoNaoLogico;

public class ProibirAcessoAMedicoService extends MedDBService {

    private String userMedico;
    private Integer idRegisto;

    public ProibirAcessoAMedicoService(String userMedico, Integer idRegisto) {
        this.userMedico = userMedico;
        this.idRegisto = idRegisto;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasRegisto(idRegisto)) {
            Registo registo = (Registo) root.getObjectByObjectID(idRegisto);
            if (root.hasPerson(userMedico)) {
                Medico medico = (Medico) root.getPersonByUsername(userMedico);

                PermissaoMedico pm = new PermissaoMedico(registo, medico);
                PermissaoNaoLogico pnl = new PermissaoNaoLogico(registo, pm);
                PermissaoELogico pel = new PermissaoELogico(registo);
                pel.addPermissao(pnl);
                pel.addPermissao(registo.getPermissao());

                registo.setPermissao(pel);
            } else {
                throw new MedicoNaoExisteException(userMedico);
            }
        } else {
            throw new RegistoNaoExisteException(idRegisto);
        }
    }

}
