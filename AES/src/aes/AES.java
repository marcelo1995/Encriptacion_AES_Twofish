package aes;

import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Generamos una clave de 128 bits adecuada para AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        // Alternativamente, una clave que queramos que tenga al menos 16 bytes
        // y nos quedamos con los bytes 0 a 15
        //String clave="123456789";
        String clave = "xxxxxxxxxxxxxxxx";//clave >= 16 caracteres
        System.out.println(clave.length());

        //System.out.println(clave.getBytes());
        key = new SecretKeySpec(clave.getBytes(), 0, 16, "AES");
        // Texto a encriptar
        System.out.println("Texto a en criptar: ");
        String texto = sc.nextLine();
        System.out.println("-------------------------------------------------");
        // Se obtiene un cifrador AES
        //Electronic Codebook (ECB)
        //ECB=Modos de operación para encriptación de bloques.
        //PKCS5Padding  = mecanismo de relleno
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // Se inicializa para encriptacion y se encripta el texto,
        // que debemos pasar como bytes.
        aes.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
        byte[] encriptado = aes.doFinal(texto.getBytes());
        // Se escribe byte a byte en hexadecimal el texto
        // encriptado para ver su pinta.
        System.out.println("Encriptado: ");
        for (byte b : encriptado) {
            System.out.print(Integer.toHexString(0xFF & b));
        }
        System.out.println("\n---------------------------------------------------");
        // Se iniciliza el cifrador para desencriptar, con la
        // misma clave y se desencripta
        aes.init(Cipher.DECRYPT_MODE, (java.security.Key) key);
        byte[] desencriptado = aes.doFinal(encriptado);
        // Texto obtenido, igual al original.
        System.out.println("Desencriptado: ");
        System.out.println(new String(desencriptado));
    }
}
