package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.login.LoggedPerson;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoComposta;
import pt.ist.sirs.permissoes.PermissaoMedico;
import pt.ist.sirs.permissoes.logicas.PermissaoNaoLogico;

public class DevolverAcessoAMedicoService extends MedDBService {

    private String userMedico;
    private Integer idRegisto;

    public DevolverAcessoAMedicoService(String userMedico, Integer idRegisto) {
        this.userMedico = userMedico;
        this.idRegisto = idRegisto;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        Registo registo = (Registo) root.getObjectByObjectID(idRegisto);

        PermissaoComposta pc = (PermissaoComposta) registo.getPermissao();
        PermissaoNaoLogico pnl = getPermissao(pc);
        pc.removePermissao(pnl);

        registo.setPermissao(pc);
    }

    private PermissaoNaoLogico getPermissao(PermissaoComposta pc) {
        for (Permissao p : pc.getPermissoes()) {
            if (p instanceof PermissaoNaoLogico && ((PermissaoNaoLogico) p).getPermissao() instanceof PermissaoMedico
                    && ((PermissaoMedico) ((PermissaoNaoLogico) p).getPermissao()).getMedico().getUsername().equals(userMedico)) {
                return (PermissaoNaoLogico) p;
            } else if (p instanceof PermissaoComposta) {
                PermissaoNaoLogico perm = getPermissao((PermissaoComposta) p);
                if (perm != null) {
                    return perm;
                }
            }
        }
        return null;
    }
}