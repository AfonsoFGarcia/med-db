package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.exceptions.ObjectoNaoExisteException;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PoliticaDeEspecialidade extends PoliticaDeEspecialidade_Base {

    public PoliticaDeEspecialidade() {
        super();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.setRoot(root);
    }

    public Especialidade getAcessor() throws ObjectoNaoExisteException {
        return (Especialidade) ((MedDBRoot) FenixFramework.getRoot()).getObjectByObjectID(getAcessorObjectID());
    }

    public Especialidade getAcedida() throws ObjectoNaoExisteException {
        return (Especialidade) ((MedDBRoot) FenixFramework.getRoot()).getObjectByObjectID(getAcedidaObjectID());
    }

    public void setAcessor(Especialidade e) {
        setAcessorObjectID(e.getObjectId());
    }

    public void setAcedida(Especialidade e) {
        setAcedidaObjectID(e.getObjectId());
    }

    /**
     * Verifica se o par <acessor, acedida> passado como parametros é o mesmo que o armazenado na classe.
     * 
     * @param acessor Especialidade acessora
     * @param acedida Especialidade acedida
     * @return true, if successful
     */
    public boolean equals(Especialidade acessor, Especialidade acedida) {
        return acessor.getObjectId().equals(getAcessorObjectID()) && acedida.getObjectId().equals(getAcedidaObjectID());
    }
}
