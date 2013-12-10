package pt.ist.sirs.application;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.CreateEspecialidadeService;
import pt.ist.sirs.services.CreateEstabelecimentoService;
import pt.ist.sirs.services.CreateMedicoService;
import pt.ist.sirs.services.CreatePessoaService;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia (70001)
 */
public class PopulateDB {

    /**
     * Main da aplicação
     * 
     * @param args Não utilizado
     * @throws MedDBException
     */
    public static void main(String[] args) throws MedDBException {
        Bootstrap.init();

        //Objectos a adicionar a DB no inicio
        CreateMedicoService medico1 = new CreateMedicoService("josel", "1234", "Jose Lagarto", false);
        medico1.execute();

        CreatePessoaService pessoa1 = new CreatePessoaService("bertor", "1234", "Alberto Ramalho");
        pessoa1.execute();

        CreateEspecialidadeService espec1 = new CreateEspecialidadeService("Psicologia");
        espec1.execute();

        CreateEstabelecimentoService estab1 = new CreateEstabelecimentoService("Hospital Julio de Matos");
        estab1.execute();
    }
}
