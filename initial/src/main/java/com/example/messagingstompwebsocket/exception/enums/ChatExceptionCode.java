package com.example.messagingstompwebsocket.exception.enums;

import lombok.Getter;

@Getter
public enum ChatExceptionCode {

    DECRYPTION_ERROR("CHAT-DECRYPT-001","We are facing some issues in decryption. We are looking at it. Please be patient!"),
    ENCRYPTION_ERROR("CHAT-DECRYPT-002","We are facing some issues in encryption. We are looking at it. Please be patient!");

    String errorCode;
    String errorMessage;

    ChatExceptionCode(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
