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
    String firstName;
    String lastName;
    String address;
    String metroStation;
    String phone;
    String comment;
    List color;
    int rentTime;
    String deliveryDate;
    Response response;

    public OrderTestParametrized(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List color) {
        this.firstName = firstName;
        this.lastName= lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }


    @Parameterized.Parameters(name = "Test data: firstname {0}, lastname {1}, address {3}, metroStation {4}, phone {5}, rentTime {6}, deliveryDate {7}, comment {8}, color {9}")
    public static Object[] getData() {
        return new Object[][] {
                {"Kostya", "Kharik",  "Konoha, 142 apt.", "4", "8951556554", 1, "2024-02-15", "Оставьте у подъеда №5", List.of("BLACK")},
                {"Ivan", "Ivanov",  "Lenina, 50", "8", "89165542636", 3, "2024-02-19", "Оставьте у квартиры", List.of("BLACK", "GREY")},
                {"Sidr", "Sidorov",  "School district, 39", "10", "89678772524", 5, "2024-02-25", "", List.of()},
        };

    }

    @Test
    public void checkColorTest() {
        OrderTestParametrized order = new OrderTestParametrized(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment,color);
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
