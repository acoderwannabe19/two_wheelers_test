import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import sn.ept.git.dic2.entities.Marque;

import static io.restassured.RestAssured.given;

public class MarqueServiceTest extends ServerTest{
    @Test
    public void listeMarquesSucces() {
        given().when().get("/marques").then().statusCode(200);
    }

    @Test
    public void creerMarque200() {
        Marque marque = new Marque();
        marque.setId(688);
        marque.setNom("Gucci");

        given()
                .contentType(ContentType.JSON)
                .body(marque)
                .when()
                .put("/marques")
                .then()
                .statusCode(200);

        given().when().delete("/marques/688");
    }

    @Test
    public void modifMarque404() {
        Marque marque = new Marque();
        marque.setId(68777);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", marque.getId())
                .body(marque)
                .when()
                .put("/marques/{id}")
                .then()
                .statusCode(404);

    }


    @Test
    public void modifMarque200() {
        Marque marque = new Marque();
        marque.setId(1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", marque.getId())
                .body(marque)
                .when()
                .put("/marques/{id}")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo(marque.getId()));

    }

    @Test
    public void rechercherMarque200() {
        given().when().get("/marques/4")
                .then().statusCode(200);
    }

    @Test
    public void rechercherMarque404() {
        given().when().get("/marques/35043")
                .then().statusCode(404);
    }

    @Test
    public void supprimerMarque200() {
        given().when().delete("/marques/3")
                .then().statusCode(200);
    }

    @Test
    public void supprimerMarque404() {
        given().when().delete("/marques/35131")
                .then().statusCode(404);
    }
}
