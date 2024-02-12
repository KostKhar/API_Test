import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class OrderAllTest extends BaseTest {

    @Test
    public void checkOrderAll() {
        ArrayList<String> orders = given()
                .get("/api/v1/orders")
                .then().extract().path("orders");
        assertTrue(orders.size() > 0);
    }

    @Override
    @After
    public void clearAllData() {
    }

}
