package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.services.dto.RegistoDTO;
import pt.ist.sirs.utils.LoggedPerson;
import pt.ist.sirs.utils.Seguranca;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class GetRegistosService extends MedDBService {

    private ArrayList<RegistoDTO> registos;

    public GetRegistosService() {
        this.registos = new ArrayList<RegistoDTO>();
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        for (Registo registo : root.getRegistos()) {
            RegistoDTO r =
                    new RegistoDTO(Seguranca.decrypt(registo.getConteudo()), registo.getPaciente().getNome(), registo.getMedico()
                            .getNome(), registo.getEspecialidade().getNome(), registo.getEstabelecimento().getNome(),
                            registo.getObjectId());
            this.registos.add(r);
        }

    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.registos;
    }
}
