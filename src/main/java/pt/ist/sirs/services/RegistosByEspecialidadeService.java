package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.dto.RegistoDTO;

public class RegistosByEspecialidadeService extends MedDBService {

    private ArrayList<RegistoDTO> registos;
    private Integer idEspecialidade;

    public RegistosByEspecialidadeService(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
        this.registos = new ArrayList<RegistoDTO>();
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        ArrayList<Registo> registos = root.getRegistosByEspecialidade(idEspecialidade);

        for (Registo registo : registos) {
            RegistoDTO registoDto =
                    new RegistoDTO(registo.getConteudo(), registo.getPaciente().getNome(), registo.getMedico().getNome(), registo
                            .getEspecialidade().getNome(), registo.getEstabelecimento().getNome(), registo.getObjectId());
            this.registos.add(registoDto);
        }
    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.registos;
    }
}
