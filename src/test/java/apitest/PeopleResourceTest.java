package apitest;

import settinguptoken.GettingToken;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class PeopleResourceTest extends GettingToken {
    @Test
    public void getPeople() throws IOException {
        String url = buildUrl("/test/people") ;
        String token = "Bearer " + getToken();

        io.restassured.response.Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get(url);
        String res = response.asString();
        System.out.println(res);
        JsonPath jsonPath = new JsonPath(res);
        String jsonResponseBodyKeyValueAsString = jsonPath.get("body");
        JsonPath jsonPath1 = new JsonPath(jsonResponseBodyKeyValueAsString);
        String actualName = jsonPath1.get("name");
        System.out.println(actualName);
        Assert.assertEquals(actualName, "Luke Skywalker");

    }
}
