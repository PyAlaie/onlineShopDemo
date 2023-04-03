package com.example.onlineshopdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {
    protected String username;
    protected String password;
    protected String email;

    private String role = null;
    private ArrayList<Notification> notifications = new ArrayList<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(role, user.role);
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public void clearNotifications(){
        notifications.clear();
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void removeNotification(Notification notification){
        notifications.removeIf(n -> n.equals(notification));
    }
}
