package apitest;

import settinguptoken.GettingToken;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@Test
public class SubmitResourceTest extends GettingToken {
    public void submitTest() throws IOException {
        String token = "Bearer "+ getToken();
        String submitPayload = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ "/payloads/submit.json")));
        RequestSpecification requestSpecification = given().body(submitPayload);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Authorization", token );
        io.restassured.response.Response response = requestSpecification.post(buildUrl("/test/submit"));
        String responseBody = response.asString();
        System.out.println(responseBody);
        JsonPath jsonPath = new JsonPath(responseBody);
        String actualUserName = jsonPath.get("message");
        Assert.assertEquals(actualUserName, "You are an Adult");

    }
}
