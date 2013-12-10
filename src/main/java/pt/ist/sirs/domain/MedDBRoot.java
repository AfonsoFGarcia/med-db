package pt.ist.sirs.domain;

import java.util.ArrayList;

import pt.ist.sirs.exceptions.ObjectoNaoExisteException;
import pt.ist.sirs.exceptions.PessoaNaoExisteException;
import pt.ist.sirs.login.LoggedPerson;

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
        this.setDefaultPermission("or(pdr,mdu,mdr,and(ppde,mdest))");
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

    public MedDBCommon getObjectByObjectID(Integer objectID) throws ObjectoNaoExisteException {
        for (MedDBCommon object : this.getObject()) {
            if (object.getObjectId().equals(objectID)) {
                return object;
            }
        }
        throw new ObjectoNaoExisteException(objectID);
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

    public boolean hasRegisto(Medico medico, Pessoa paciente, String conteudo, Especialidade especialidade,
            Estabelecimento estabelecimento) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo && ((Registo) object).getMedico().equals(medico)
                    && ((Registo) object).getPaciente().equals(paciente) && ((Registo) object).getConteudo().equals(conteudo)
                    && ((Registo) object).getEspecialidade().equals(especialidade)
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

    public boolean hasEstabelecimento(String nome) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Estabelecimento && ((Estabelecimento) object).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEspecialidade(String nome) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Especialidade && ((Especialidade) object).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasEspecialidade(Integer id) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Especialidade && ((Especialidade) object).getObjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Registo> getRegistosFromPaciente(String pacienteUsername) {
        ArrayList<Registo> registos = new ArrayList<Registo>();

        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo && ((Registo) object).getPaciente().getUsername().equals(pacienteUsername)) {
                Registo r = (Registo) object;
                Medico m = (Medico) LoggedPerson.getInstance().getLoggedPerson();
                if (r.getPermissao().isAllowed(m)) {
                    registos.add(r);
                    continue;
                }
            }
        }

        return registos;

    }

    public ArrayList<Registo> getRegistosByEspecialidade(Integer idEspecialidade) {
        ArrayList<Registo> registos = new ArrayList<Registo>();

        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo) {
                Registo r = (Registo) object;
                Medico m = (Medico) LoggedPerson.getInstance().getLoggedPerson();
                if (r.getPermissao().isAllowed(m)) {
                    registos.add(r);
                    continue;
                }
            }
        }

        return registos;
    }
}
