package pt.ist.sirs.domain;

/**
 * Classe <b>MedDBRoot</b>. <br>
 * <br>
 * Disponibiliza a gestão centralizada de identificadores únicos utilizados pela classe {@link MedDBCommon}. <br>
 * Permite a procura de objectos pelo identificador.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class MedDBRoot extends MedDBRoot_Base {

    /**
     * Cria um objecto MedDBRoot
     */
    public MedDBRoot() {
        super();
        this.setObjectId(0);
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

    public MedDBCommon getObjectByObjectID(Integer objectID) {
        for (MedDBCommon object : this.getObject()) {
            if (object.getObjectId().equals(objectID)) {
                return object;
            }
        }
        return null;
    }

    /**
     * Verifica o acesso à especialidade na lista de pares <Especialidade, Especialidade> (associações permitidas estão na lista).
     * 
     * @param acessor Especialidade que quer aceder à acedida.
     * @param acedida Especialidade que vai ser acedida
     * @return true, se encontrar um par igual ao passado como argumento.
     */
    public boolean verificaAcessoAEspecialidade(Especialidade acessor, Especialidade acedida) {
        for (PoliticaDeEspecialidade p : this.getPoliticaDeEspecialidade()) {
            if (p.equals(acessor, acedida)) {
                return true;
            }
        }
        return false;
    }
}
