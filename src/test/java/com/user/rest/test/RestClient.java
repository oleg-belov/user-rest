package com.user.rest.test;

import com.user.rest.entity.Message;
import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serhii_Pirohov on 11.09.2015.
 */
public class RestClient {

    RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate();
    }

    private static final String MAIN_URL = "http://localhost:8080/api/v1/";
    private static final String USERS_INFO = MAIN_URL + "users";
    private static final String USER_INFO = USERS_INFO + "/user1";

    public Message getGreetingMessage() {
        return restTemplate.getForObject(MAIN_URL, Message.class);
    }

    public User[] getAllUsers() {
        return restTemplate.getForObject(USERS_INFO, User[].class);
    }

    public Response createUser(User user) {
        return restTemplate.postForObject(USERS_INFO, user, Response.class);
    }

    public Response getUserById(long uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", uid);
        return restTemplate.getForObject(USER_INFO + "?id={id}", Response.class, params);
    }

    public void updateUser(long uid, User newUser) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", uid);
        restTemplate.put(USER_INFO + "?id={id}", newUser, params);
    }

    public void delete(long uid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", uid);
        restTemplate.delete(USER_INFO + "?id={id}", params);
    }
}
