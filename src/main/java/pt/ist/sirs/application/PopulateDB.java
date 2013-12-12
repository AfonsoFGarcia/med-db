package pt.ist.sirs.application;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import jvstm.Atomic;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import pt.ist.sirs.Bootstrap;
import pt.ist.sirs.domain.Pessoa;
import pt.ist.sirs.exceptions.MedDBException;
import pt.ist.sirs.services.AdminLoginService;
import pt.ist.sirs.services.CreateEspecialidadeService;
import pt.ist.sirs.services.CreateEstabelecimentoService;
import pt.ist.sirs.services.CreateMedicoService;
import pt.ist.sirs.services.CreatePessoaService;
import pt.ist.sirs.utils.LoggedPerson;

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

        createRoot();

        AdminLoginService login = new AdminLoginService("root", "root");
        login.execute();

        //Objectos a adicionar a DB no inicio
        CreateMedicoService medico1 = new CreateMedicoService("josel", "1234", "Jose Lagarto", false);
        medico1.execute();

        CreatePessoaService pessoa1 = new CreatePessoaService("bertor", "1234", "Alberto Ramalho");
        pessoa1.execute();

        CreateEspecialidadeService espec1 = new CreateEspecialidadeService("Psicologia");
        espec1.execute();

        CreateEstabelecimentoService estab1 = new CreateEstabelecimentoService("Hospital Julio de Matos");
        estab1.execute();

        LoggedPerson.getInstance().removeLoggedPerson();
    }

    @Atomic
    private static void createRoot() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Root");
        pessoa.setUsername("root");

        SecureRandom rand = new SecureRandom(pessoa.getObjectId().toString().getBytes());
        byte[] saltBytes = new byte[32];
        rand.nextBytes(saltBytes);
        String salt = new String(saltBytes);
        String saltedPass = new String(DigestUtils.sha1("root" + salt));

        pessoa.setPassword(saltedPass);
        pessoa.setSalt(salt);
        pessoa.setAdmin(true);
    }

    private static void generateSystemKey() {
        System.out.println("A gerar a chave assimetrica do sistema");

        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e1) {
            System.out.println("Nao foi possivel gerar a chave!");
            System.exit(-1);
        }
        keyGen.init(new SecureRandom());
        SecretKey systemKey = keyGen.generateKey();
        byte[] key = Base64.encodeBase64(systemKey.getEncoded());

        System.out.print("Introduza o local onde armazenar a chave: ");
        String local = System.console().readLine();

        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(local));
            out.write(key);
            out.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel guardar a chave!");
            System.exit(-1);
        }
    }
}