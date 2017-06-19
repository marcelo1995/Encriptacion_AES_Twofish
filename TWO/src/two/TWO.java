/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package two;

import javax.crypto.spec.*;
import java.security.*;
import javax.crypto.*;
import org.bouncycastle.util.encoders.Hex;

/**
 *
 * @author Usuario-1
 */
public class TWO {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        String mensaje = "Este codigo deberia estar encriptado!";
        System.out.println("Encriptando...");
        String key = "password";
        SecureRandom sr = new SecureRandom(key.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance("Twofish");//api caida o no actualizada
        kg.init(sr);
        SecretKey sk = kg.generateKey();
        Cipher cipher = Cipher.getInstance("twofish");
        
        cipher.init(Cipher.ENCRYPT_MODE, sk);
        byte[] encrypted = cipher.doFinal(mensaje.getBytes());
        System.out.println("Desemcriptado...");
        SecureRandom sr1 = new SecureRandom(key.getBytes());
        KeyGenerator kg1 = KeyGenerator.getInstance("twofish");
        kg1.init(sr1);
        SecretKey sk1 = kg1.generateKey();
        Cipher cipher1 = Cipher.getInstance("twofish");
        cipher1.init(Cipher.DECRYPT_MODE, sk1);
        byte[] decrypted = cipher1.doFinal(encrypted);
        System.out.println("Decrypted text: " + new String(decrypted));
    }

}
