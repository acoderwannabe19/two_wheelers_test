import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;
import sn.ept.git.dic2.entities.Categorie;
import sn.ept.git.dic2.entities.Marque;
import sn.ept.git.dic2.entities.Produit;
import sn.ept.git.dic2.entities.Produit;


import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProduitServiceTest extends ServerTest{
    @Test
    public void listeProduitsSucces() {
        given().when().get("/produits").then().statusCode(200);
    }

    @Test
    public void creerProduit200() {
        Produit produit =  new Produit(3480, "Ndiolle Tall", null, null, 2022, new BigDecimal(922.34));


        given()
                .contentType(ContentType.JSON)
                .body(produit)
                .when()
                .put("/produits")
                .then()
                .statusCode(200);

        given().when().delete("/produits/3480");
    }


    @Test
    public void modifProduit404() {
        Produit produit = new Produit(3444);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", produit.getId())
                .body(produit)
                .when()
                .put("/produits/{id}")
                .then()
                .statusCode(404);

    }

    @Test
    public void modifProduit200() {
        Produit produit = new Produit(2);
        produit.setCategorieId(new Categorie(1));

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", produit.getId())
                .body(produit)
                .when()
                .put("/produits/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo(produit.getId()));

    }

    @Test
    public void rechercherProduit200() {
        given().when().get("/produits/5")
                .then().statusCode(200);
    }

    @Test
    public void rechercherProduit404() {
        given().when().get("/produits/673")
                .then().statusCode(404);
    }

    @Test
    public void supprimerProduit200() {
        given().when().delete("/produits/7")
                .then().statusCode(200);
    }

    @Test
    public void supprimerProduit404() {
        given().when().delete("/produits/350")
                .then().statusCode(404);
    }

}
