package pt.ist.sirs.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Seguranca {

    public static void generateSystemKey() {
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
        String key = new String(Base64.encodeBase64(systemKey.getEncoded()));
        System.out.print("Introduza o local onde armazenar a chave: ");
        String local = System.console().readLine();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(local));
            out.write(key);
            out.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel guardar a chave!");
            System.exit(-1);
        }
    }

    public static SecretKey getKeyFromFile(String file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        String data = in.readLine();
        in.close();
        byte[] encoded = Base64.decodeBase64(data);
        return new SecretKeySpec(encoded, "AES");
    }
}
