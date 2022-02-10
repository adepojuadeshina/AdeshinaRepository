package com.etz.comparedoc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Component
public class EncryptionUtils {
    //@Value("${admin.encrypt.key}")
    private String Key = "7576656d44696e67706c6174666f8880";

   //    @Value("${admin.encrypt.iv}")
    private String iv= "75746c99746e6588";

    public EncryptionUtils() {

    }
    public String encrypt(String password) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, ShortBufferException, UnsupportedEncodingException {

        byte[] input = password.getBytes("utf-8");

        SecretKeySpec skc = new SecretKeySpec(Key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skc, new IvParameterSpec(iv.getBytes("utf-8")));
        byte[] cipherText = cipher.doFinal(input);
        return Base64.getEncoder()
                .encodeToString(cipherText);
    }

    public String decrypt(String encryptedMessage) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException, ShortBufferException, UnsupportedEncodingException {


        SecretKeySpec skc = new SecretKeySpec(Key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skc, new IvParameterSpec(iv.getBytes("utf-8")));
        byte[] plainText = cipher.doFinal(Base64.getDecoder()
                .decode(encryptedMessage.getBytes("utf-8")));
        return new String(plainText);
    }

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, ShortBufferException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        EncryptionUtils encryptionUtils = new EncryptionUtils();
        System.out.println("Encryption of adeshina123 is === "+encryptionUtils.encrypt("adeshina123"));
    }
}
