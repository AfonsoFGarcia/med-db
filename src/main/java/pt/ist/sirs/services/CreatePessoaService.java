package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.UsernameJaExisteException;

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
    public void run() throws UsernameJaExisteException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        if (!root.hasPerson(this.username)) {
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(this.nome);
            pessoa.setUsername(this.username);
            pessoa.setPassword(this.password);
        } else {
            throw new UsernameJaExisteException(this.username);
        }
    }

}
