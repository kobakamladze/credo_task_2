package base;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.testng.annotations.*;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8081";
        WireMock.configureFor("localhost", 8081);
    }

    @AfterMethod
    public void resetStubs() {
        WireMock.reset();
    }
}
