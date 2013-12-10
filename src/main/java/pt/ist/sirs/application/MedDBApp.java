package pt.ist.sirs.application;

import java.io.IOException;
import java.util.ArrayList;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.Medico;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.login.LoggedPerson;
import pt.ist.sirs.services.AdminLoginService;
import pt.ist.sirs.services.ChangePermissaoService;
import pt.ist.sirs.services.CreateEspecialidadeService;
import pt.ist.sirs.services.CreateEstabelecimentoService;
import pt.ist.sirs.services.CreateMedicoBanidoDeEspecialidadeService;
import pt.ist.sirs.services.CreateMedicoService;
import pt.ist.sirs.services.CreatePessoaService;
import pt.ist.sirs.services.CreateRegistoService;
import pt.ist.sirs.services.DevolverAcessoAMedicoService;
import pt.ist.sirs.services.LoginService;
import pt.ist.sirs.services.ProibirAcessoAMedicoService;
import pt.ist.sirs.services.RegistosByEspecialidadeService;
import pt.ist.sirs.services.RegistosFromPacienteService;
import pt.ist.sirs.services.dto.RegistoDTO;

/**
 * Classe <b>MedDBApp</b>. <br>
 * <br>
 * Implementa a interface de utilizador do Med-DB.
 * 
 * @author Afonso F. Garcia (70001)
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
            num = 69;
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
        System.out.println();
        System.out.println("1 - Permitir acesso a registo");
        System.out.println("2 - Negar acesso a registo");
        System.out.println("3 - Permitir acesso a especialidade");
        System.out.println("4 - Negar acesso a especialidade");

        System.out.println("0 - Logout");
        System.out.println();
        System.out.print("Seleccione a opcao pretendida: ");
        String opcao = System.console().readLine();

        Integer num;

        try {
            num = Integer.parseInt(opcao);
        } catch (Exception e) {
            num = 69;
        }

        switch (num) {
        case 1:
            reporAcessoARegisto();
            break;
        case 2:
            negarAcessoARegisto();
            break;
        case 3:
            acessoMedicoAEspecialidade(3);
            break;
        case 4:
            acessoMedicoAEspecialidade(4);
            break;
        case 5:
            System.out.print("Nao implementado");
            break;
        case 6:
            System.out.print("Nao implementado");
            break;
        default:
            break;
        }

        LoggedPerson.getInstance().removeLoggedPerson();
    }

//TODO: Modificar função!!
    private static void acessoMedicoAEspecialidade(Integer num) {
        System.out.print("Indique o username do Medico: ");
        String usernameMedico = System.console().readLine();

        System.out.print("Indique o id da Especialidade: ");
        Integer idEspecialidade = Integer.parseInt(System.console().readLine());

        //TODO:
        switch (num) {
        case 3://Permitir
               //TODO:Adicionar especialidade ao medico

            break;
        case 4://Negar
               //Adicioar elemento MedicoBanidoDeEspecialidade
            CreateMedicoBanidoDeEspecialidadeService cmbeServ =
                    new CreateMedicoBanidoDeEspecialidadeService(usernameMedico, idEspecialidade);
            try {
                cmbeServ.execute();
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
            break;
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
        Integer idRegisto = Integer.parseInt(System.console().readLine());
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
        Integer especialidade = Integer.parseInt(System.console().readLine());
        System.out.print("Introduza o id do estabelecimento: ");
        Integer estabelecimento = Integer.parseInt(System.console().readLine());
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

    private static void registosPorEspecialidade() {
        System.out.print("Introduza o id da especialidade: ");
        Integer especialidade = Integer.parseInt(System.console().readLine());
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
            System.out.println("Nao ha registos para mostrar");
        }

    }

    private static void createPessoa() {

        System.out.print("Introduza o username: ");
        String username = System.console().readLine();
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
        System.out.print("Introduza o username: ");
        String username = System.console().readLine();
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
                System.out.println("2 - Registar Medico");
                System.out.println("3 - Registar Pessoa");
                System.out.println("4 - Registar Estabelecimento");
                System.out.println("5 - Registar Especialidade");
                System.out.println("6 - Login Administracao");
                System.out.println("0 - Sair");
                System.out.println();
                System.out.print("Seleccione a opcao pretendida: ");
                String opcao = System.console().readLine();

                Integer num;

                try {
                    num = Integer.parseInt(opcao);
                } catch (Exception e) {
                    num = 69;
                }

                switch (num) {
                case 0:
                    LoggedPerson.getInstance().removeLoggedPerson();
                    return;
                case 1:
                    personLogin();
                    break;
                case 2:
                    createMedico();
                    break;
                case 3:
                    createPessoa();
                    break;
                case 4:
                    createEstabelecimento();
                    break;
                case 5:
                    createEspecialidade();
                    break;
                case 6:
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
        System.out.println("1 - Alterar permissao de registo");
        System.out.println("9 - Ajuda");
        System.out.println("0 - Sair");
        System.out.println();
        System.out.print("Seleccione a opcao pretendida: ");
        String opcao = System.console().readLine();

        Integer num;

        try {
            num = Integer.parseInt(opcao);
        } catch (Exception e) {
            num = 69;
        }

        switch (num) {
        case 0:
            LoggedPerson.getInstance().removeLoggedPerson();
            return;
        case 1:
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
        System.out.println();
        System.out.print("Introduza o nome do estabelecimento: ");
        String nome = System.console().readLine();

        CreateEstabelecimentoService serv = new CreateEstabelecimentoService(nome);

        try {
            serv.execute();
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void createEspecialidade() {
        System.out.println();
        System.out.print("Introduza o nome da especialidade: ");
        String nome = System.console().readLine();

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
