package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.AcessoRecusadoException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.RegistoNaoExisteException;
import pt.ist.sirs.services.dto.RegistoDTO;
import pt.ist.sirs.utils.LoggedPerson;

public class GetRegistoService extends MedDBService {

    private Integer idRegisto;
    private RegistoDTO registo;

    public GetRegistoService(Integer idRegisto) {
        this.idRegisto = idRegisto;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasRegisto(idRegisto)) {
            Registo reg = (Registo) root.getObjectByObjectID(idRegisto);

            if (reg.getPermissao().isAllowed(LoggedPerson.getInstance().getLoggedPerson())) {
                registo =
                        new RegistoDTO(reg.getConteudo(), reg.getPaciente().getNome(), reg.getMedico().getNome(), reg
                                .getEspecialidade().getNome(), reg.getEstabelecimento().getNome(), reg.getObjectId());
            } else {
                throw new AcessoRecusadoException(idRegisto, LoggedPerson.getInstance().getLoggedPerson().getUsername());
            }
        } else {
            throw new RegistoNaoExisteException(idRegisto);
        }
    }

    public RegistoDTO getRegisto() {
        return registo;
    }
}
