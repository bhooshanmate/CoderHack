package com.crio.coderhack.DTO;

import com.crio.coderhack.entites.User;

public class ResponseDTO {
    private User user;
    private String message;

    public ResponseDTO(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}
