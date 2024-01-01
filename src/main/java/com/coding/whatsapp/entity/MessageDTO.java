package com.coding.whatsapp.entity;

public class MessageDTO {
    private String chatId;
    private User fromUser;
    private String content;

    public String getChatId() {
        return chatId;
    }

    public User getFromUser() {
        return fromUser;
    }

    public String getContent() {
        return content;
    }

    public MessageDTO(String chatId, User fromUser, String content) {
        this.chatId = chatId;
        this.fromUser = fromUser;
        this.content = content;
    }
}
