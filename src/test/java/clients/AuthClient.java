package clients;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    public Response createToken(String username, String password) {
        String requestBody = "{\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth");
    }
}