package com.user.rest.test;

import com.user.rest.entity.User;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sergey on 06.09.15.
 */
public class TestUserController {


    RestTemplate restTemplate = new RestTemplate();

    private static final String MAIN_URL = "http://localhost:8080/api/v1/";

    @Test
    public void shouldBeGreetingMessageAtStart() {
        String msg = restTemplate.getForObject(MAIN_URL, String.class);
        assertEquals(msg, "Greetings from User Rest!");
    }

    @Test
    public void shouldBeAtLeastOneUserAtStart() {
        User[] forNow = restTemplate.getForObject(MAIN_URL + "users", User[].class);
        List<User> users = Arrays.asList(forNow);
        assertEquals(1, users.size());
        assertEquals("ADMIN", users.get(0).getName());
    }
}
