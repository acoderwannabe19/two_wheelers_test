import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
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

        Stock stock = new Stock(3200, new Produit(98), new Magasin(78));

        given()
                .contentType(ContentType.JSON)
                .body(stock)
                .when()
                .put("/stocks")
                .then()
                .statusCode(200);

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
                .put("/stocks")
                .then()
                .statusCode(200)
                .body("magasinId/produiId", CoreMatchers.equalTo(stock.getProduit().getId()), CoreMatchers.equalTo(stock.getMagasin().getId()));

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
                .put("/commandes/{magasinId}/{produitId}")
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
