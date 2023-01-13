package hsos.vts;

import io.quarkus.test.junit.QuarkusTest;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        //EmbeddedPostgres pg = EmbeddedPostgres.start();
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello from RESTEasy Reactive"));
    }

}