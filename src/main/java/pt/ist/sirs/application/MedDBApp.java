package pt.ist.sirs.application;

import java.io.IOException;
import java.util.ArrayList;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.AdicionarAcessoDeEspecialidadeService;
import pt.ist.sirs.services.AdicionarEspecialidadeAMedicoService;
import pt.ist.sirs.services.AdminLoginService;
import pt.ist.sirs.services.AlterarPermissaoDefaultService;
import pt.ist.sirs.services.AssociarMedicoAEstabelecimentoService;
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
import pt.ist.sirs.services.RemoverMedicoDeEstabelecimentoService;
import pt.ist.sirs.services.ToggleAdminService;
import pt.ist.sirs.services.ToogleMedicoUrgenciaService;
import pt.ist.sirs.services.dto.EspecialidadeDTO;
import pt.ist.sirs.services.dto.RegistoDTO;
import pt.ist.sirs.utils.LoggedPerson;
import pt.ist.sirs.utils.Seguranca;
import edu.vt.middleware.password.AlphabeticalSequenceRule;
import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.NumericalSequenceRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.QwertySequenceRule;
import edu.vt.middleware.password.RepeatCharacterRegexRule;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.WhitespaceRule;

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

        if (args.length < 1) {
            System.out.println("Nao foi especificada a localizacao da chave secreta!");
            System.exit(-1);
        }

        Seguranca.setFileName(args[0]);

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
            System.out.println(" 1 - Consultar todos os registos");
            System.out.println(" 2 - Permitir acesso a registo");
            System.out.println(" 3 - Negar acesso a registo");
            System.out.println(" 4 - Consultar todas as especialidades");
            System.out.println(" 5 - Associar medico a especialidade");
            System.out.println(" 6 - Negar acesso de medico a especialidade");
            System.out.println(" 7 - Devolver acesso de medico a especialidade");
            System.out.println(" 8 - Associar medico a estabelecimento");
            System.out.println(" 9 - Remover medico de estabelecimento");
            System.out.println("10 - Alterar estatuto de Urgencia");
            System.out.println("11 - Modificar permissao default do registo");
            System.out.println("12 - Registar Medico");
            System.out.println("13 - Registar Pessoa");
            System.out.println("14 - Registar Estabelecimento");
            System.out.println("15 - Registar Especialidade");
            System.out.println("16 - Adicionar politica de especialidade");
            System.out.println("17 - Remover politica de especialidade");
            System.out.println("18 - Alterar estatuto de admin");
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
                consultarTodosOsRegistos();
                break;
            case 2:
                reporAcessoARegisto();
                break;
            case 3:
                negarAcessoARegisto();
                break;
            case 4:
                consultarTodasAsEspecialidades();
                break;
            case 5:
                permitirAcessoAEspecialidade();
                break;
            case 6:
                negarAcessoAEspecialidade();
                break;
            case 7:
                devolverAcessoAEspecialidade();
                break;
            case 8:
                associarMedicoAEstabelecimento();
                break;
            case 9:
                removerMedicoDeEstabelecimento();
                break;
            case 10:
                toogleMedicoUrgencia();
                break;
            case 11:
                writeNewDefaultPermission();
                break;
            case 12:
                createMedico();
                break;
            case 13:
                createPessoa();
                break;
            case 14:
                createEstabelecimento();
                break;
            case 15:
                createEspecialidade();
                break;
            case 16:
                adicionarAcessoDeEspecialidade();
                break;
            case 17:
                removerAcessoDeEspecialidade();
                break;
            case 18:
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

    private static void removerMedicoDeEstabelecimento() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id do Estabelecimento: ");

        Integer idEstabelecimento;
        try {
            idEstabelecimento = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }
        RemoverMedicoDeEstabelecimentoService rmdeServ =
                new RemoverMedicoDeEstabelecimentoService(usernameMedico, idEstabelecimento);
        try {
            rmdeServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void associarMedicoAEstabelecimento() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id do Estabelecimento: ");

        Integer idEstabelecimento;
        try {
            idEstabelecimento = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }
        AssociarMedicoAEstabelecimentoService amaeServ =
                new AssociarMedicoAEstabelecimentoService(usernameMedico, idEstabelecimento);
        try {
            amaeServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void consultarTodasAsEspecialidades() {
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
        } else {
            System.out.println("Não existem especialidades registadas!");
        }
    }

    private static void consultarTodosOsRegistos() {
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
        } else {
            System.out.println("Nao existem registos no sistema");
        }

    }

    private static void adicionarAcessoDeEspecialidade() {
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

    }

    private static void removerAcessoDeEspecialidade() {

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

    private static void permitirAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id da Especialidade: ");

        Integer idEspecialidade;
        try {
            idEspecialidade = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }
        AdicionarEspecialidadeAMedicoService aemServ = new AdicionarEspecialidadeAMedicoService(usernameMedico, idEspecialidade);
        try {
            aemServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void negarAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id da Especialidade: ");

        Integer idEspecialidade;
        try {
            idEspecialidade = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }

        CreateMedicoBanidoDeEspecialidadeService cmbeServ =
                new CreateMedicoBanidoDeEspecialidadeService(usernameMedico, idEspecialidade);
        try {
            cmbeServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void devolverAcessoAEspecialidade() {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id da Especialidade: ");

        Integer idEspecialidade;
        try {
            idEspecialidade = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }

        RemoveMedicoBanidoDeEspecialidadeService cmbeServ =
                new RemoveMedicoBanidoDeEspecialidadeService(usernameMedico, idEspecialidade);
        try {
            cmbeServ.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void negarAcessoARegisto() {

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

    }

    private static void reporAcessoARegisto() {

        System.out.print("Introduza o ID do registo: ");
        Integer idRegisto;
        try {
            idRegisto = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }

        System.out.print("Introduza o username do medico: ");
        String userMedico = System.console().readLine();

        DevolverAcessoAMedicoService serv = new DevolverAcessoAMedicoService(userMedico, idRegisto);

        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
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
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
        }
        System.out.print("Introduza o id do estabelecimento: ");
        Integer estabelecimento;
        try {
            estabelecimento = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
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

        System.out.print("Introduza o id da especialidade: ");
        Integer especialidade;
        try {
            especialidade = Integer.parseInt(System.console().readLine());
        } catch (Exception e) {
            System.out.println("O valor introduzido nao e um inteiro!");
            return;
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
            if (checkPasswordStenght(password)) {
                System.out.print("Confirme a password: ");
                String passconf = new String(System.console().readPassword());

                if (password.equals(passconf)) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Passwords nao iguais!");
                }
            } else {
                System.out.println();
                System.out.println("A password não cumpre os requisitos!");
                printRequisitosPassword();
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
            if (checkPasswordStenght(password)) {
                System.out.print("Confirme a password: ");
                String passconf = new String(System.console().readPassword());

                if (password.equals(passconf)) {
                    break;
                } else {
                    System.out.println();
                    System.out.println("Passwords nao iguais!");
                }
            } else {
                System.out.println();
                System.out.println("A password não cumpre os requisitos!");
                printRequisitosPassword();
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

    private static void printRequisitosPassword() {
        System.out.println("A password deve conter:");
        System.out.println(" - Entre 6 e 18 carateres");
        System.out.println(" - Pelo menos 1 digito");
        System.out.println(" - Pelo menos 1 carater nao alfanumerico");
        System.out.println("A password não pode conter:");
        System.out.println(" - Espacos em branco");
        System.out.println(" - Sequencias de digitos ou alfanumericas");
        System.out.println(" - 3 carateres seguidos repetidos");
        System.out.println(" - sequencias do tipo \"qwerty\"");
    }

    private static boolean checkPasswordStenght(String password) {
        LengthRule lenghtR = new LengthRule(6, 18);
        WhitespaceRule whiteSpaceR = new WhitespaceRule();
        CharacterCharacteristicsRule characterCR = new CharacterCharacteristicsRule();
        characterCR.getRules().add(new DigitCharacterRule(1));
        characterCR.getRules().add(new NonAlphanumericCharacterRule(1));
        AlphabeticalSequenceRule alphabeticalSeqR = new AlphabeticalSequenceRule();
        NumericalSequenceRule numericalSeqR = new NumericalSequenceRule();
        QwertySequenceRule qweertySeqR = new QwertySequenceRule();
        RepeatCharacterRegexRule repeatCharacterR = new RepeatCharacterRegexRule(3);

        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(lenghtR);
        rules.add(whiteSpaceR);
        rules.add(characterCR);
        rules.add(alphabeticalSeqR);
        rules.add(numericalSeqR);
        rules.add(qweertySeqR);
        rules.add(repeatCharacterR);

        PasswordValidator passwValidator = new PasswordValidator(rules);
        PasswordData passwordData = new PasswordData(new Password(password));
        RuleResult result = passwValidator.validate(passwordData);

        if (result.isValid()) {
            return true;
        } else {
            return false;

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
