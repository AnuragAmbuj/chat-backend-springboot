package com.ambuj.konnect.security.impl;

import com.ambuj.konnect.exception.ChatException;
import com.ambuj.konnect.exception.enums.ChatExceptionCode;
import com.ambuj.konnect.security.EncryptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class AES256EncryptionService implements EncryptionService {

    @Value("${chat.aes.secret}")
    private String secretKey;

    @Value("${chat.aes.secret.salt}")
    private String salt;


    @Override
    public String encrypt(String plainText, String key) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            final IvParameterSpec ivspec = new IvParameterSpec(iv);
            final SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            final KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            final SecretKey tmp = factory.generateSecret(spec);
            final SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new ChatException(ChatExceptionCode.ENCRYPTION_ERROR.getErrorCode(), ChatExceptionCode.ENCRYPTION_ERROR.getErrorMessage(), e);
        }
    }
}
