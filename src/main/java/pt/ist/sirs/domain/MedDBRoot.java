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
 * @author Afonso F. Garcia (70001), José Góis (79261)
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

    /**
     * Devolve o objecto de dominio com o ID desejado
     * 
     * @param objectID ID do objecto a ser retornado
     * @return Objecto de dominio com o ID objectID
     * @throws ObjectoNaoExisteException
     */
    public MedDBCommon getObjectByObjectID(Integer objectID) throws ObjectoNaoExisteException {
        for (MedDBCommon object : this.getObject()) {
            if (object.getObjectId().equals(objectID)) {
                return object;
            }
        }
        throw new ObjectoNaoExisteException(objectID);
    }

    /**
     * Devolve a {@link Pessoa} com o username desejado
     * 
     * @param username Username da pessoa a ser retornada
     * @return Pessoa com o username username
     * @throws PessoaNaoExisteException
     */
    public Pessoa getPersonByUsername(String username) throws PessoaNaoExisteException {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Pessoa && ((Pessoa) object).getUsername().equals(username)) {
                return (Pessoa) object;
            }
        }
        throw new PessoaNaoExisteException(username);
    }

    /**
     * Verifica se a {@link Pessoa} existe
     * 
     * @param username Username da pessoa a verificar
     * @return true, se a pessoa existir
     */
    public boolean hasPerson(String username) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Pessoa && ((Pessoa) object).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o {@link Registo} existe
     * 
     * @param id ID do registo a verificar
     * @return true, se o registo existir
     */
    public boolean hasRegisto(Integer id) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo && ((Registo) object).getObjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o {@link Registo} existe
     * 
     * @param medico Medico do registo
     * @param paciente Paciente do registo
     * @param conteudo Conteudo do registo
     * @param especialidade Especialidade do registo
     * @param estabelecimento Estabelecimento do registo
     * @return true, se o registo existir
     */
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
     * @param acessor Especialidade que quer aceder à acedida
     * @param acedida Especialidade que vai ser acedida
     * @return true, se encontrar um par igual ao passado como argumento
     */
    public boolean verificaAcessoAEspecialidade(Especialidade acessor, Especialidade acedida) {
        for (PoliticaDeEspecialidade p : this.getPoliticaDeEspecialidade()) {
            if (p.equals(acessor, acedida)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o {@link Estabelecimento} existe
     * 
     * @param nome Nome do estabelecimento
     * @return true, se o estabelecimento exisir
     */
    public boolean hasEstabelecimento(String nome) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Estabelecimento && ((Estabelecimento) object).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se o {@link Estabelecimento} existe
     * 
     * @param idEstabelecimento ID do estabelecimento
     * @return true, se o estabelecimento existir
     */
    public boolean hasEstabelecimento(Integer idEstabelecimento) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Estabelecimento && ((Estabelecimento) object).getObjectId().equals(idEstabelecimento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a {@link Especialidade} existe
     * 
     * @param nome Nome da especialidade
     * @return true, se a especialidade existir
     */
    public boolean hasEspecialidade(String nome) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Especialidade && ((Especialidade) object).getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se a {@link Especialidade} existe
     * 
     * @param id ID da especialidade
     * @return true, se a especialidade existir
     */
    public boolean hasEspecialidade(Integer id) {
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Especialidade && ((Especialidade) object).getObjectId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtem os registos de um paciente que a pessoa com login feito tem acesso
     * 
     * @param pacienteUsername Username do paciente
     * @return Lista de registos do paciente
     */
    public ArrayList<Registo> getRegistosFromPaciente(String pacienteUsername) {
        ArrayList<Registo> registos = new ArrayList<Registo>();

        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo && ((Registo) object).getPaciente().getUsername().equals(pacienteUsername)) {
                Registo r = (Registo) object;
                Pessoa m = LoggedPerson.getInstance().getLoggedPerson();
                if (r.getPermissao().isAllowed(m)) {
                    registos.add(r);
                    continue;
                }
            }
        }

        return registos;

    }

    /**
     * Obtem os registos de uma especialidade que a pessoa com login feito tem acesso
     * 
     * @param idEspecialidade ID da especialidade
     * @return Lista de registos da especialidade
     */
    public ArrayList<Registo> getRegistosByEspecialidade(Integer idEspecialidade) {
        ArrayList<Registo> registos = new ArrayList<Registo>();

        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo) {
                Registo r = (Registo) object;
                Pessoa m = LoggedPerson.getInstance().getLoggedPerson();
                if (r.getPermissao().isAllowed(m) && r.getEspecialidade().getObjectId().equals(idEspecialidade))) {
                    registos.add(r);
                    continue;
                }
            }
        }

        return registos;
    }

    /**
     * Obtem as especialidades existentes no sistema
     * 
     * @return Lista de especialidades
     */
    public ArrayList<Especialidade> getEspecialidades() {
        ArrayList<Especialidade> especialidades = new ArrayList<Especialidade>();
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Especialidade) {
                Especialidade e = (Especialidade) object;
                especialidades.add(e);
                continue;
            }
        }

        return especialidades;
    }

    /**
     * Obtem os registos existentes no sistema<br>
     * <br>
     * <b>ATENCAO! So usar em servicos de administracao!</b>
     * 
     * @return Lista de registos
     */
    public ArrayList<Registo> getRegistos() {
        ArrayList<Registo> registos = new ArrayList<Registo>();
        for (MedDBCommon object : this.getObject()) {
            if (object instanceof Registo) {
                Registo r = (Registo) object;
                registos.add(r);
                continue;
            }
        }

        return registos;
    }
}
