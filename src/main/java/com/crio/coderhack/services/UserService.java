package com.crio.coderhack.services;


import com.crio.coderhack.entites.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public boolean saveUser(User user);
    public User removeUser(Integer userId);
    public List<User> getAllUsers();
    public User updateUserScore(Integer userId,Integer updateBy);
    public User getUser(Integer UserId);
}
