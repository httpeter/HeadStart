package case1.nl.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class AESEncryptor implements Serializable {

    private final Cipher encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
    private final Cipher decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");



    public AESEncryptor() throws Exception {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        String sessionKey = externalContext.getInitParameter("sixteenBitEncryptionKey");
        String iv = externalContext.getInitParameter("sixteenBitEncryptionSalt");

        byte[] keyBytes;
        byte[] vectorBytes;

        keyBytes = sessionKey.getBytes();

        vectorBytes = iv.getBytes();

        encryptor.init(Cipher.ENCRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                new IvParameterSpec(vectorBytes));

        decryptor.init(Cipher.DECRYPT_MODE,
                new SecretKeySpec(keyBytes, "AES"),
                encryptor.getParameters());
    }



    public String encrypt(String plainText) throws UnsupportedEncodingException,
            IllegalBlockSizeException,
            BadPaddingException {
        // get bytes from string, encrypt, encode
        byte[] utf8bytes = plainText.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = encryptor.doFinal(utf8bytes);
        return Base64.getUrlEncoder().encodeToString(ciphertext);
    }



    public String decrypt(String cipherText) throws IOException,
            IllegalBlockSizeException,
            BadPaddingException {
        // decode, decrypt, use bytes to create string
        byte[] encryptedBytes = Base64.getUrlDecoder()
                .decode(cipherText.getBytes(StandardCharsets.UTF_8));
        byte[] plaintext = decryptor.doFinal(encryptedBytes);
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}
