package com.user.rest.test;

import com.user.rest.entity.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;

/**
 * Created by sergey on 06.09.15.
 */
public class TestUserController {

    @Test
    public void shouldBeGreetingMessageAtStart() {
        when().get("/")
                .then()
                .body(equalTo("Greetings from User Rest!"));
    }

    @Test
    public void shouldAddNewUser() {
        User user = getTestUser();
        User saved = addUser(user);
        assertEquals(user, saved, "id");
    }

    @Test
    public void shouldUpdateUserLastName() {
        User user = getTestUser();
        User inserted = addUser(user);
        // update user data
        inserted.setUsername("Misha");
        User updated = updateUser(inserted);
        assertEquals(inserted, updated, "id");
    }

    @Test
    public void shouldDeleteUser() {
        User user = getTestUser();
        User inserted = addUser(user);
        delete("/rest/user/delete/{id}", inserted.getId());
        User[] users = get("/rest/users").getBody().as(User[].class);
        assertFalse(Arrays.asList(users).contains(inserted));
    }

    private User addUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .post("/rest/user/add")
                .getBody().as(User.class);
    }

    private User updateUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .put("/rest/user/update/{id}", user.getId())
                .getBody().as(User.class);
    }

    private User getTestUser() {
        User user = new User();
        user.setLastname("Ivanov");
        user.setFirstname("Ivan");
        user.setEmail("ivanov@gmail.com");
        user.setUsername("ivan4ik");
        return user;
    }

    private static void assertEquals(Object obj1, Object obj2, String... fieldsToIgnore) {
        Assert.assertThat(obj1 + "\n not equal to " + obj2, EqualsBuilder.reflectionEquals(obj1, obj2, fieldsToIgnore), equalTo(true));
    }
}
