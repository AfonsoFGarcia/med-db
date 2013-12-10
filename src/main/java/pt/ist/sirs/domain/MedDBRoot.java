package pt.ist.sirs.domain;

import pt.ist.sirs.exceptions.PessoaNaoExisteException;
import pt.ist.sirs.permissoes.Permissao;

/**
 * Classe <b>MedDBRoot</b>. <br>
 * <br>
 * Disponibiliza a gestão centralizada de identificadores únicos utilizados pela classe {@link MedDBCommon}. <br>
 * Permite a procura de objectos pelo identificador.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class MedDBRoot extends MedDBRoot_Base {

    /**
     * Cria um objecto MedDBRoot
     */
    public MedDBRoot() {
        super();
        this.setObjectId(0);
    }

    /**
     * Cria um identificador para utilização num objecto da classe {@link MedDBCommon} ou classes que herdem desta
     * 
     * @return Identificador único do objecto
     */
    public Integer getNewObjectId() {
        Integer newObjectId = this.getObjectId();
        this.setObjectId(++newObjectId);
        return newObjectId;
    }

    public MedDBCommon getObjectByObjectID(Integer objectID) {
        for (MedDBCommon object : this.getObject()) {
            if (object.getObjectId().equals(objectID)) {
                return object;
            }
        }
        return null;
    }

    public Pessoa getPersonByUsername(String username) throws PessoaNaoExisteException {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Pessoa && ((Pessoa) object).getUsername().equals(username)) {
                return (Pessoa) object;
            }
        }
        throw new PessoaNaoExisteException(username);
    }

    public boolean hasPerson(String username) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Pessoa && ((Pessoa) object).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRegisto(Medico medico, Pessoa paciente, String conteudo, Especialidade especialidade, Permissao permissao,
            Estabelecimento estabelecimento) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo && ((Registo) object).getMedico().equals(medico)
                    && ((Registo) object).getPaciente().equals(paciente) && ((Registo) object).getConteudo().equals(conteudo)
                    && ((Registo) object).getEspecialidade().equals(especialidade)
                    && ((Registo) object).getPermissao().equals(permissao)
                    && ((Registo) object).getEstabelecimento().equals(estabelecimento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica o acesso à especialidade na lista de pares <Especialidade, Especialidade> (associações permitidas estão na lista).
     * 
     * @param acessor Especialidade que quer aceder à acedida.
     * @param acedida Especialidade que vai ser acedida
     * @return true, se encontrar um par igual ao passado como argumento.
     */
    public boolean verificaAcessoAEspecialidade(Especialidade acessor, Especialidade acedida) {
        for (PoliticaDeEspecialidade p : this.getPoliticaDeEspecialidade()) {
            if (p.equals(acessor, acedida)) {
                return true;
            }
        }
        return false;
    }
}
