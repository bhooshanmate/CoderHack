package com.crio.coderhack.controllers;

import com.crio.coderhack.DTO.RequestDTO;
import com.crio.coderhack.DTO.ResponseDTO;
import com.crio.coderhack.entites.User;
import com.crio.coderhack.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServicesImpl userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> usersList = userService.getAllUsers();
        return new ResponseEntity<>(usersList,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable("userId") Integer userId){
        User user = userService.getUser(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody RequestDTO userDTO) {

        User user = new User();
        if (userDTO.getUserId() != null && userDTO.getUsername() != null) {
            user.setUserId(userDTO.getUserId());
            user.setUsername(userDTO.getUsername());
        }
        else {
            return new ResponseEntity<>("Request attributes missing", HttpStatus.BAD_REQUEST);
        }



        if (userService.saveUser(user)) {
            return new ResponseEntity<>(new ResponseDTO(user,"User created successfully"),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Registration failed, please try with unique credentials !" ,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{userId}/{updateBy}")
    public ResponseEntity<Object> updateUserScore(@PathVariable("userId") Integer userId,
                                                  @PathVariable("updateBy") Integer updateBy){
        if (updateBy <=0) {
            return new ResponseEntity<>("score cannot be updated by 0 or negative numbers",HttpStatus.BAD_REQUEST);
        }
        User user = userService.updateUserScore(userId,updateBy);
        if (user != null) {
            return new ResponseEntity<>(new ResponseDTO(user, "User score updated successfully !"),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("user with userId " + userId +" not found," +
                    " please provide the correct userId",
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> removeUserFromContest(@PathVariable("userId") Integer userId) {
        User user = userService.removeUser(userId);
        if (user != null) {
            return new ResponseEntity<>(new ResponseDTO(user,"user successfully removed !"),
                    HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found !",
                    HttpStatus.NOT_FOUND);
        }
    }
}
