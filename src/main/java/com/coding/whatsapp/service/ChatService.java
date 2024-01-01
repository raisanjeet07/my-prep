package com.coding.whatsapp.service;

import com.coding.whatsapp.entity.Chat;

import java.util.HashMap;
import java.util.Map;

public class ChatService {
    Map<String, Chat> chatMap = new HashMap<>();

    public Chat getChatById(String chatId){
        return chatMap.get(chatId);
    }

}
