package tests.api.users;

import base.BaseTest;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.given;

public class UsersTest extends BaseTest {

    @Test
    public void getUsersReturnsSuccessTest() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody("OK")));

        given()
                .log().all()
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getUsersReturnsInternalServerErrorTest() {
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                    "status": "error",
                                    "message": "Internal Server Error"
                                }
                            """)));

        given()
                .log().all()
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(500);
    }
}
