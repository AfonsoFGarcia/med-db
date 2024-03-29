package pt.ist.sirs.domain;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.enums.ObjectType;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoBuilderParser;

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
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();
        Permissao perm = PermissaoBuilderParser.getPermissao(root.getDefaultPermission());
        perm.setRegisto(this);
        this.setPermissao(perm);
    }

}
