package Restassured;

// Correct imports
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class PostRequest {

    // Base URL
    String ROOT_URI = "https://petstore.swagger.io/v2/pet";

    @Test
    public void AddNewPet() {

        // Correct JSON (single line OR concatenated)
        String reqBody = "{"
                + "\"id\": 77232,"
                + "\"name\": \"Riley\","
                + "\"status\": \"alive\""
                + "}";

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .body(reqBody)
                .when()
                        .post(ROOT_URI);

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);
    }
}