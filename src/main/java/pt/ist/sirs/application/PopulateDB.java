package pt.ist.sirs.application;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.CreateMedicoService;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PopulateDB {

    /**
     * Main da aplicação
     * 
     * @param args Não utilizado
     * @throws MedDBException
     */
    public static void main(String[] args) throws MedDBException {
        Bootstrap.init();

        //Objectos a adicionar a DB no inicio
        CreateMedicoService medico1 = new CreateMedicoService("josel", "1234", "Jose", false);
        medico1.execute();
    }
}
