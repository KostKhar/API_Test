import io.restassured.response.Response;
import org.example.Track;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static io.restassured.RestAssured.given;
import static java.lang.String.valueOf;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)

public class OrderTestParametrized extends BaseTest {
    //    Проверь, что когда создаёшь заказ:\
    List color;
    int rentTime;
    String deliveryDate;
    Response response;

    public OrderTestParametrized(int rentTime, String deliveryDate, List color) {
        String firstName = "Kostya";
        String lastName = "Kharik";
        String address = "Konoha, 142 apt.";
        String metroStation = "4";
        String phone = "8951556554";
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        String comment = "Оставьте у подъеда №5";
        this.color = color;
    }


    @Parameterized.Parameters(name = "Test data: rentTime {0}, deliveryDate {1}, color {2}")
    public static Object[] getData() {
        Object[][] objects = {
                {1, "2024-02-15", List.of("BLACK")},
                {3, "2024-02-15", List.of("BLACK", "GREY")},
                {5, "2024-02-15", List.of()},
        };
        return objects;
    }

    @Test
    public void checkColorTest() {
        OrderTestParametrized order = new OrderTestParametrized(rentTime, deliveryDate, color);
        Response response = given()
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post("/api/v1/orders");
        this.response = response;
        response.then().assertThat().statusCode(201).and().extract().path("track", valueOf(notNullValue()));

    }

    @Override
    @After
    public void clearAllData() {
        int sessionId = response.then().extract().path("track");
        new Track(sessionId).clearOrder();
    }


}
