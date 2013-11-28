package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;
import pt.ist.sirs.permissoes.PermissaoComposta;
import pt.ist.sirs.permissoes.PermissaoMedicoDeUrgencia;
import pt.ist.sirs.permissoes.PermissaoMedicoDoEstabelecimento;
import pt.ist.sirs.permissoes.PermissaoPacienteDoRegisto;
import pt.ist.sirs.permissoes.PermissaoPoliticaDeEspecialidade;
import pt.ist.sirs.permissoes.logicas.PermissaoELogico;
import pt.ist.sirs.permissoes.logicas.PermissaoOuLogico;

/**
 * Classe <b>Registo</b>.<br>
 * <br>
 * Mantém os registos médicos de uma pessoa.<br>
 * Permite obter o médico, pessoa, estabelecimento e especialidade associados.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see Medico
 * @see Pessoa
 * @see Estabelecimento
 * @see Especialidade
 */
public class Registo extends Registo_Base {

    /**
     * Instantiates a new registo.
     */
    public Registo() {
        super();
        this.setType(ObjectType.REGISTO);

        PermissaoComposta permissaoMedico = new PermissaoELogico(this);
        permissaoMedico.addPermissao(new PermissaoPoliticaDeEspecialidade(this));
        permissaoMedico.addPermissao(new PermissaoMedicoDoEstabelecimento(this));

        PermissaoComposta permissao = new PermissaoOuLogico(this);
        permissao.addPermissao(new PermissaoMedicoDeUrgencia(this));
        permissao.addPermissao(new PermissaoPacienteDoRegisto(this));
        permissao.addPermissao(permissaoMedico);

        this.setPermissao(permissao);
    }

}
