package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.exceptions.ObjectoNaoExisteException;

/**
 * Classe <b>PoliticaDeEspecialidade</b>.<br>
 * <br>
 * Especifica que os medicos da especialidade x podem aceder aos registos da especialidade y.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Medico
 * @see Especialidade
 * @see Registo
 */
public class PoliticaDeEspecialidade extends PoliticaDeEspecialidade_Base {

    /**
     * Cria um objecto PoliticaDeEspecialidade
     */
    public PoliticaDeEspecialidade() {
        super();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.setRoot(root);
    }

    /**
     * Devolve o objecto {@link Especialidade} que acede
     * 
     * @return Especialidade acessora
     * @throws ObjectoNaoExisteException
     */
    public Especialidade getAcessor() throws ObjectoNaoExisteException {
        return (Especialidade) ((MedDBRoot) FenixFramework.getRoot()).getObjectByObjectID(getAcessorObjectID());
    }

    /**
     * Devolve o objecto {@link Especialidade} que e acedido
     * 
     * @return Especialidade acedida
     * @throws ObjectoNaoExisteException
     */
    public Especialidade getAcedida() throws ObjectoNaoExisteException {
        return (Especialidade) ((MedDBRoot) FenixFramework.getRoot()).getObjectByObjectID(getAcedidaObjectID());
    }

    /**
     * Define o objecto {@link Especialidade} que acede
     * 
     * @param e Especialidade acessora
     */
    public void setAcessor(Especialidade e) {
        setAcessorObjectID(e.getObjectId());
    }

    /**
     * Define o objecto {@link Especialidade} que e acedida
     * 
     * @param e Especialidade acedida
     */
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
