package Restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Activity3 {

    // Declare request specification
    RequestSpecification requestSpec;

    // Declare response specification for POST and GET
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {

        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setBasePath("/pet")
                .addHeader("Content-Type", "application/json")
                .build();

        // Create response specification for POST and GET
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .build();
    }

    @DataProvider(name = "petInfo")
    public Object[][] petInfoProvider() {
        return new Object[][] {
                { 77232, "Riley", "alive" },
                { 77233, "Hansel", "alive" }
        };
    }

    @Test(priority = 1, dataProvider = "petInfo")
    public void addPets(int petId, String petName, String petStatus) {

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("id", petId);
        reqBody.put("name", petName);
        reqBody.put("status", petStatus);

        given()
            .spec(requestSpec)
            .body(reqBody)
        .when()
            .post()
        .then()
            .spec(responseSpec)
            .body("id", equalTo(petId))
            .body("name", equalTo(petName))
            .body("status", equalTo(petStatus))
            .log().all();
    }

    @Test(priority = 2, dataProvider = "petInfo")
    public void getPets(int petId, String petName, String petStatus) {

        given()
            .spec(requestSpec)
            .pathParam("petId", petId)
            .log().all()
        .when()
            .get("/{petId}")
        .then()
            .spec(responseSpec)
            .body("id", equalTo(petId))
            .body("name", equalTo(petName))
            .body("status", equalTo(petStatus))
            .log().all();
    }

    @Test(priority = 3, dataProvider = "petInfo")
    public void deletePets(int petId, String petName, String petStatus) {

        given()
            .spec(requestSpec)
            .pathParam("petId", petId)
        .when()
            .delete("/{petId}")
        .then()
            .statusCode(200)
            .body("code", equalTo(200))
            .body("message", equalTo(String.valueOf(petId)))
            .log().all();
    }
}