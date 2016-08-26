package com.user.rest.test;

import com.user.rest.entity.Message;
import com.user.rest.entity.Response;
import com.user.rest.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.client.HttpServerErrorException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sergey on 06.09.15.
 */
public class TestUserController {

    private RestClient restClient = new RestClient();


    @Test
    public void shouldBeGreetingMessageAtStart() {
        Message response = restClient.getGreetingMessage();
        assertThat(response.getMessage(), equalTo("Greetings from User Rest!"));
    }

    @Test
    public void shouldBeAtLeastOneUserAtStart() {
        User[] response = restClient.getAllUsers();
        assertThat(response, arrayWithSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void shouldAddNewUser() {
        User user = buildUser();
        Response response = restClient.createUser(user);
        assertResponse(response, user);
        response = restClient.getUserById(response.getUser().getUid());
        assertResponse(response, user);
    }

    @Test
    public void shouldUpdateUserLastName() {
        User user = createUser();
        long uid = user.getUid();
        User newUser = new User();
        newUser.setUsername("Misha");
        newUser.setLastname("Dirda");
        restClient.updateUser(uid, newUser);
        Response response = restClient.getUserById(uid);
        Assert.assertThat(response.getUser().getLastname(), equalTo(newUser.getLastname()));
    }

    @Test(expected = HttpServerErrorException.class)
    public void shouldDeleteUser() {
        long userId = createUser().getUid();
        restClient.delete(userId);
        restClient.getUserById(userId);
    }

    private void assertResponse(Response response, User actual) {
        assertNotNull(response);
        User expected = response.getUser();
        assertNotNull(expected);
        Assert.assertThat(expected.getUsername(), equalTo(actual.getUsername()));
        Assert.assertThat(expected.getFirstname(), equalTo(actual.getFirstname()));
        assertEquals(expected.getLastname(), actual.getLastname());
        assertEquals(expected.getEmail(), actual.getEmail());
    }

    private User buildUser() {
        User user = new User();
        user.setUsername("dima99");
        user.setFirstname("Dmytro");
        user.setLastname("Dmitriev");
        user.setEmail("dima@gmail.com");
        return user;
    }

    private User createUser() {
        User user = buildUser();
        return restClient.createUser(user).getUser();
    }

}
