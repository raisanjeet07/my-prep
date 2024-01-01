package com.coding.whatsapp.entity;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GroupMember {
    private User user;
    private boolean isAdmin;
    private boolean readOnly;
    private long addedOn;
    private ChatGroup group;
}
