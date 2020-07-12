package com.example.messagingstompwebsocket.model;

import lombok.Data;

@Data
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String receiver;

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
}
