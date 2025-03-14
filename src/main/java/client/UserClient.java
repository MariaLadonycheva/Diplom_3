package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import model.User;


public class UserClient {

    private static final String REGISTER_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/register";
    private static final String LOGIN_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String DELETE_ENDPOINT = "https://stellarburgers.nomoreparties.site/api/auth/user";

    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(REGISTER_ENDPOINT);
    }

    public Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_ENDPOINT);
    }
}

