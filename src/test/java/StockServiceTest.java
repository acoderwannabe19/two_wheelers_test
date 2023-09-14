import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;
import sn.ept.git.dic2.entities.*;

import static io.restassured.RestAssured.given;

public class StockServiceTest extends ServerTest{
    @Test
    public void listeStocksSucces() {
        given().when().get("/stocks").then().statusCode(200);
    }

    @Test
    public void creerStock200() {
        Produit produit = new Produit(9866);
        given()
                .contentType(ContentType.JSON)
                .body(produit)
                .when()
                .put("/produits");

        Magasin magasin = new Magasin(997);
        given()
                .contentType(ContentType.JSON)
                .body(magasin)
                .when()
                .put("/magasins");

        Stock stock = new Stock(3209, produit, magasin);

        given()
                .contentType(ContentType.JSON)
                .body(stock)
                .when()
                .put("/stocks")
                .then()
                .statusCode(200);

        given().when().delete("/stocks/997/9866");
        given().when().delete("/magasins/997");
        given().when().delete("/produits/9866");


    }

    @Test
    public void creerStock500() {

        Stock stock = new Stock(300, new Produit(600), new Magasin(2));

        given()
                .contentType(ContentType.JSON)
                .body(stock)
                .when()
                .put("/stocks")
                .then()
                .statusCode(500);

    }

    @Test
    public void modifStock200() {
        Stock stock =  new Stock(23, new Produit(300), new Magasin(2));

        given()
                .contentType(ContentType.JSON)
                .pathParam("magasinId", stock.getMagasin().getId())
                .pathParam("produitId", stock.getProduit().getId())
                .body(stock)
                .when()
                .put("/stocks/{magasinId}/{produitId}")
                .then()
                .statusCode(200);
    }

    @Test
    public void modifStock404() {
        Stock stock = new Stock(23, new Produit(2000), new Magasin(2900));

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("magasinId", stock.getMagasin().getId())
                .pathParam("produitId", stock.getProduit().getId())
                .body(stock)
                .when()
                .put("/stocks/{magasinId}/{produitId}")
                .then()
                .statusCode(404);
    }

    @Test
    public void rechercherStock200() {
        given().when().get("/stocks/1/1")
                .then().statusCode(200);
    }

    @Test
    public void rechercherStock404() {
        given().when().get("/stocks/3543/34")
                .then().statusCode(404);
    }

    @Test
    public void supprimerStock200() {
        given().when().delete("/stocks/1/1")
                .then().statusCode(200);
    }

    @Test
    public void supprimerStock404() {
        given().when().delete("/stocks/35/1")
                .then().statusCode(404);
    }

}
