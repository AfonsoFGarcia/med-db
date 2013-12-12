package pt.ist.sirs.permissoes;

import java.util.List;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.sirs.domain.Especialidade;
import pt.ist.sirs.domain.MedDBRoot;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PermissaoPoliticaDeEspecialidade extends Permissao {
    private static final long serialVersionUID = 1L;

    public PermissaoPoliticaDeEspecialidade(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        Medico medico = (Medico) pessoa;
        List<Especialidade> especialidades = medico.getEspecialidades();
        Especialidade especialidadeDoRegisto = registo.getEspecialidade();
        MedDBRoot root = (MedDBRoot) FenixFramework.getRoot();

        for (Especialidade especialidade : especialidades) {
            if (root.verificaAcessoAEspecialidade(especialidade, especialidadeDoRegisto)) {
                return true && !super.medicoBanido(medico);
            }
        }

        return false;
    }

}
