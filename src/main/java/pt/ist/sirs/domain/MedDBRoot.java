package pt.ist.sirs.domain;

/**
 * Classe <b>MedDBRoot</b>. <br>
 * <br>
 * Disponibiliza a gestão centralizada de identificadores únicos utilizados pela classe {@link MedDBCommon}. <br>
 * Permite a procura de objectos pelo identificador.
 * 
 * @author Afonso F. Garcia
 */
public class MedDBRoot extends MedDBRoot_Base {

    /**
     * Cria um objecto MedDBRoot
     */
    public MedDBRoot() {
        super();
    }

    /**
     * Cria um identificador para utilização num objecto da classe {@link MedDBCommon} ou classes que herdem desta
     * 
     * @return Identificador único do objecto
     */
    public Integer getNewObjectId() {
        Integer newObjectId = this.getObjectId();
        this.setObjectId(++newObjectId);
        return newObjectId;
    }

}
