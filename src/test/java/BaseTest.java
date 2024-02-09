import io.restassured.RestAssured;
import org.example.Courier;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    Courier courier;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    //чистка данных после теста ля курьера,  в заказах @Override
    @After
    public void clearAllData() {
        int sessionId = courier.loginCourier().then().
                statusCode(200).extract().
                path("id");
        courier.deleteCourier(sessionId);
    }


}
