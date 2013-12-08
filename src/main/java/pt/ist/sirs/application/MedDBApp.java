package pt.ist.sirs.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.exceptions.MedDBException;
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
    public static void main(String[] args) throws IOException {
        Bootstrap.init();

        System.out.println("######################## MED_DB_APP BEGIN ###########################");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Introduza o seu username: ");
        String username = br.readLine();

        LoginService login = new LoginService(username, "");
        try {
            login.execute();
            System.out.println("Bem vindo " + username + "!");
        } catch (MedDBException e) {
            System.out.println(e.getMessage());
        }

        System.out.println();
        System.out.println("########################  MED_DB_APP END ############################");
    }
}
