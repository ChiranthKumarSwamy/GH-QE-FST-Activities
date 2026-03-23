package Restassured;

// Required imports
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class getIPInformationQuery {

    // Base URL
    final static String ROOT_URI = "http://ip-api.com/json";

    @Test
    public void getIPInformation() {

        Response response =
                given()
                        .contentType(ContentType.JSON) // Header
                .when()
                        .queryParam("fields", "query,country,city,timezone") // Query param
                        .get(ROOT_URI + "/125.219.5.94"); // API call

        // Print response
        System.out.println(response.getBody().asPrettyString());
    }
}