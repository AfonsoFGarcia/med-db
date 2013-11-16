package pt.ist.sirs.domain.enums;

import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.MedDBCommon;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * Enumumerado <b>ObjectType</b>. <br>
 * <br>
 * Descreve o tipo de objecto de domínio. Apenas considera as folhas da árvore de herança da classe {@link MedDBCommon}. <br>
 * <br>
 * Utilizado para verificações de permissões.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Pessoa
 * @see Registo
 * @see Medico
 * @see Estabelecimento
 * @see Especialidade
 */
public enum ObjectType {

    PESSOA, REGISTO, MEDICO, ESTABELECIMENTO, ESPECIALIDADE;
}
