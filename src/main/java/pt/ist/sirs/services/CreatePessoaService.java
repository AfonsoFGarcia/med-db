package pt.ist.sirs.services;

import pt.ist.sirs.domain.Pessoa;

public class CreatePessoaService extends MedDBService {

    private String nome;
    private String password;

    public CreatePessoaService(String nome, String password) {
        this.nome = nome;
        this.password = password;
    }

    @Override
    public void run() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(this.nome);
        pessoa.setPassword(this.password);
    }

}
