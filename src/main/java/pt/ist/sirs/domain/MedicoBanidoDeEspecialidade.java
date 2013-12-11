package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;

public class MedicoBanidoDeEspecialidade extends MedicoBanidoDeEspecialidade_Base {

    public MedicoBanidoDeEspecialidade() {
        super();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        this.setRoot(root);
    }

}
