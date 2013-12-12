package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.EstabelecimentoNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.MedicoNaoExisteException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class AssociarMedicoAEstabelecimentoService extends MedDBService {

    private String usernameMedico;
    private Integer idEstabelecimento;

    public AssociarMedicoAEstabelecimentoService(String usernameMedico, Integer idEstabelecimento) {
        this.usernameMedico = usernameMedico;
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasMedico(usernameMedico)) {
            Medico m = (Medico) root.getPersonByUsername(usernameMedico);
            if (root.hasEstabelecimento(idEstabelecimento)) {
                Estabelecimento e = (Estabelecimento) root.getObjectByObjectID(idEstabelecimento);
                m.addEstabelecimentos(e);
            } else {
                throw new EstabelecimentoNaoExisteException(idEstabelecimento);
            }
        } else {
            throw new MedicoNaoExisteException(usernameMedico);
        }
    }

}
