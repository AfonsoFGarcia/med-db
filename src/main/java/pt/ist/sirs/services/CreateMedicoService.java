package pt.ist.sirs.services;

import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.exceptions.UsernameJaExisteException;

public class CreateMedicoService extends MedDBService {

    private String nome;
    private String password;
    private String username;
    private boolean medicoDeUrgencia;

    public CreateMedicoService(String username, String password, String nome, boolean medicoDeUrgencia) {
        this.nome = nome;
        this.password = password;
        this.username = username;
        this.medicoDeUrgencia = medicoDeUrgencia;
    }

    @Override
    public void run() throws UsernameJaExisteException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        if (!root.hasPerson(this.username)) {
            Medico novoMedico = new Medico();
            novoMedico.setNome(this.nome);
            novoMedico.setUsername(this.username);

            SecureRandom rand = new SecureRandom(novoMedico.getObjectId().toString().getBytes());
            byte[] saltBytes = new byte[32];
            rand.nextBytes(saltBytes);
            String salt = new String(saltBytes);
            String saltedPass = new String(DigestUtils.sha1(this.password + salt));

            novoMedico.setPassword(saltedPass);
            novoMedico.setSalt(salt);

            novoMedico.setMedicoDeUrgencia(this.medicoDeUrgencia);
        } else {
            throw new UsernameJaExisteException(this.username);
        }
    }
}
