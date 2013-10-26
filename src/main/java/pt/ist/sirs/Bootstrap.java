package pt.ist.sirs;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;

/**
 * Classe <b>Bootstrap</b>. <br>
 * <br>
 * Inicializa a Fénix Framework. Gerada automaticamente pelo maven.
 */
public class Bootstrap {

    /**
     * Inicializa a Fénix Framework
     */
    public static void init() {
        try {
            FenixFramework.initialize(new Config() {
                {
                    domainModelPath = "/med-bd.dml";
                    dbAlias = "//localhost:3306/med-db";
                    dbUsername = "med-db";
                    dbPassword = "med-db-pass";
                    rootClass = MedDBRoot.class;
                }
            });
        } catch (Error e) {

        }
    }
}