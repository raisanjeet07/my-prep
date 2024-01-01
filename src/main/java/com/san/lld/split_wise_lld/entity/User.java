package com.san.lld.split_wise_lld.entity;

import java.util.Objects;

public class User {
    private final String id;
    private final String mobile;
    private final String name;
    private final String eMail;

    public User(String id, String mobile, String name, String eMail) {
        this.id = id;
        this.mobile = mobile;
        this.name = name;
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public String geteMail() {
        return eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && mobile.equals(user.mobile) && Objects.equals(name, user.name) && Objects.equals(eMail, user.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mobile, name, eMail);
    }
}
