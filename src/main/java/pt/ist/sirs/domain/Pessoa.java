package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;

/**
 * Classe <b>Pessoa</b>.<br>
 * <br>
 * Define uma pessoa.<br>
 * Permite obter os médicos da pessoa e os registos a ela associados.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see Medico
 * @see Registo
 */
public class Pessoa extends Pessoa_Base {

    /**
     * Cria um objecto Pessoa
     */
    public Pessoa() {
        super();
        this.setType(ObjectType.PESSOA);
    }

}
