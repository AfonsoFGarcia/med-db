package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.dto.EspecialidadeDTO;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class GetEspecialidadesService extends MedDBService {

    private ArrayList<EspecialidadeDTO> especialidades;

    public GetEspecialidadesService() {
        this.especialidades = new ArrayList<EspecialidadeDTO>();
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        for (Especialidade especialidadade : root.getEspecialidades()) {
            EspecialidadeDTO e = new EspecialidadeDTO(especialidadade.getObjectId(), especialidadade.getNome());
            this.especialidades.add(e);
        }

    }

    public ArrayList<EspecialidadeDTO> getEspecialidades() {
        return this.especialidades;
    }
}
