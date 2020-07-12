package com.example.messagingstompwebsocket.security.impl;

import com.example.messagingstompwebsocket.exception.ChatException;
import com.example.messagingstompwebsocket.exception.enums.ChatExceptionCode;
import com.example.messagingstompwebsocket.security.DecryptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Service
public class AES256DecryptionService implements DecryptionService {

    @Value("${chat.aes.secret}")
    private String secretKey;

    @Value("${chat.aes.secret.salt}")
    private String salt;

    @Override
    public String decrypt(String cipherText) {

        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)));
        }catch (NoSuchAlgorithmException e) {
            throw new ChatException(ChatExceptionCode.DECRYPTION_ERROR.getErrorCode(), ChatExceptionCode.DECRYPTION_ERROR.getErrorMessage(), e);
        } catch (InvalidKeySpecException e) {
            throw new ChatException(ChatExceptionCode.DECRYPTION_ERROR.getErrorCode(), ChatExceptionCode.DECRYPTION_ERROR.getErrorMessage(), e);
        }catch (Exception e){
            throw new ChatException(ChatExceptionCode.DECRYPTION_ERROR.getErrorCode(), ChatExceptionCode.DECRYPTION_ERROR.getErrorMessage(), e);
        }
    }
}
