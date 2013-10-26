package pt.ist.sirs.application;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.MedDBRoot;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia
 */
public class MedDBApp {

    /**
     * Main da aplicação
     * 
     * @param args Não utilizado
     */
    public static void main(String[] args) {
        Bootstrap.init();
        initMedDBRoot();
    }

    /**
     * Inicializa o objecto {@link MedDBRoot}
     */
    private static void initMedDBRoot() {
        Transaction.begin();
        MedDBRoot app = (MedDBRoot) FenixFramework.getRoot();
        if (app.getObjectId() < 1) {
            app.setObjectId(0);
        }
        Transaction.commit();
    }
}
