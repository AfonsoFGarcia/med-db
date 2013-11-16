package pt.ist.sirs.permissoes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public abstract class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;
    Registo registo;

    Permissao(Registo r) {
        registo = r;
    }

    public abstract boolean isAllowed(Pessoa pessoa);

    @Override
    public String toString() {
        String object = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(this);
            object = baos.toString();

            oos.close();
        } catch (IOException e) {
            System.out.println("FAILED: Could not serialize permission for object " + registo.getObjectId() + ".");
            e.printStackTrace();
        }

        return object;
    }

    public static Permissao fromString(String perm) {
        Permissao permissao = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(perm.getBytes());
            ObjectInputStream ois = new ObjectInputStream(bais);

            permissao = (Permissao) ois.readObject();

            ois.close();
        } catch (IOException e) {
            System.out.println("FAILED: Could not deserialize permission.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("FAILED: Could not materialize permission.");
            e.printStackTrace();
        }

        return permissao;
    }

    public Registo getRegisto() {
        return registo;
    }

    public void setRegisto(Registo registo) {
        this.registo = registo;
    }

}
