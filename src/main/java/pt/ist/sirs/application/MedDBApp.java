package pt.ist.sirs.application;

import java.io.IOException;
import java.util.ArrayList;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.login.LoggedPerson;
import pt.ist.sirs.services.AdicionarAcessoDeEspecialidadeService;
import pt.ist.sirs.services.AdicionarEspecialidadeAMedicoService;
import pt.ist.sirs.services.AdminLoginService;
import pt.ist.sirs.services.AlterarPermissaoDefaultService;
import pt.ist.sirs.services.ChangePermissaoService;
import pt.ist.sirs.services.CreateEspecialidadeService;
import pt.ist.sirs.services.CreateEstabelecimentoService;
import pt.ist.sirs.services.CreateMedicoBanidoDeEspecialidadeService;
import pt.ist.sirs.services.CreateMedicoService;
import pt.ist.sirs.services.CreatePessoaService;
import pt.ist.sirs.services.CreateRegistoService;
import pt.ist.sirs.services.DevolverAcessoAMedicoService;
import pt.ist.sirs.services.GetEspecialidadesService;
import pt.ist.sirs.services.GetRegistosService;
import pt.ist.sirs.services.LoginService;
import pt.ist.sirs.services.ProibirAcessoAMedicoService;
import pt.ist.sirs.services.RegistosByEspecialidadeService;
import pt.ist.sirs.services.RegistosFromPacienteService;
import pt.ist.sirs.services.RegistosPropriosService;
import pt.ist.sirs.services.RemoveMedicoBanidoDeEspecialidadeService;
import pt.ist.sirs.services.RemoverAcessoDeEspecialidadeService;
import pt.ist.sirs.services.ToggleAdminService;
import pt.ist.sirs.services.ToogleMedicoUrgenciaService;
import pt.ist.sirs.services.dto.EspecialidadeDTO;
import pt.ist.sirs.services.dto.RegistoDTO;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia (70001), José Góis (79261)
 */
public class MedDBApp {

    /**
     * Main da aplicação
     * 
     * @param args Não utilizado
     * @throws IOException
     */
    public static void main(String[] args) {
        Bootstrap.init();

        System.out.println("######################## MED_DB_APP BEGIN ###########################");

        printMenu();

        System.out.println();
        System.out.println("########################  MED_DB_APP END ############################");
    }

    private static void printMedicoMenu() {
        System.out.println();
        System.out.println("1 - Criar Novo Registo");
        System.out.println("2 - Consultar Registos por Paciente");
        System.out.println("3 - Consultar Registos por Especialidade");
        System.out.println("0 - Logout");
        System.out.println();
        System.out.print("Seleccione a opcao pretendida: ");
        String opcao = System.console().readLine();

        Integer num;

        try {
            num = Integer.parseInt(opcao);
        } catch (Exception e) {
            num = -1;
        }

        switch (num) {
        case 0:
            LoggedPerson.getInstance().removeLoggedPerson();
            return;
        case 1:
            criarNovoRegisto();
            break;
        case 2:
            registosPorPaciente();

            break;
        case 3:
            registosPorEspecialidade();
            break;
        default:
            System.out.println("Opcao inválida!");
            break;
        }

    }

    private static void printAdminMenu() {
        while (true) {
            System.out.println();
            System.out.println(" 1 - Permitir acesso a registo");
            System.out.println(" 2 - Negar acesso a registo");
            System.out.println(" 3 - Associar medico a especialidade");
            System.out.println(" 4 - Negar acesso de medico a especialidade");
            System.out.println(" 5 - Devolver acesso de medico a especialidade");
            System.out.println(" 6 - Alterar estatuto de Urgencia");
            System.out.println(" 7 - Modificar permissao default do registo");
            System.out.println(" 8 - Registar Medico");
            System.out.println(" 9 - Registar Pessoa");
            System.out.println("10 - Registar Estabelecimento");
            System.out.println("11 - Registar Especialidade");
            System.out.println("12 - Adicionar politica de especialidade");
            System.out.println("13 - Remover politica de especialidade");
            System.out.println("14 - Alterar estatuto de admin");
            System.out.println("99 - Ajuda");

            System.out.println(" 0 - Logout");
            System.out.println();
            System.out.print("Seleccione a opcao pretendida: ");
            String opcao = System.console().readLine();

            Integer num;

            try {
                num = Integer.parseInt(opcao);
            } catch (Exception e) {
                num = -1;
            }

            switch (num) {
            case 1:
                reporAcessoARegisto();
                break;
            case 2:
                negarAcessoARegisto();
                break;
            case 3:
                permitirAcessoAEspecialidade();
                break;
            case 4:
                negarAcessoAEspecialidade();
                break;
            case 5:
                devolverAcessoAEspecialidade();
                break;
            case 6:
                toogleMedicoUrgencia();
                break;
            case 7:
                writeNewDefaultPermission();
                break;
            case 8:
                createMedico();
                break;
            case 9:
                createPessoa();
                break;
            case 10:
                createEstabelecimento();
                break;
            case 11:
                createEspecialidade();
                break;
            case 12:
                adicionarAcessoDeEspecialidade();
                break;
            case 13:
                removerAcessoDeEspecialidade();
                break;
            case 14:
                toggleAdmin();
                break;
            case 99:
                printAjudaPaciente();
                break;
            case 0:
                LoggedPerson.getInstance().removeLoggedPerson();
                return;
            default:
                System.out.println("Opcao inválida!");
                break;
            }
        }
    }

