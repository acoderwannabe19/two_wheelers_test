import io.restassured.http.ContentType;
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;
import sn.ept.git.dic2.entities.ArticleCommande;
import sn.ept.git.dic2.entities.Commande;
import sn.ept.git.dic2.entities.Produit;

import java.math.BigDecimal;
import java.util.Date;

import static io.restassured.RestAssured.given;

public class ArticleCommandeServiceTest extends ServerTest {
    @Test
    public void listeClientsSucces() {
        given().when().get("/articlesCommandes").then().statusCode(200);
    }

    @Test
    public void creerProduit200() {
        Commande commande = new Commande(2797, null, (short) 0, null, null, null, null, null);
        given()
                .contentType(ContentType.JSON)
                .body(commande)
                .when()
                .put("/commandes");

        ArticleCommande articleCommande =  new ArticleCommande(commande, 1908, null, 34, new BigDecimal(4333.43), new BigDecimal(23.3));

        given()
                .contentType(ContentType.JSON)
                .body(articleCommande)
                .when()
                .put("/articlesCommandes")
                .then()
                .statusCode(200);

        given().when().delete("/articlesCommandes/1908/2797");
        given().when().delete("commandes/2797");
    }

    @Test
    public void modifProduit404() {
        ArticleCommande articleCommande =  new ArticleCommande(new Commande(), 199090, null, 34, new BigDecimal(4333.43), new BigDecimal(23.3));
        articleCommande.getNumeroCommande().setNumero(54654);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("ligne", articleCommande.getLigne())
                .pathParam("numeroCommande", articleCommande.getNumeroCommande().getNumero())
                .body(articleCommande)
                .when()
                .put("/articlesCommandes/{ligne}/{numeroCommande}")
                .then()
                .statusCode(404);

    }


    @Test
    public void modifProduit200() {
        ArticleCommande articleCommande =  new ArticleCommande(new Commande(), 1, null, 34, new BigDecimal(4333.43), new BigDecimal(23.3));
        articleCommande.getNumeroCommande().setNumero(1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("ligne", articleCommande.getLigne())
                .pathParam("numeroCommande", articleCommande.getNumeroCommande().getNumero())
                .body(articleCommande)
                .when()
                .put("/articlesCommandes/{ligne}/{numeroCommande}")
                .then()
                .statusCode(200);

    }

    @Test
    public void rechercherArticleCommande200() {
        given().when().get("/articlesCommandes/1/1")
                .then().statusCode(200);
    }

    @Test
    public void rechercherArticleCommande404() {
        given().when().get("/articlesCommandes/3543/34")
                .then().statusCode(404);
    }

    @Test
    public void supprimerArticleCommande200() {
        given().when().delete("/articlesCommandes/1/1")
                .then().statusCode(200);
    }

    @Test
    public void supprimerArticleCommande404() {
        given().when().delete("/articlesCommandes/3905/1")
                .then().statusCode(404);
    }
}
