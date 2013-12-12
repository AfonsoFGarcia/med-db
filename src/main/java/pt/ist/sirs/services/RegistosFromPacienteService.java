package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.PessoaNaoExisteException;
import pt.ist.sirs.services.dto.RegistoDTO;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class RegistosFromPacienteService extends MedDBService {

    private ArrayList<RegistoDTO> registos;
    private String pacienteUsername;

    public RegistosFromPacienteService(String pacienteUsername) {
        this.pacienteUsername = pacienteUsername;
        this.registos = new ArrayList<RegistoDTO>();
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasPerson(pacienteUsername)) {
            ArrayList<Registo> registos = root.getRegistosFromPaciente(pacienteUsername);

            for (Registo registo : registos) {
                RegistoDTO registoDto =
                        new RegistoDTO(registo.getConteudo(), registo.getPaciente().getNome(), registo.getMedico().getNome(),
                                registo.getEspecialidade().getNome(), registo.getEstabelecimento().getNome(),
                                registo.getObjectId());
                this.registos.add(registoDto);
            }
        } else {
            throw new PessoaNaoExisteException(pacienteUsername);
        }
    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.registos;
    }
}
