package com.user.rest.test;

import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by sergey on 06.09.15.
 */
public class TestUserController {

    RestTemplate restTemplate = new RestTemplate();

    private static final String MAIN_URL = "http://localhost:8080/api/v1/";

    @Test
    public void shouldBeGreetingMessageAtStart() {
        ResponseEntity<String> response = restTemplate.getForEntity(MAIN_URL, String.class);
        assertThat(response.getBody(), equalTo("{\"message\":\"Greetings from User Rest!\"}"));
    }

    @Test
    public void shouldBeAtLeastOneUserAtStart() {
        User[] response = restTemplate.getForObject(MAIN_URL + "users", User[].class);
        assertThat(response, arrayWithSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldAddNewUser() {
        User user = new User();
        user.setName("Dmytro");
        Response response = restTemplate.postForObject(MAIN_URL + "users", user, Response.class);
        assertThat(response.getStatus(), is(CREATED));
        assertThat(response.getUser().getName(), equalTo(user.getName()));
    }
}
