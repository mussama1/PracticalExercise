package api;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class JsonPlaceholderPostTest {

    // Constants - Easy to maintain and change
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String POSTS_ENDPOINT = "/posts";
    private static final int RESPONSE_TIME_LIMIT_MS = 3000; // 3 seconds

    @BeforeClass
    public void setup() {
        // Set base URL once - applies to all tests
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Test 1: Verify single post data with all validations
     * - Status code
     * - Response time
     * - Content type
     * - Body fields
     */
    @Test(priority = 1, description = "Verify GET /posts/1 returns correct data")
    public void verifyGetSinglePost() {
        try {
            given() // SETUP - Prepare the request
                    .log().uri()                          // Log the request URL
                    .when()//action send the request
                    .get(POSTS_ENDPOINT + "/1")
                    .then()
                    .log().status()                       // Log response status
                    .statusCode(200)                      // Verify status code
                    .time(lessThan((long) RESPONSE_TIME_LIMIT_MS))  // Verify response time
                    .contentType("application/json")      // Verify content type
                    .body("id", equalTo(1))               // Verify id
                    .body("userId", equalTo(1))           // Verify userId
                    .body("title", notNullValue())        // Verify title exists
                    .body("body", notNullValue());        // Verify body exists
        }
        catch (Exception ex) {
            // Error handling
            System.out.println("Something went wrong while calling the API: " + ex.getMessage());
            Assert.fail("Test failed due to an error...");
        }
    }
}