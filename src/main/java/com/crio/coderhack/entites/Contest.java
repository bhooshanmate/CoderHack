package com.crio.coderhack.entites;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Document(collection = "contests")
public class Contest {
    private static Contest instance;
    private List<User> registeredUsers;

    private Contest() {
        registeredUsers = new ArrayList<>();
    }
    public static Contest getInstance() {
        if (instance == null) {
            instance = new Contest();
        }
        return instance;
    }

    public List<User> getRegisteredUsers() {
        return registeredUsers;
    }
    public void adduser(User user) {
        this.registeredUsers.add(user);
    }
    public void removeUser(User user) {
        this.registeredUsers.remove(user);
    }
}
