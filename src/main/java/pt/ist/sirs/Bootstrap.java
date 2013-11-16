package pt.ist.sirs;

import pt.ist.fenixframework.Config;
import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;

public class Bootstrap {

  public static void init() {
    try {
      FenixFramework.initialize(new Config() {{
        domainModelPath = "/med-db.dml";
        dbAlias = "//localhost:3306/med-db";
        dbUsername = "med-db";
        dbPassword = "med-db-pass";
        rootClass = MedDBRoot.class;
      }});
    } catch(Error e) {
      
    }
  }
}
