package pt.ist.sirs.permissoes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.codec.binary.Base64;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.MedicoBanidoDeEspecialidade;
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

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            System.out.println("Erro na serializacao da permissao!");
        }
        return new String(Base64.encodeBase64(baos.toByteArray()));
    }

    /**
     * Reconstrói uma permissão a partir da string serializada.
     * 
     * @param perm Permissão a ser reconstruída.
     * @return Permissão reconstruída.
     */
    public static Permissao fromString(String perm) {
        byte[] data = Base64.decodeBase64(perm);
        ObjectInputStream ois;
        Permissao p = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            p = (Permissao) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println("Erro na serializacao da permissao!");
        }
        return p;
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

    protected boolean medicoBanido(Medico medico) {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        for (MedicoBanidoDeEspecialidade m : root.getMedicoBanidoDeEspecialidade()) {
            if (this.registo.getEspecialidade().getObjectId().equals(m.getEspecialidadeObjectID())
                    && medico.getObjectId().equals(m.getMedicoObjectID())) {
                return true;
            }
        }
        return false;
    }
}
