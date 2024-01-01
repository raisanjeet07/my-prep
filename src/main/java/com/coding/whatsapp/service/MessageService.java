package com.coding.whatsapp.service;

import com.coding.whatsapp.entity.Message;
import com.coding.whatsapp.entity.MessageDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageService {
    private final Map<String, List<Message>> chatMessageMap = new HashMap<>();

    public List<Message> getMessagesByChatId(String chatId){
        return chatMessageMap.get(chatId);
    }

    public void addMessage(MessageDTO message){
        Message msg = new Message(message.getChatId(), message.getFromUser(), message.getContent());
        chatMessageMap.putIfAbsent(msg.getChatId(), new ArrayList<>());
        chatMessageMap.get(msg.getChatId()).add(msg);
    }
}
