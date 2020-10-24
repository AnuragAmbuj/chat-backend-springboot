package com.ambuj.konnect.model.enums;

public enum MessageType {
    CHAT("Chat"),
    JOIN("Join"),
    WRITE_RECEIPT("Write"),
    READ_RECEIPT("Read"),
    LEAVE("Leave");

    private String value;

    MessageType(String value){
        this.value = value;
    }

    public String getMessageString(){
        return value;
    }
}