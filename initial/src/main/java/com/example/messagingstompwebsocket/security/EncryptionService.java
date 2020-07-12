package com.example.messagingstompwebsocket.security;

public interface EncryptionService {
    String encrypt(String plainText, String key);
}
