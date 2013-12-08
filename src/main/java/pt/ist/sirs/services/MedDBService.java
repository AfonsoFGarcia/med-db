package pt.ist.sirs.services;

import jvstm.Atomic;
import pt.ist.sirs.exceptions.MedDBException;

public abstract class MedDBService {

    public abstract void run() throws MedDBException;

    @Atomic
    public void execute() throws MedDBException {
        run();
    }
}
