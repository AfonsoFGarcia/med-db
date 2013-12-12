package pt.ist.sirs.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Afonso F. Garcia (70001)
 */
public class Seguranca {

    private static String fileFolder;

    public static void setFileName(String f) {
        fileFolder = f;
    }

    public static void generateSystemKey() {
        System.out.println("A gerar a chave assimetrica do sistema...");

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
            BufferedWriter out = new BufferedWriter(new FileWriter(local + "key.scr"));
            out.write(key);
            out.close();
        } catch (Exception e) {
            System.out.println("Nao foi possivel guardar a chave!");
            System.exit(-1);
        }
    }

    public static SecretKey getKeyFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileFolder + "key.scr"));
        String data = in.readLine();
        in.close();
        byte[] encoded = Base64.decodeBase64(data);
        return new SecretKeySpec(encoded, "AES");
    }

    public static String encrypt(String encrypt) {
        Cipher c;
        SecretKey sysKey;
        byte[] base64Conteudo = Base64.encodeBase64(encrypt.getBytes());
        byte[] encrypted;

        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            sysKey = Seguranca.getKeyFromFile();
            c.init(Cipher.ENCRYPT_MODE, sysKey, ivspec);
            encrypted = new byte[c.getOutputSize(base64Conteudo.length)];
            int enc_len = c.update(base64Conteudo, 0, base64Conteudo.length, encrypted, 0);
            enc_len += c.doFinal(encrypted, enc_len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return Base64.encodeBase64String(encrypted);
    }

    public static String decrypt(String enc) {
        Cipher c;
        SecretKey sysKey;
        byte[] encrypted = Base64.decodeBase64(enc);
        byte[] base64Conteudo;

        try {
            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            sysKey = Seguranca.getKeyFromFile();
            c.init(Cipher.DECRYPT_MODE, sysKey, ivspec);
            base64Conteudo = new byte[c.getOutputSize(encrypted.length)];
            int dec_len = c.update(encrypted, 0, encrypted.length, base64Conteudo, 0);
            dec_len += c.doFinal(base64Conteudo, dec_len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return new String(Base64.decodeBase64(base64Conteudo));
    }
}
