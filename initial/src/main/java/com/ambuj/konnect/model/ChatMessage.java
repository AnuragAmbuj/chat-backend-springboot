package com.ambuj.konnect.model;

import com.ambuj.konnect.model.enums.MessageType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String receiver;



    @Override
    public String toString(){
        return type.getMessageString();
    }
}
