package pt.ist.sirs.permissoes;

import java.util.List;

import pt.ist.sirs.domain.Estabelecimento;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.domain.Registo;

public class PermissaoMedicoDoEstabelecimento extends Permissao {
    private static final long serialVersionUID = 1L;

    protected PermissaoMedicoDoEstabelecimento(Registo r) {
        super(r);
    }

    @Override
    public boolean isAllowed(Pessoa pessoa) {
        Medico medico = (Medico) pessoa;
        List<Estabelecimento> estabelecimentos = medico.getEstabelecimentos();

        for (Estabelecimento estabelecimento : estabelecimentos) {
            if (registo.getEstabelecimento().getObjectId().equals(estabelecimento.getObjectId())) {
                return true;
            }
        }
        return false;
    }
}
