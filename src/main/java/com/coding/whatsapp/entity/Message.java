package com.coding.whatsapp.entity;

import com.coding.whatsapp.util.Util;

public class Message {
    private String messageId = Util.getId();
    private String chatId;
    private User fromUser;
    private long timestamp = System.currentTimeMillis();

    private String content;

    public Message(String chatId, User fromUser, String content) {
        this.chatId = chatId;
        this.fromUser = fromUser;
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getChatId() {
        return chatId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }
}
