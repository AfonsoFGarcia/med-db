package pt.ist.sirs.services;

import java.security.SecureRandom;

import org.apache.commons.codec.digest.DigestUtils;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.NotAdminException;
import pt.ist.sirs.exceptions.UsernameJaExisteException;
import pt.ist.sirs.login.LoggedPerson;

/**
 * @author Afonso Garcia (70001), José Góis (79261)
 */
public class CreatePessoaService extends MedDBService {

    private String nome;
    private String username;
    private String password;

    public CreatePessoaService(String username, String password, String nome) {
        this.nome = nome;
        this.password = password;
        this.username = username;
    }

    @Override
    public void run() throws MedDBException {
        if (!LoggedPerson.getInstance().loggedPersonIsAdmin()) {
            throw new NotAdminException(LoggedPerson.getInstance().getLoggedPerson().getNome());
        }
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        if (!root.hasPerson(this.username)) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(this.nome);
            pessoa.setUsername(this.username);

            SecureRandom rand = new SecureRandom(pessoa.getObjectId().toString().getBytes());
            byte[] saltBytes = new byte[32];
            rand.nextBytes(saltBytes);
            String salt = new String(saltBytes);
            String saltedPass = new String(DigestUtils.sha1(this.password + salt));

            pessoa.setPassword(saltedPass);
            pessoa.setSalt(salt);
        } else {
            throw new UsernameJaExisteException(this.username);
        }
    }
}
