package Restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class Activity2 {

    String username = "justinc";   // :white_check_mark: keep common
    String userId = "9901";

    // :white_check_mark: 1. ADD USER
    @Test(priority = 1)
    public void addNewUserFromFile() throws IOException {

        // :white_check_mark: Correct file path
        FileInputStream inputJSON = new FileInputStream(
                "src/test/java/RestAssured/userInfo.json");

        Response response = given()
                .baseUri("https://petstore.swagger.io/v2/user")
                .header("Content-Type", "application/json")
                .body(inputJSON)
                .when()
                .post();

        inputJSON.close();

        // :white_check_mark: Assertions
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo(userId)); // 9901
    }

    // :white_check_mark: 2. GET USER
    @Test(priority = 2)
    public void getUserInfo() {

        // :white_check_mark: Correct folder path
        File outputJSON = new File(
                "src/test/java/RestAssured/userGETResponse.json");

        Response response = given()
                .baseUri("https://petstore.swagger.io/v2/user")
                .header("Content-Type", "application/json")
                .pathParam("username", username)
                .when()
                .get("/{username}");

        String resBody = response.getBody().asPrettyString();

        try {
            outputJSON.createNewFile();
            FileWriter writer = new FileWriter(outputJSON.getPath());
            writer.write(resBody);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // :white_check_mark: Assertions
        response.then().body("id", equalTo(9901));
        response.then().body("username", equalTo(username));
        response.then().body("firstName", equalTo("Justin"));
        response.then().body("lastName", equalTo("Case"));
        response.then().body("email", equalTo("justincase@mail.com"));
        response.then().body("password", equalTo("password123"));
        response.then().body("phone", equalTo("9812763450"));
    }

    // :white_check_mark: 3. DELETE USER
    @Test(priority = 3)
    public void deleteUser() {

        Response response = given()
                .baseUri("https://petstore.swagger.io/v2/user")
                .header("Content-Type", "application/json")
                .pathParam("username", username)
                .when()
                .delete("/{username}");

        // :white_check_mark: Assertions
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo(username));
    }
}