import io.restassured.RestAssured;
import org.example.Courier;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;

public class CourierLoginTest extends BaseTest {

    @Override
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
        this.courier = new Courier("KostKhar", "1234", "kostya");
        courier.createCourier();
    }


    //курьер может авторизоваться

    @Test
    public void checkCourierLoginWithCorrectLoginAndPassword() {
        this.courier = new Courier("KostKhar", "1234");
        courier.loginCourier().then().assertThat();
    }

//    для авторизации нужно передать все обязательные поля;

    @Test
    public void checkCourierLoginWithoutParams() {
        new Courier("KostKhar", "1234").loginCourier().then().assertThat().statusCode(400);
    }


    //    система вернёт ошибку, если неправильно указать логин или пароль;
    @Test
    public void checkCourierLoginWithUncorrectPassword() {
        assertEquals("Учетная запись не найдена", new Courier("KostKhar", "12345").loginCourier().thenReturn().path("message"));
    }


    //    если какого-то поля нет, запрос возвращает ошибку
    @Test
    public void checkCourierLoginWithoutPassword() {
        assertEquals(400, new Courier("KostKhar").loginCourier().thenReturn().statusCode());
    }


    //    если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
    @Test
    public void checkCourierLoginWithUncorrectLogin() {
        assertEquals("Учетная запись не найдена", new Courier("ivan", "1234").loginCourier().thenReturn().path("message"));
    }

    //    успешный запрос возвращает id
    @Test
    public void checkCourierLoginReturnId() {
        new Courier("KostKhar", "1234").loginCourier().then().
                assertThat().extract().
                path("id", String.valueOf(notNullValue()));
    }

}
