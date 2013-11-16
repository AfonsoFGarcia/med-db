package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;

/**
 * Classe <b>Especialidade</b>. <br>
 * <br>
 * Define a especialidade do médico.<br>
 * Permite obter os médicos da especialidade, bem como todos os registos associados a esta especialidade.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see {@link Medico}, {@link Registo}
 */
public class Especialidade extends Especialidade_Base {

    /**
     * Cria um objecto Especialidade.
     */
    public Especialidade() {
        super();
        this.setType(ObjectType.ESPECIALIDADE);
    }

}
