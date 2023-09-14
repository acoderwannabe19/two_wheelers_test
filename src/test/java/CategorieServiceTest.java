import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import sn.ept.git.dic2.entities.Categorie;

import static io.restassured.RestAssured.given;

public class CategorieServiceTest extends ServerTest{
    @Test
    public void listeCategoriesSucces() {
        given().when().get("/categories").then().statusCode(200);
    }

    @Test
    public void creerCategorie200() {
        Categorie categorie = new Categorie();
        categorie.setId(687);
        categorie.setNom("Gucci");

        given()
                .contentType(ContentType.JSON)
                .body(categorie)
                .when()
                .put("/categories")
                .then()
                .statusCode(200);

        given().when().delete("/categories/687");
    }

    @Test
    public void modifCategorie200() {
        Categorie categorie = new Categorie();
        categorie.setId(2);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", categorie.getId())
                .body(categorie)
                .when()
                .put("/categories/{id}")
                .then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo(categorie.getId()));

    }

    @Test
    public void modifCategorie404() {
        Categorie categorie = new Categorie();
        categorie.setId(68777);


        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", categorie.getId())
                .body(categorie)
                .when()
                .put("/categories/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void rechercherCategorie200() {
        given().when().get("/categories/4")
                .then().statusCode(200);
    }

    @Test
    public void rechercherCategorie404() {
        given().when().get("/categories/35043")
                .then().statusCode(404);
    }

    @Test
    public void supprimerCategorie200() {
        given().when().delete("/categories/7")
                .then().statusCode(200);
    }

    @Test
    public void supprimerCategorie404() {
        given().when().delete("/categories/35")
                .then().statusCode(404);
    }
}
