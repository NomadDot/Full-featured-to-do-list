package com.example.javaprojectfophone.pojo;

import java.util.Objects;

public class User {
    private long id;
    private String imageUrl;
    private String name;
    private String nick;

    public User(long id, String imageUrl, String name, String nick) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.nick = nick;
    }

    public long getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getNick() {
        return nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(imageUrl, user.imageUrl) &&
                name.equals(user.name) &&
                nick.equals(user.nick);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageUrl, name, nick);
    }

}
