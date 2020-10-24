package com.ambuj.konnect.security;

public interface EncryptionService {
    String encrypt(String plainText, String key);
}
