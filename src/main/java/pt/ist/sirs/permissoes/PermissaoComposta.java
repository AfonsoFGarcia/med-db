package pt.ist.sirs.permissoes;

import java.util.Set;
import java.util.TreeSet;

import pt.ist.sirs.domain.Registo;

/**
 * Classe <b>PermissaoComposta</b>.<br>
 * <br>
 * Define um conjunto de métodos utilizados em todas as permissões compostas.
 * 
 * @author Afonso F. Garcia (70001)
 * @see Permissao
 * @see Registo
 */
public abstract class PermissaoComposta extends Permissao {

    private static final long serialVersionUID = 1L;
    private Set<Permissao> permissoes;

    /**
     * Construtor que disponibiliza a associação entre o registo e a permissao.<br>
     * Não cria nenhum objecto pois a classe é abstracta.
     * 
     * @param r Registo associado à permissão.
     */
    public PermissaoComposta(Registo r) {
        super(r);
        permissoes = new TreeSet<Permissao>();
    }

    /**
     * Construtor que disponibiliza a associação entre o registo e a permissao.<br>
     * Permite criar uma permissão composta com um set de permissões já existente.<br>
     * Não cria nenhum objecto pois a classe é abstracta.
     * 
     * @param r Registo associado à permissão.
     * @param p Set de permissões para compor.
     */
    public PermissaoComposta(Registo r, Set<Permissao> p) {
        super(r);
        permissoes = p;
    }

    /**
     * Adiciona uma permissão à permissão composta.
     * 
     * @param p Permissão a adicionar.
     */
    public void addPermissao(Permissao p) {
        permissoes.add(p);
    }

    /**
     * Retira uma permissão da permissão composta.
     * 
     * @param p Permissão a remover.
     */
    public void removePermissao(Permissao p) {
        permissoes.remove(p);
    }

    /**
     * Obtém as permissões na composição.
     * 
     * @return Set de permissões.
     */
    public Set<Permissao> getPermissoes() {
        return permissoes;
    }
}
