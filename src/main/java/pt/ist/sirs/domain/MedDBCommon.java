package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;

/**
 * Classe <b>MedDBCommon</b>. <br>
 * <br>
 * Disponibiliza identificadores únicos, ligação automática ao {@link MedDBRoot} e identificadores do tipo de objecto.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class MedDBCommon extends MedDBCommon_Base {

    /**
     * Cria um objecto MedDBCommon ligado ao {@link MedDBRoot}
     */
    public MedDBCommon() {
        super();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.setObjectId(root.getNewObjectId());
        this.setRoot(root);
        root.addObject(this);
    }
}
