package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.dto.RegistoDTO;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class RegistosPropriosService extends MedDBService {

    private RegistosFromPacienteService recServ;

    @Override
    public void run() throws MedDBException {
        String username = LoggedPerson.getInstance().getLoggedPerson().getUsername();
        recServ = new RegistosFromPacienteService(username);
        recServ.run();
    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.recServ.getRegistos();
    }

}
