package org.example;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Courier {
    private final String login;
    private String password;
    private String firstname;


    public Courier(String login, String password, String firstname) {
        this.login = login;
        this.password = password;
        this.firstname = firstname;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Courier(String login) {
        this.login = login;
    }


    public Response loginCourier() {
        return given()
                .header("Content-type", "application/json")
                .body(this)
                .when()
                .post("/api/v1/courier/login");
    }

    public Response createCourier() {
        return given()
                .header("Content-type", "application/json")
                .body(this)
                .when()
                .post("/api/v1/courier");
    }

    public Response deleteCourier(int sessionId) {
        return given()
                .header("Content-type", "application/json")
                .body(this)
                .when()
                .delete("/api/v1/courier/" + sessionId);
    }


}
