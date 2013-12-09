package pt.ist.sirs.application;

import java.io.IOException;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.login.LoggedPerson;
import pt.ist.sirs.services.CreateMedicoService;
import pt.ist.sirs.services.CreatePessoaService;
import pt.ist.sirs.services.LoginService;

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

    private static void createPessoa() {

        System.out.print("Introduza o nome: ");
        String nome = System.console().readLine();
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

        CreatePessoaService pessoaServ = new CreatePessoaService(nome, password);
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
            System.out.println();
            System.out.println("1 - Login Med-DB");
            System.out.println("2 - Registar Medico");
            System.out.println("3 - Registar Pessoa");
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
                LoggedPerson.removeLoggedPerson();
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
            default:
                System.out.println("Opcao inválida!");
                break;
            }
        }
    }

    private static void personLogin() {
        while (LoggedPerson.getLoggedPerson() == null) {
            System.out.println();
            System.out.print("Introduza o seu username: ");
            String username = System.console().readLine();
            System.out.print("Introduza a sua password: ");
            String password = new String(System.console().readPassword());

            LoginService login = new LoginService(username, password);
            try {
                login.execute();
                System.out.println("Bem vindo " + username + "!");
            } catch (MedDBException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
