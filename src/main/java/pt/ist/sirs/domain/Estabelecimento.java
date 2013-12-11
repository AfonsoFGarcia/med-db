package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;

/**
 * Classe <b>Estabelecimento</b>.<br>
 * <br>
 * Define o local onde o médico pratica a sua actividade.<br>
 * Permite obter os médicos do estabelecimento bem como os registos associados.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see Medico
 * @see Registo
 */
public class Estabelecimento extends Estabelecimento_Base {

    /**
     * Cria um objecto Estabelecimento.
     */
    public Estabelecimento() {
        super();
        this.setType(ObjectType.ESTABELECIMENTO);
    }

}
