package pt.ist.sirs.application;

import jvstm.Atomic;
import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;
import pt.ist.sirs.permissoes.Permissao;
import pt.ist.sirs.permissoes.PermissaoPublica;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia
 */
public class MedDBApp {

    /**
     * Main da aplicação
     * 
     * @param args Não utilizado
     */
    public static void main(String[] args) {
        Bootstrap.init();

        System.out.println("Fenix Framework inited");

        testApp();
    }

    @Atomic
    private static void testApp() {
        Pessoa p = new Pessoa();
        Medico m = new Medico();
        Registo r = new Registo();
        Especialidade e = new Especialidade();
        Estabelecimento h = new Estabelecimento();
        PermissaoPublica pp = new PermissaoPublica(r);

        System.out.println("Objects created");

        m.addEspecialidades(e);

        h.addMedicos(m);

        r.setMedico(m);

        r.setPessoa(p);

        r.setEspecialidade(e);

        r.setEstabelecimento(h);

        r.setPermissao(pp);

        String perm = pp.toString();
        PermissaoPublica pps = (PermissaoPublica) Permissao.fromString(perm);
    }
}
