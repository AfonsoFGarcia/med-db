package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.dto.RegistoDTO;

/**
 * 
 * @author José Góis (79261)
 */
public class GetRegistosService extends MedDBService {

    private ArrayList<RegistoDTO> registos;

    public GetRegistosService() {
        this.registos = new ArrayList<RegistoDTO>();
    }

    @Override
    public void run() throws MedDBException {

        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        for (Registo registo : root.getRegistos()) {
            RegistoDTO r =
                    new RegistoDTO(registo.getConteudo(), registo.getPaciente().getNome(), registo.getMedico().getNome(), registo
                            .getEspecialidade().getNome(), registo.getEstabelecimento().getNome(), registo.getObjectId());
            this.registos.add(r);
        }

    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.registos;
    }
}
