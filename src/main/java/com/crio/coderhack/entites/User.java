package com.crio.coderhack.entites;

import com.crio.coderhack.Enums.Badge;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "users")
public class User {

    @Id
    private Integer userId;
    private String username;
    private Integer score;
    private List<Badge> badges;

    public User (){
        score = 0;
        badges = new ArrayList<>(3);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        if( score <= 100 && score >= 0) {
            this.score = score;
        }
    }

    public List<Badge> getBadges() {
        return badges;
    }

    public void setBadge(Badge badge) {
        if (!badges.contains(badge)) {
            badges.add(badge);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(),
                getUsername(),
                getScore(),
                getBadges());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", score=" + score +
                ", badges=" + badges +
                '}';
    }
}
