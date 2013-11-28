package pt.ist.sirs.services;

import jvstm.Atomic;

public abstract class MedDBService {

    public abstract void run();

    @Atomic
    public void execute() {
        run();
    }
}
