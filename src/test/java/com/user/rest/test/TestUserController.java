package com.user.rest.test;

import com.user.rest.entity.Message;
import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by sergey on 06.09.15.
 */
public class TestUserController {

    RestTemplate restTemplate = new RestTemplate();

    private static final String MAIN_URL = "http://localhost:8080/api/v1/";
    private static final String USERS_INFO = MAIN_URL + "users";
    private static final String USER_INFO = USERS_INFO + "/user1";


    @Test
    public void shouldBeGreetingMessageAtStart() {
        ResponseEntity<Message> response = restTemplate.getForEntity(MAIN_URL, Message.class);
        assertThat(response.getBody().getMessage(), equalTo("Greetings from User Rest!"));
    }

    @Test
    public void shouldBeAtLeastOneUserAtStart() {
        User[] response = restTemplate.getForObject(USERS_INFO, User[].class);
        assertThat(response, arrayWithSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldAddNewUser() {
        User user = new User();
        user.setName("Dmytro");
        Response response = restTemplate.postForObject(USERS_INFO, user, Response.class);
        assertThat(response.getStatus(), is(CREATED));
        assertThat(response.getUser().getName(), equalTo(user.getName()));
    }

    @Test
    public void shouldUpdateUsername() {
        User user = new User();
        user.setName("Ivan");
        restTemplate.postForObject(MAIN_URL + "users", user, Response.class);
        given().put("http://localhost:8080/api/v1/users/user1?id=1&name=Georgy");
        given().get("http://localhost:8080/api/v1/users/user1?id=1").then().body("name", equalTo("Georgy"));
    }
}
