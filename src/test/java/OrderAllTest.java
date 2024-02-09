import org.junit.After;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderAllTest extends BaseTest {

    @Test
    public void checkOrderAll() {
        given()
                .get("/api/v1/orders")
                .then().assertThat().extract().path("orders", String.valueOf(notNullValue()));
    }

    @Override
    @After
    public void clearAllData() {
    }

}
