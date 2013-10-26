package pt.ist.sirs.application;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.pstm.Transaction;
import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.MedDBRoot;

public class MedDBApp {

    public static void main(String[] args) {
        Bootstrap.init();
        initMedBDRoot();
    }

    private static void initMedBDRoot() {
        Transaction.begin();
        MedDBRoot app = (MedDBRoot) FenixFramework.getRoot();
        if (app.getObjectId() < 1) {
            app.setObjectId(0);
        }
        Transaction.commit();
    }
}
