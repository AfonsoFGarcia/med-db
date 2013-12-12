package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;

/**
 * Classe <b>MedicoBanidoDeEspecialidade</b>.<br>
 * <br>
 * Especifica que o Medico nao pode aceder a registos da Especialidade.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Medico
 * @see Especialidade
 */
public class MedicoBanidoDeEspecialidade extends MedicoBanidoDeEspecialidade_Base {

    /**
     * Cria um objecto MedicoBanidoDeEspecialidade
     */
    public MedicoBanidoDeEspecialidade() {
        super();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.setRoot(root);
    }

}
