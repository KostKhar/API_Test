package org.example;

import static io.restassured.RestAssured.given;

public class Track {
    int sessionId;

    public Track() {

    }

    public Track(int sessionId) {
        this.sessionId = sessionId;
    }

    public void clearOrder() {
        given()
                .header("Content-type", "application/json")
                .body(this)
                .when()
                .put("/api/v1/orders/cancel");
    }

    public int getId() {
        return sessionId;
    }

    public void setId(int sessionId) {
        this.sessionId = sessionId;
    }

}
