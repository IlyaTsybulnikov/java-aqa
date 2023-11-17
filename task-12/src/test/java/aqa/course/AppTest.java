package aqa.course;

import static aqa.course.constants.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;

import aqa.course.pojo.error.ErrorResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aqa.course.pojo.authorization.*;
import aqa.course.pojo.resource.ResourceResponse;
import aqa.course.pojo.user.*;
import io.restassured.http.ContentType;

import java.util.List;

public class AppTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getListUsersTest() {
        List<UserResponse> users = given()
                .when()
                .get(USERS_URL)
                .then().body("data", iterableWithSize(6))
                .extract().body().jsonPath().getList("data", UserResponse.class);

        users.forEach(user -> {
            Assertions.assertTrue(user.getAvatar().startsWith(AVATAR_LINK_BEGINNING + user.getId()));
        });
    }

    @Test
    public void getSingleUserTest() {
        given().
                when().get(USERS_URL + "/2").
                then().assertThat().statusCode(200)
                .and().body("data.first_name", is("Janet"))
                .and().body("data.last_name", is("Weaver"))
                .and().body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    public void getUserNotFoundTest() {
        given().
                when().get(USERS_URL + "/2222222").
                then().assertThat().statusCode(404);
    }

    @Test
    public void getListResourcesTest() {
        List<ResourceResponse> resources = given()
                .when()
                .get(RESOURCES_URL)
                .then().body("data", iterableWithSize(6))
                .extract().body().jsonPath().getList("data", ResourceResponse.class);

        resources.forEach(resource -> {
            Assertions.assertNotNull(resource.getId());
        });
    }

    @Test
    public void getSingleResourceTest() {
        ResourceResponse response = given().
                when().get(RESOURCES_URL + "/2").
                then().assertThat().statusCode(200)
                .extract().body().jsonPath().getObject("data", ResourceResponse.class);

        Assertions.assertEquals(response.getName(), "fuchsia rose");
        Assertions.assertEquals(response.getYear(), 2001);
        Assertions.assertEquals(response.getColor(), "#C74375");
        Assertions.assertEquals(response.getPantone_value(), "17-2031");
    }

    @Test
    public void getResourceNotFoundTest() {
        given().
                when().get(RESOURCES_URL + "/2222222").
                then().assertThat().statusCode(404);
    }

    @Test
    public void createUserTest() throws JsonProcessingException {
        UserRequest newUser = new UserRequest()
                .withName("Morbius")
                .withJob("Doctor");

        UserCreateResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(newUser))
                .when().post(USERS_URL)
                .then().assertThat().statusCode(201)
                .extract().body().as(UserCreateResponse.class);

        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getCreatedAt());
        Assertions.assertEquals(newUser.getName(), response.getName());
        Assertions.assertEquals(newUser.getJob(), response.getJob());
    }

    @Test
    public void updateUserTest() throws JsonProcessingException {
        UserRequest newUser = new UserRequest()
                .withName("Morbius")
                .withJob("Vampire");

        UserUpdateResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(newUser))
                .when().put(USERS_URL + "/2")
                .then().assertThat().statusCode(200)
                .extract().body().as(UserUpdateResponse.class);

        Assertions.assertNotNull(response.getUpdatedAt());
        Assertions.assertEquals(newUser.getName(), response.getName());
        Assertions.assertEquals(newUser.getJob(), response.getJob());
    }

    @Test
    public void deleteUserTest() {
        given().
                when().delete(USERS_URL + "/2").
                then().assertThat().statusCode(204);
    }

    @Test
    public void registerSuccessfulTest() throws JsonProcessingException {
        AuthorizationRequest authRequest = new AuthorizationRequest()
                .withEmail("eve.holt@reqres.in")
                .withPassword("pistol");

        AuthorizationResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(authRequest))
                .when().post(REGISTER_URL)
                .then().assertThat().statusCode(200)
                .extract().body().as(AuthorizationResponse.class);

        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getToken());
    }

    @Test
    public void registerUnsuccessfulTest() throws JsonProcessingException {
        AuthorizationRequest authRequest = new AuthorizationRequest()
                .withEmail("eve.holt@reqres.in");

        ErrorResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(authRequest))
                .when().post(REGISTER_URL)
                .then().assertThat().statusCode(400)
                .extract().body().as(ErrorResponse.class);

        Assertions.assertEquals(response.getError(), "Missing password");
    }

    @Test
    public void loginSuccessfulTest() throws JsonProcessingException {
        AuthorizationRequest authRequest = new AuthorizationRequest()
                .withEmail("eve.holt@reqres.in")
                .withPassword("cityslicka");

        AuthorizationResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(authRequest))
                .when().post(LOGIN_URL)
                .then().assertThat().statusCode(200)
                .extract().body().as(AuthorizationResponse.class);

        Assertions.assertNull(response.getId());
        Assertions.assertNotNull(response.getToken());
    }

    @Test
    public void loginUnsuccessfulTest() throws JsonProcessingException {
        AuthorizationRequest authRequest = new AuthorizationRequest()
                .withEmail("eve.holt@reqres.in");

        ErrorResponse response = given().
                accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(authRequest))
                .when().post(LOGIN_URL)
                .then().assertThat().statusCode(400)
                .extract().body().as(ErrorResponse.class);

        Assertions.assertEquals(response.getError(), "Missing password");
    }
}
