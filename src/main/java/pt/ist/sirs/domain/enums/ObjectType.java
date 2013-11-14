package pt.ist.sirs.domain.enums;

import pt.ist.sirs.domain.MedDBCommon;

/**
 * Enumumerado <b>ObjectType</b>. <br>
 * <br>
 * Descreve o tipo de objecto de domínio. Apenas considera as folhas da árvore de herança da classe {@link MedDBCommon}. <br>
 * <br>
 * Utilizado para verificações de permissões.
 * 
 * @author Afonso F. Garcia
 */
public enum ObjectType {

    /** Objecto do tipo pt.ist.sirs.domain.Utente */
    PESSOA, REGISTO, MEDICO, ESTABELECIMENTO, ESPECIALIDADE;
}
