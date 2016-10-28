package com.user.rest.controller;

import com.user.rest.entity.User;
import com.user.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sergey on 05.09.15.
 */
@RestController
@RequestMapping("/rest")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public List<User> users() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Map<String, String> createUser(@RequestBody User user) {
        String id = userRepository.save(user).getId();
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String id, @RequestBody User user) {
        User dbUser = userRepository.findOne(id);
        if (user.getEmail() != null) {
            dbUser.setEmail(user.getEmail());
        }
        if (user.getFirstname() != null) {
            dbUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname() != null) {
            dbUser.setLastname(user.getLastname());
        }
        if (user.getUsername() != null) {
            dbUser.setUsername(user.getUsername());
        }
        return userRepository.save(dbUser);
    }

    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable("id") String id) {
        userRepository.delete(id);
    }
}
