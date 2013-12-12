package pt.ist.sirs.services;

import jvstm.Atomic;
import pt.ist.sirs.exceptions.MedDBException;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public abstract class MedDBService {

    public abstract void run() throws MedDBException;

    @Atomic
    public void execute() throws MedDBException {
        run();
    }
}
