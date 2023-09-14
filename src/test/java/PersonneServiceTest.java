import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import sn.ept.git.dic2.entities.Categorie;
import sn.ept.git.dic2.entities.Personne;
import sn.ept.git.dic2.entities.Produit;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PersonneServiceTest extends ServerTest{
    @Test
    public void listePersonnesSucces() {
        given().when().get("/personnes").then().statusCode(200);
    }

    @Test
    public void creerPersonne200() {
        Personne personne = new Personne(687, "Doe", "John", "johndoe@example.com", "123456789");

        given()
                .contentType(ContentType.JSON)
                .body(personne)
                .when()
                .put("/personnes")
                .then()
                .statusCode(200);

        given().when().delete("/personnes/687");
    }

    @Test
    public void modifPersonne404() {
        Personne personne = new Personne();
        personne.setId(30004);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", personne.getId())
                .body(personne)
                .when()
                .put("/personnes/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void modifPersonne200() {
        Personne personne = new Personne();
        personne.setId(1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", personne.getId())
                .body(personne)
                .when()
                .put("/personnes/{id}")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo(personne.getId()));

    }

    @Test
    public void rechercherPersonne200() {
        given().when().get("/personnes/46")
                .then().statusCode(200);
    }

    @Test
    public void rechercherPersonne404() {
        given().when().get("/personnes/3504")
                .then().statusCode(404);
    }

    @Test
    public void supprimerPersonne200() {
        given().when().delete("/personnes/12")
                .then().statusCode(200);
    }

    @Test
    public void supprimerPersonne404() {
        given().when().delete("/personnes/3005")
                .then().statusCode(404);
    }

}
