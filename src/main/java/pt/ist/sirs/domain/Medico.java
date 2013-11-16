package pt.ist.sirs.domain;

import pt.ist.sirs.domain.enums.ObjectType;

/**
 * Classe <b>Medico</b>.<br>
 * <br>
 * Define um médico. Um médico é uma pessoa (pode ser um paciente).<br>
 * Permite obter os pacientes, os estabelecimentos onde trabalha, as especialidades e os registos a ele associados.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261), João Santos (79276)
 * @see Pessoa
 * @see Estabelecimento
 * @see Especialidade
 * @see Registo
 */
public class Medico extends Medico_Base {

    /**
     * Cria um objecto Medico.
     */
    public Medico() {
        super();
        this.setType(ObjectType.MEDICO);
    }

}
