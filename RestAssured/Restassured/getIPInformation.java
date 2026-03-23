package Restassured;

// Required imports
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class getIPInformation {

    // Base URL with path parameter
    String ROOT_URI = "http://ip-api.com/json/{ipAddress}";

    @Test
    public void getIPInfoTest() {

        Response response =
                given()
                        .contentType(ContentType.JSON) // Set header
                .when()
                        .pathParam("ipAddress", "107.218.134.107") // Path param
                        .get(ROOT_URI); // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());
    }
}