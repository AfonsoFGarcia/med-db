package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;

// TODO: Auto-generated Javadoc
/**
 * Classe <b>Registo</b>.<br>
 * <br>
 * Mantém os registos médicos de uma pessoa.<br>
 * Permite obter o médico, pessoa, estabelecimento e especialidade associados.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see {@link Medico}, {@link Pessoa}, {@link Estabelecimento}, {@link Especialidade}
 */
public class Registo extends Registo_Base {

    /**
     * Instantiates a new registo.
     */
    public Registo() {
        super();
        this.setType(ObjectType.REGISTO);
    }

}
