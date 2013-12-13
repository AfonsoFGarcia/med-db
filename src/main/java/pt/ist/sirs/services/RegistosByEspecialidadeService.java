package pt.ist.sirs.services;

import java.util.ArrayList;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.dto.RegistoDTO;
import pt.ist.sirs.utils.Seguranca;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
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
        if (root.hasEspecialidade(idEspecialidade)) {

            ArrayList<Registo> registos = root.getRegistosByEspecialidade(idEspecialidade);

            for (Registo registo : registos) {
                RegistoDTO registoDto =
                        new RegistoDTO(Seguranca.decrypt(registo.getConteudo()), registo.getPaciente().getNome(), registo
                                .getMedico().getNome(), registo.getEspecialidade().getNome(), registo.getEstabelecimento()
                                .getNome(), registo.getObjectId());
                this.registos.add(registoDto);
            }
        } else {
            throw new EspecialidadeNaoExisteException(idEspecialidade);
        }
    }

    public ArrayList<RegistoDTO> getRegistos() {
        return this.registos;
    }
}
