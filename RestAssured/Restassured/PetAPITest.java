package Restassured;
 
 
 
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
import static io.restassured.RestAssured.*;
 
public class PetAPITest {
 
    RequestSpecification req;
    long petId;
 
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
 
        req = given().header("Content-Type", "application/json");
 
        Console.log("Setup completed...");
    }
 
    @Test(priority = 1)
    public void createPet() {
 
        String body = "{ \"name\": \"Doggy\", \"status\": \"available\" }";
 
        Console.log("Creating pet...");
 
        Response res = given()
                .spec(req)
                .body(body)
        .when()
                .post("/pet");
 
        res.then().statusCode(200);
 
        petId = res.jsonPath().getLong("id");
 
        Console.log("Pet created with ID: " + petId);
 
        Assert.assertTrue(petId > 0, "Invalid Pet ID");
    }
 
    @Test(priority = 2, dependsOnMethods = "createPet")
    public void getPet() {
 
        Console.log("Getting pet details...");
 
        Response res = given()
                .spec(req)
        .when()
                .get("/pet/" + petId);
 
        res.then().statusCode(200);
 
        Console.log("GET Response: " + res.asString());
    }
 
    @Test(priority = 3, dependsOnMethods = "getPet")
    public void updatePet() {
 
        String body = "{ \"id\": " + petId + ", \"name\": \"Doggy\", \"status\": \"sold\" }";
 
        Console.log("Updating pet status...");
 
        Response res = given()
                .spec(req)
                .body(body)
        .when()
                .put("/pet");
 
        res.then().statusCode(200);
 
        Console.log("Pet updated successfully");
    }
 
    @Test(priority = 4, dependsOnMethods = "updatePet")
    public void verifyUpdate() {
 
        Console.log("Verifying update...");
 
        Response res = given()
                .spec(req)
        .when()
                .get("/pet/" + petId);
 
        res.then().statusCode(200);
 
        String status = res.jsonPath().getString("status");
 
        Console.log("Updated status: " + status);
 
        Assert.assertEquals(status, "sold");
    }
 
    @Test(priority = 5, dependsOnMethods = "verifyUpdate")
    public void deletePet() {
 
        Console.log("Deleting pet...");
 
        Response res = given()
                .spec(req)
        .when()
                .delete("/pet/" + petId);
 
        res.then().statusCode(200);
 
        Console.log("Pet deleted");
    }
 
    @Test(priority = 6, dependsOnMethods = "deletePet")
    public void verifyDeletion() {
 
        Console.log("Verifying deletion...");
 
        Response res = given()
                .spec(req)
        .when()
                .get("/pet/" + petId);
 
        res.then().statusCode(404);
 
        Console.log("Pet not found (expected)");
    }
}