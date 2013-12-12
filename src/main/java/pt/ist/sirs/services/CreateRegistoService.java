package pt.ist.sirs.services;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.exceptions.EspecialidadeNaoExisteException;
import pt.ist.sirs.exceptions.EstabelecimentoNaoExisteException;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.exceptions.PessoaNaoExisteException;
import pt.ist.sirs.exceptions.RegistoJaExisteException;
import pt.ist.sirs.utils.LoggedPerson;

/**
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class CreateRegistoService extends MedDBService {

    private String conteudo;
    private String userPessoa;
    private Integer idEspecialidade;
    private Integer idEstabelecimento;

    public CreateRegistoService(String userPaciente, String conteudo, Integer idEspecialidade, Integer idEstabelecimento) {
        this.userPessoa = userPaciente;
        this.conteudo = conteudo;
        this.idEspecialidade = idEspecialidade;
        this.idEstabelecimento = idEstabelecimento;
    }

    @Override
    public void run() throws MedDBException {
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        Medico medico = (Medico) LoggedPerson.getInstance().getLoggedPerson();
        if (root.hasPerson(userPessoa)) {
            Pessoa pessoa = (Pessoa) root.getPersonByUsername(userPessoa);
            if (root.hasEspecialidade(idEspecialidade)) {
                Especialidade especialidade = (Especialidade) root.getObjectByObjectID(idEspecialidade);
                if (root.hasEstabelecimento(idEstabelecimento)) {
                    Estabelecimento estabelecimento = (Estabelecimento) root.getObjectByObjectID(idEstabelecimento);
                    if (!root.hasRegisto(medico, pessoa, this.conteudo, especialidade, estabelecimento)) {
                        Registo novoRegisto = new Registo();
                        novoRegisto.setConteudo(this.conteudo);
                        novoRegisto.setEspecialidade(especialidade);
                        novoRegisto.setMedico(medico);
                        novoRegisto.setPaciente(pessoa);
                        novoRegisto.setEstabelecimento(estabelecimento);
                        pessoa.addMedicos(medico);
                    } else {
                        throw new RegistoJaExisteException();
                    }
                } else {
                    throw new EstabelecimentoNaoExisteException(idEstabelecimento);
                }
            } else {
                throw new EspecialidadeNaoExisteException(idEspecialidade);
            }
        } else {
            throw new PessoaNaoExisteException(userPessoa);

        }
    }
}
