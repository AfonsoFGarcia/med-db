package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.OperacaoNaoPermitidaException;
import pt.ist.sirs.exceptions.PermissaoIncorrectaException;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoBuilderParser;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class ChangePermissaoService extends MedDBService {

    private String perm;
    private Integer idRegisto;

    public ChangePermissaoService(String perm, Integer idRegisto) {
        this.perm = perm;
        this.idRegisto = idRegisto;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        Registo reg = (Registo) root.getObjectByObjectID(idRegisto);
        Pessoa loggedPerson = LoggedPerson.getInstance().getLoggedPerson();

        if (loggedPerson.getUsername().equals(reg.getPaciente().getUsername())) {
            Permissao perm = PermissaoBuilderParser.getPermissao(this.perm);
            if (perm == null) {
                throw new PermissaoIncorrectaException(this.perm);
            }
            perm.setRegisto(reg);
            reg.setPermissao(perm);
        } else {
            throw new OperacaoNaoPermitidaException("mudar permissao", loggedPerson.getUsername());
        }

    }
}
