package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.RegistoJaExisteException;
import pt.ist.sirs.permissoes.Permissao;

public class CreateRegistoService extends MedDBService {

    private String conteudo;
    private Pessoa pessoa;
    private Medico medico;
    private Especialidade especialidade;
    private Permissao permissao;
    private Estabelecimento estabelecimento;

    public CreateRegistoService(Medico medico, Pessoa paciente, String conteudo, Especialidade especialidade,
            Permissao permissao, Estabelecimento estabelecimento) {
        this.medico = medico;
        this.pessoa = paciente;
        this.conteudo = conteudo;
        this.especialidade = especialidade;
        this.permissao = permissao;
        this.estabelecimento = estabelecimento;
    }

    @Override
    public void run() throws RegistoJaExisteException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        if (root.hasRegisto(this.medico, this.pessoa, this.conteudo, this.especialidade, this.permissao, this.estabelecimento)) {
            Registo novoRegisto = new Registo();
            novoRegisto.setConteudo(this.conteudo);
            novoRegisto.setEspecialidade(this.especialidade);
            novoRegisto.setMedico(this.medico);
            novoRegisto.setPaciente(this.pessoa);
            novoRegisto.setPermissao(this.permissao);
            novoRegisto.setEstabelecimento(this.estabelecimento);
        } else {

            throw new RegistoJaExisteException();
        }
    }
}
