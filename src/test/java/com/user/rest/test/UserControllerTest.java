package com.user.rest.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.user.rest.Application;
import com.user.rest.entity.User;
import com.user.rest.repository.UserRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.restassured.RestDocumentationFilter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.restdocs.restassured.RestAssuredRestDocumentation;

import java.util.Arrays;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

/**
 * Created by sergey on 06.09.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation =
            new JUnitRestDocumentation("build/generated-snippets");

    private RequestSpecification spec;

    private RestDocumentationFilter documentationFilter;

    @Before
    public void setUp() {
        this.documentationFilter = document("{method-name}",
                preprocessResponse(prettyPrint()));
        this.spec = new RequestSpecBuilder().addFilter(
                documentationConfiguration(this.restDocumentation))
                .addFilter(this.documentationFilter)
                .build();
    }

    @Test
    public void shouldBeGreetingMessageAtStart() {
        when().get("/")
                .then()
                .body(equalTo("Greetings from User Rest!"));
    }

    @Test
    public void getUser() {
        RestAssured
                .given(this.spec)
                .accept("application/json")
                .filter(document("user", preprocessResponse(prettyPrint()), responseFields(
                        fieldWithPath("[].id").description("The user's contact details"),
                        fieldWithPath("[].username").description("The user's contact details"),
                        fieldWithPath("[].lastname").description("The user's contact details"),
                        fieldWithPath("[].email").description("The user's contact details"),
                        fieldWithPath("[].firstname").description("The user's email address"))))
                .when().get("/rest/users")
                .then()
                .assertThat()
                .statusCode(equalTo(200));
    }

    @Test
    public void shouldUpdateUserLastName() {
        User user = createTestUser();
        user.setUsername("Misha");
        User updated = updateUser(user);
        assertEquals(user, updated, "id");
    }

    @Test
    public void shouldDeleteUser() {
        User user = createTestUser();
        String id = addUser(user);
        User inserted = getUserById(id);
        delete("/rest/user/delete/{id}", inserted.getId());
        User[] users = get("/rest/users").getBody().as(User[].class);
        assertFalse(Arrays.asList(users).contains(inserted));
    }

    private User getUserById(String id) {
        return get("/rest/user/{id}", id).getBody().as(User.class);
    }

    private String addUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .post("/rest/user/add")
                .then().extract().path("id");
    }

    private User updateUser(User user) {
        return given(this.spec)
                .contentType("application/json")
                .body(user)
                .put("/rest/user/update/{id}", user.getId())
                .getBody().as(User.class);
    }

    private User createTestUser() {
        User user = new User();
        user.setLastname("Ivanov");
        user.setFirstname("Ivan");
        user.setEmail("ivanov@gmail.com");
        user.setUsername("ivan4ik");
        String id = addUser(user);
        User saved = getUserById(id);
        assertEquals(user, saved, "id");
        return saved;
    }

    private static void assertEquals(Object obj1, Object obj2, String... fieldsToIgnore) {
        Assert.assertThat(obj1 + "\n not equal to " + obj2, EqualsBuilder.reflectionEquals(obj1, obj2, fieldsToIgnore), equalTo(true));
    }
}
