package pt.ist.sirs.domain;

/**
 * Classe <b>MedDBCommon</b>. <br>
 * <br>
 * Disponibiliza identificadores únicos, ligação automática ao {@link MedDBRoot} e identificadores do tipo de objecto.
 * 
 * @author Afonso F. Garcia
 */
public class MedDBCommon extends MedDBCommon_Base {

    /**
     * Cria um objecto MedDBCommon
     */
    @Deprecated
    public MedDBCommon() {
        super();
    }

    /**
     * Cria um objecto MedDBCommon ligado ao {@link MedDBRoot}
     * 
     * @param root Objecto root do Med-DB
     */
    public MedDBCommon(MedDBRoot root) {
        super();
        this.setObjectId(root.getNewObjectId());
        this.setRoot(root);
        root.addObject(this);
    }
}
