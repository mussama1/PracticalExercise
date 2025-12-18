package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostTest {

    @Test
    public void verifyJsonPlaceholderPostData() {

        try {
            // API Call
            Response response = RestAssured.get("https://jsonplaceholder.typicode.com/posts/1");

            // Status code
            Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

            // Body validation
            Assert.assertEquals(response.jsonPath().getInt("userId"), 2, "userId mismatch");
            Assert.assertEquals(response.jsonPath().getInt("id"), 2, "id mismatch");

        } catch (Exception e) {
            // Error handling
            System.out.println("Something went wrong while calling the API: " + e.getMessage());
            Assert.fail("Test failed due to an error...");
        }
    }
}
