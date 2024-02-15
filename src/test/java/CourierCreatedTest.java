import org.example.Courier;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class CourierCreatedTest extends BaseTest {


    //курьера можно создать;

    @Test
    public void checkCourierCreate() {
        this.courier = new Courier("KostKhar", "1234", "kostya");
        courier.createCourier().then().assertThat().statusCode(201);
    }

    //нельзя создать двух одинаковых курьеров
    @Test
    public void checkCourierNotCreate2Same() {
        this.courier = new Courier("KostKhar", "1234", "kostya");
        courier.createCourier();
        assertEquals(409, courier.createCourier().thenReturn().statusCode());
    }


    //чтобы создать курьера, нужно передать в ручку все обязательные поля
    @Test
    public void checkCourierNotCreateWithoutNecessary() {
        this.courier = new Courier("KostKhar", "1234");
        assertEquals(400, courier.createCourier().thenReturn().statusCode());
    }


    //запрос возвращает правильный код ответа
    @Test
    public void checkCourierCreateStatusCode() {
        this.courier = new Courier("KostKhar", "1234", "kostya");
        assertEquals(201, courier.createCourier().thenReturn().statusCode());
    }


    //успешный запрос возвращает ok: true
    @Test
    public void checkCourierCreateWithCorrectResponseBody() {
        this.courier = new Courier("KostKhar", "1234", "kostya");
        courier.createCourier().then().statusCode(201).extract().
                path("ok", String.valueOf(is(true)));
    }


    //если одного из полей нет, запрос возвращает ошибку
    @Test
    public void checkCourierNotCreateWithoutNecessaryResponseBody() {
        this.courier = new Courier("KostKhar", "1234");
        courier.createCourier().then().assertThat().statusCode(400);
    }


    //если создать пользователя с логином, который уже есть, возвращается ошибка 409
    @Test
    public void checkCourierNotCreateWithReplayLoginWithCorrectResponseBody() {
        this.courier = new Courier("KostKhar", "1234", "kostya");
        courier.createCourier();
        assertEquals("Этот логин уже используется", courier.createCourier().then().extract().path("message"));
    }


}
