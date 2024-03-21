package com.crio.coderhack.services;

import com.crio.coderhack.Enums.Badge;
import com.crio.coderhack.entites.Contest;
import com.crio.coderhack.entites.User;
import com.crio.coderhack.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServicesImpl implements UserService {

    private List<User> currentUsers = Contest.getInstance().getRegisteredUsers();
    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean saveUser(User user) {
        if (currentUsers.size() <= 0) {
            currentUsers = getAllUsers();
        }

        boolean idExists = currentUsers
                .stream()
                .anyMatch(existingUser -> existingUser.getUserId()
                        .equals(user.getUserId()));
        boolean usernameExists = currentUsers
                .stream()
                .anyMatch(existingUser -> existingUser.getUsername()
                        .equals(user.getUsername()));

        if (idExists || usernameExists) {
            return false;
        }

        setBadgeForCurrentUser(user);

        userRepository.save(user);
        Contest.getInstance().adduser(user);
        refreshCurrentUsersList();
        return true;
    }


    @Override
    public User removeUser(Integer userId) {
        if (currentUsers.size() <= 0) {
            currentUsers = getAllUsers();
        }

        Optional<User> userToRemove = currentUsers.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();

        if (userToRemove.isPresent()) {
            User removedUser = userToRemove.get();
            Contest.getInstance().removeUser(removedUser);
            userRepository.delete(removedUser);
            refreshCurrentUsersList();
            return removedUser;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository
                .findAll(Sort.by(Sort.Direction.ASC, "score"));
    }

    @Override
    public User updateUserScore(Integer userId,Integer updateBy ) {
        refreshCurrentUsersList();
        if (currentUsers.size() <= 0) {
            currentUsers = getAllUsers();
        }
        refreshCurrentUsersList();

        Optional<User> userToUpdate = currentUsers.stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();

        if (userToUpdate.isPresent()) {
            User user = userToUpdate.get();
            int newScore = user.getScore() + updateBy;
            user.setScore(newScore);
            setBadgeForCurrentUser(user);
            userRepository.save(user);
            return user;
        }
        return null;
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    private void setBadgeForCurrentUser(User user) {
        Integer scoreOfCurrentUser = user.getScore();
        List<Badge> badges = user.getBadges();

        if (scoreOfCurrentUser >= 1 && scoreOfCurrentUser <= 30) {
            if (!badges.contains(Badge.CODE_NINJA)) {
                badges.add(Badge.CODE_NINJA);
            }
        }
        if (scoreOfCurrentUser > 30 && scoreOfCurrentUser <= 60) {
            if (!badges.contains(Badge.CODE_NINJA)) {
                badges.add(Badge.CODE_NINJA);
            }
            if (!badges.contains(Badge.CODE_CHAMP)) {
                badges.add(Badge.CODE_CHAMP);
            }
        }
        if (scoreOfCurrentUser > 60 && scoreOfCurrentUser <= 100) {
            if (!badges.contains(Badge.CODE_NINJA)) {
                badges.add(Badge.CODE_NINJA);
            }
            if (!badges.contains(Badge.CODE_CHAMP)) {
                badges.add(Badge.CODE_CHAMP);
            }
            if (!badges.contains(Badge.CODE_MASTER)) {
                badges.add(Badge.CODE_MASTER);
            }
        }
    }

    private void refreshCurrentUsersList() {
        currentUsers = Contest.getInstance().getRegisteredUsers();
    }
}
