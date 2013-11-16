package pt.ist.sirs.permissoes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * Classe <b>Permissao</b>.<br>
 * <br>
 * Implementa o sistema de permissões dos registos, garantindo a confidencialidade e o acesso aos dados apenas por quem é
 * autorizado a aceder-lhes.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Registo
 * @see Pessoa
 */
public abstract class Permissao implements Serializable {

    private static final long serialVersionUID = 1L;
    Registo registo;

    /**
     * Construtor que disponibiliza a associação entre o registo e a permissao.<br>
     * Não cria nenhum objecto pois a classe é abstracta.
     * 
     * @param r Registo associado à permissão.
     */
    protected Permissao(Registo r) {
        registo = r;
    }

    /**
     * Verifica se a pessoa pode aceder ao registo associado.
     * 
     * @param pessoa Pessoa que quer aceder.
     * @return true, se a pessoa tiver permissão de acesso.
     */
    public abstract boolean isAllowed(Pessoa pessoa);

    /**
     * Serializa o objecto para armazenamento na base de dados.
     * 
     * @return Objecto serializado numa string.
     */
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

    /**
     * Reconstrói uma permissão a partir da string serializada.
     * 
     * @param perm Permissão a ser reconstruída.
     * @return Permissão reconstruída.
     */
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

    /**
     * Obtém o registo associado à permissão.
     * 
     * @return Registo associado à permissão.
     */
    public Registo getRegisto() {
        return registo;
    }

    /**
     * Define o registo associado à permissão.
     * 
     * @param registo Registo a associar à permissão.
     */
    public void setRegisto(Registo registo) {
        this.registo = registo;
    }

}