    // TODO: Alterar display de especialidades para menu proprio
    private static void adicionarAcessoDeEspecialidade() {
        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }

            System.out.print("Introduza o ID da especialidade acessora: ");
            Integer idAcessora;
            try {
                idAcessora = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idAcessora = -1;
            }
            System.out.print("Introduza o ID da especialidade acedida: ");
            Integer idAcedida;
            try {
                idAcedida = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idAcedida = -1;
            }
            AdicionarAcessoDeEspecialidadeService serv = new AdicionarAcessoDeEspecialidadeService(idAcessora, idAcedida);
            try {
                serv.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }
    }

    // TODO: Alterar display de especialidades para menu proprio
    private static void removerAcessoDeEspecialidade() {
        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }
            System.out.print("Introduza o ID da especialidade acessora: ");
            Integer idAcessora;
            try {
                idAcessora = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idAcessora = -1;
            }
            System.out.print("Introduza o ID da especialidade acedida: ");
            Integer idAcedida;
            try {
                idAcedida = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idAcedida = -1;
            }
            RemoverAcessoDeEspecialidadeService serv = new RemoverAcessoDeEspecialidadeService(idAcessora, idAcedida);
            try {
                serv.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }
    }

    private static void writeNewDefaultPermission() {
        System.out.print("Insira a nova permissao default: ");
        String newPerm = System.console().readLine();

        AlterarPermissaoDefaultService serv = new AlterarPermissaoDefaultService(newPerm);

        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void toogleMedicoUrgencia() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();
        ToogleMedicoUrgenciaService tmuServ = new ToogleMedicoUrgenciaService(usernameMedico);
        try {
            tmuServ.execute();
            System.out.println(usernameMedico + " alterado para " + tmuServ.getEstadoActual());
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void toggleAdmin() {
        System.out.print("Indique o username: ");
        String usernameMedico = System.console().readLine();
        ToggleAdminService serv = new ToggleAdminService(usernameMedico);
        try {
            serv.execute();
            System.out.println(usernameMedico + " alterado para " + serv.getEstadoActual());
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    // TODO: Alterar display de especialidades para menu proprio
    private static void permitirAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }
            System.out.print("Indique o id da Especialidade: ");

            Integer idEspecialidade;
            try {
                idEspecialidade = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idEspecialidade = -1;
            }
            AdicionarEspecialidadeAMedicoService aemServ =
                    new AdicionarEspecialidadeAMedicoService(usernameMedico, idEspecialidade);
            try {
                aemServ.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }

    }

    // TODO: Alterar display de especialidades para menu proprio
    private static void negarAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }
            System.out.print("Indique o id da Especialidade: ");

            Integer idEspecialidade;
            try {
                idEspecialidade = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idEspecialidade = -1;
            }

            CreateMedicoBanidoDeEspecialidadeService cmbeServ =
                    new CreateMedicoBanidoDeEspecialidadeService(usernameMedico, idEspecialidade);
            try {
                cmbeServ.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }
    }

    // TODO: Alterar display de especialidades para menu proprio
    private static void devolverAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }
            System.out.print("Indique o id da Especialidade: ");

            Integer idEspecialidade;
            try {
                idEspecialidade = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idEspecialidade = -1;
            }

            RemoveMedicoBanidoDeEspecialidadeService cmbeServ =
                    new RemoveMedicoBanidoDeEspecialidadeService(usernameMedico, idEspecialidade);
            try {
                cmbeServ.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }
    }

    // TODO: Alterar display de registos para menu proprio
    private static void negarAcessoARegisto() {
        GetRegistosService greServ = new GetRegistosService();
        ArrayList<RegistoDTO> registos = null;
        try {
            greServ.execute();
            registos = greServ.getRegistos();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (registos != null && registos.size() > 0) {

            for (RegistoDTO registoDTO : registos) {
                System.out.println("ID: " + registoDTO.getObjectID());
                System.out.println("  -P: " + registoDTO.getNomePaciente());
                System.out.println("  -M: " + registoDTO.getNomeMedico());
                System.out.println("  -E: " + registoDTO.getNomeEspecialidade());
                System.out.println("  -Est: " + registoDTO.getNomeEstabelecimento());
                System.out.println("  -C: " + registoDTO.getConteudo());
            }
            System.out.print("Introduza o ID do registo: ");
            Integer idRegisto = Integer.parseInt(System.console().readLine());
            System.out.print("Introduza o username do medico: ");
            String userMedico = System.console().readLine();

            ProibirAcessoAMedicoService serv = new ProibirAcessoAMedicoService(userMedico, idRegisto);

            try {
                serv.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Nao existem registos no sistema");
        }
    }

    // TODO: Alterar display de registos para menu proprio
    private static void reporAcessoARegisto() {
        GetRegistosService greServ = new GetRegistosService();
        ArrayList<RegistoDTO> registos = null;
        try {
            greServ.execute();
            registos = greServ.getRegistos();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (registos != null && registos.size() > 0) {

            for (RegistoDTO registoDTO : registos) {
                System.out.println("ID: " + registoDTO.getObjectID());
                System.out.println("  -P: " + registoDTO.getNomePaciente());
                System.out.println("  -M: " + registoDTO.getNomeMedico());
                System.out.println("  -E: " + registoDTO.getNomeEspecialidade());
                System.out.println("  -Est: " + registoDTO.getNomeEstabelecimento());
                System.out.println("  -C: " + registoDTO.getConteudo());
            }

            System.out.print("Introduza o ID do registo: ");
            Integer idRegisto;
            try {
                idRegisto = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                idRegisto = -1;
            }

            System.out.print("Introduza o username do medico: ");
            String userMedico = System.console().readLine();

            DevolverAcessoAMedicoService serv = new DevolverAcessoAMedicoService(userMedico, idRegisto);

            try {
                serv.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Nao existem registos no sistema");
        }
    }

    private static void criarNovoRegisto() {
        System.out.print("Introduza o username do paciente: ");
        String paciente = System.console().readLine();
        System.out.print("Introduza o id da especialidade: ");
        Integer especialidade;
        try {
            especialidade = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            especialidade = -1;
        }
        System.out.print("Introduza o id do estabelecimento: ");
        Integer estabelecimento;
        try {
            estabelecimento = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            estabelecimento = -1;
        }
        System.out.println();
        System.out.print("Conteudo: ");
        String conteudo = System.console().readLine();

        CreateRegistoService registoSrv = new CreateRegistoService(paciente, conteudo, especialidade, estabelecimento);
        try {
            registoSrv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void registosPorPaciente() {
        System.out.print("Introduza o username do paciente: ");
        String username = System.console().readLine();
        ArrayList<RegistoDTO> registos = null;

        RegistosFromPacienteService rfpServ = new RegistosFromPacienteService(username);
        try {
            rfpServ.execute();
            registos = rfpServ.getRegistos();

        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

        if (registos != null && registos.size() > 0) {
            for (RegistoDTO registo : registos) {
                System.out.println(registo.getObjectID() + "- Especialidade: " + registo.getNomeEspecialidade() + " - Medico: "
                        + registo.getNomeMedico());
                System.out.println("Conteudo: " + registo.getConteudo());
            }
        } else {
            System.out.println("Nao ha registos para mostrar");
        }

    }

    private static void registosProprios() {
        ArrayList<RegistoDTO> registos = null;
        RegistosPropriosService rfpServ = new RegistosPropriosService();
        try {
            rfpServ.execute();
            registos = rfpServ.getRegistos();

        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

        if (registos != null && registos.size() > 0) {
            for (RegistoDTO registo : registos) {
                System.out.println(registo.getObjectID() + "- Especialidade: " + registo.getNomeEspecialidade() + " - Medico: "
                        + registo.getNomeMedico());
                System.out.println("Conteudo: " + registo.getConteudo());
            }
        } else {
            System.out.println("Nao ha registos para mostrar");
        }
    }

    private static void registosPorEspecialidade() {
        GetEspecialidadesService gespServ = new GetEspecialidadesService();
        ArrayList<EspecialidadeDTO> especialidades = null;
        try {
            gespServ.execute();
            especialidades = gespServ.getEspecialidades();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
        if (especialidades != null && especialidades.size() > 0) {

            for (EspecialidadeDTO especialidade : especialidades) {
                System.out.println("ID: " + especialidade.getObjectId() + " - " + especialidade.getNome());
            }

            System.out.print("Introduza o id da especialidade: ");
            Integer especialidade;
            try {
                especialidade = Integer.parseInt(System.console().readLine());
            } catch (Exception e) {
                especialidade = -1;
            }
            RegistosByEspecialidadeService rbeServ = new RegistosByEspecialidadeService(especialidade);
            ArrayList<RegistoDTO> registos = null;
            try {
                rbeServ.execute();
                registos = rbeServ.getRegistos();

            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }

            if (registos != null && registos.size() > 0) {

                for (RegistoDTO registo : registos) {
                    System.out.println(registo.getObjectID() + "- Paciente: " + registo.getNomePaciente() + " - Medico: "
                            + registo.getNomeMedico());
                    System.out.println("Conteudo: " + registo.getConteudo());
                }
            } else {
                System.out.println("Nao ha registos para mostrar!");
            }
        } else {
            System.out.println("Não existem especialidades registadas!");
        }

    }

    private static void createPessoa() {
        String username;
        do {
            System.out.print("Introduza o username: ");
            username = System.console().readLine();
        } while (username.length() == 0);
        String password;

        while (true) {
            System.out.print("Introduza a password: ");
            password = new String(System.console().readPassword());
            System.out.print("Confirme a password: ");
            String passconf = new String(System.console().readPassword());

            if (password.equals(passconf)) {
                break;
            } else {
                System.out.println();
                System.out.println("Passwords nao iguais!");
            }
        }

        System.out.print("Introduza o nome: ");
        String nome = System.console().readLine();

        CreatePessoaService pessoaServ = new CreatePessoaService(username, password, nome);
        try {
            pessoaServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createMedico() {
        String username;
        do {
            System.out.print("Introduza o username: ");
            username = System.console().readLine();
        } while (username.length() == 0);
        String password;
        Boolean medicoDeUrgencia;

        while (true) {
            System.out.print("Introduza a password: ");
            password = new String(System.console().readPassword());
            System.out.print("Confirme a password: ");
            String passconf = new String(System.console().readPassword());

            if (password.equals(passconf)) {
                break;
            } else {
                System.out.println();
                System.out.println("Passwords nao iguais!");
            }
        }

        System.out.print("Introduza o nome: ");
        String nome = System.console().readLine();

        while (true) {
            System.out.print("Medico de urgencia (T/F): ");
            String mDU = System.console().readLine();
            if (mDU.equals("T") || mDU.equals("t")) {
                medicoDeUrgencia = true;
                break;
            } else if (mDU.equals("F") || mDU.equals("f")) {
                medicoDeUrgencia = false;
                break;
            } else {
                System.out.println("Opcao incorrecta!");
            }
        }

        CreateMedicoService serv = new CreateMedicoService(username, password, nome, medicoDeUrgencia);
        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printMenu() {
        while (true) {
            if (LoggedPerson.getInstance().getLoggedPerson() instanceof Medico) {
                printMedicoMenu();
            } else if (LoggedPerson.getInstance().getLoggedPerson() instanceof Pessoa) {
                printPacienteMenu();
            } else {
                System.out.println();
                System.out.println("1 - Login Med-DB");
                System.out.println("2 - Login Administracao");
                System.out.println("0 - Sair");
                System.out.println();
                System.out.print("Seleccione a opcao pretendida: ");
                String opcao = System.console().readLine();

                Integer num;

                try {
                    num = Integer.parseInt(opcao);
                } catch (Exception e) {
                    num = -1;
                }

                switch (num) {
                case 0:
                    LoggedPerson.getInstance().removeLoggedPerson();
                    return;
                case 1:
                    personLogin();
                    break;
                case 2:
                    adminLogin();
                    break;
                default:
                    System.out.println("Opcao inválida!");
                    break;
                }
            }
        }
    }

    private static void printPacienteMenu() {
        System.out.println();
        System.out.println("1 - Ver os meus registos");
        System.out.println("2 - Alterar permissao de registo");
        System.out.println("9 - Ajuda");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Seleccione a opcao pretendida: ");
        String opcao = System.console().readLine();

        Integer num;

        try {
            num = Integer.parseInt(opcao);
        } catch (Exception e) {
            num = -1;
        }

        switch (num) {
        case 0:
            LoggedPerson.getInstance().removeLoggedPerson();
            return;
        case 1:
            registosProprios();
            break;
        case 2:
            changePermReg();
            break;
        case 9:
            printAjudaPaciente();
            break;
        default:
            System.out.println("Opcao inválida!");
            break;
        }

    }

    private static void printAjudaPaciente() {
        System.out.println();
        System.out.println("Como definir permissoes:");
        System.out
                .println("O Med-DB tem um poderoso compilador de permissoes para que seja possivel, escrevendo, definir permissoes para o registo. As seguintes opcoes são validas:");
        System.out.println();
        System.out.println("mdesp - Permite o acesso aos medicos da especialidade do registo");
        System.out.println("mdp - Permite o acesso aos medicos do paciente do registo");
        System.out.println("mdu - Permite o acesso aos medicos de urgencia (RECOMENDADO)");
        System.out.println("mdest - Permite o acesso aos medicos do estabelecimento do registo");
        System.out.println("mdr - Permite o acesso ao medico do registo (RECOMENDADO)");
        System.out.println("pdr - Permite o acesso ao paciente do registo (RECOMENDADO)");
        System.out.println("ppde - Permite o acesso aos medicos de acordo com as politicas de especialidade");
        System.out.println("pp - Permite o acesso a todos os utilizadores");
        System.out.println();
        System.out.println("Existem ainda um conjunto de permissoes que implementam operadores logicos. Estas sao:");
        System.out.println();
        System.out.println("and - Operador logico e");
        System.out.println("or - Operador logico ou");
        System.out.println("not - Operador logico not");
        System.out.println();
        System.out.println("O seguinte exemplo mostra como escrever o conjunto de permissoes recomendadas:");
        System.out.println();
        System.out.println("or(mdu,mdr,pdr)");
    }

    private static void changePermReg() {
        System.out.println();
        System.out.print("Introduza o ID do registo: ");
        Integer idReg = Integer.parseInt(System.console().readLine());
        System.out.print("Introduza a permissao pretendida: ");
        String perm = System.console().readLine();

        ChangePermissaoService serv = new ChangePermissaoService(perm, idReg);
        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createEstabelecimento() {
        String nome;
        do {
            System.out.println();
            System.out.print("Introduza o nome do estabelecimento: ");
            nome = System.console().readLine();
        } while (nome.length() == 0);
        CreateEstabelecimentoService serv = new CreateEstabelecimentoService(nome);

        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createEspecialidade() {
        String nome;
        do {
            System.out.println();
            System.out.print("Introduza o nome da especialidade: ");
            nome = System.console().readLine();
        } while (nome.length() == 0);
        CreateEspecialidadeService serv = new CreateEspecialidadeService(nome);

        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void personLogin() {
        while (LoggedPerson.getInstance().getLoggedPerson() == null) {
            System.out.println();
            System.out.print("Introduza o seu username: ");
            String username = System.console().readLine();
            System.out.print("Introduza a sua password: ");
            String password = new String(System.console().readPassword());

            LoginService login = new LoginService(username, password);
            try {
                login.execute();
                System.out.println("Bem vindo " + login.getNome() + "!");
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private static void adminLogin() {
        System.out.println();
        System.out.print("Introduza o seu username: ");
        String username = System.console().readLine();
        System.out.print("Introduza a sua password: ");
        String password = new String(System.console().readPassword());

        AdminLoginService login = new AdminLoginService(username, password);
        try {
            login.execute();
            System.out.println("Bem vindo " + login.getNome() + "!");

            printAdminMenu();
            return;
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }
}
