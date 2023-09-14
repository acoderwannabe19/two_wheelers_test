
import jakarta.ws.rs.core.MediaType;
import org.junit.Test;
import sn.ept.git.dic2.entities.Client;
import sn.ept.git.dic2.entities.Commande;
import sn.ept.git.dic2.entities.Employe;


import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CommandeServiceTest extends ServerTest{

    @Test
    public void listeCommandesSucces() {
        given().when().get("/commandes").then().statusCode(200);
    }

    @Test
    public void creerCommande200() {
        Commande commande = new Commande(610, null, (short) 0, new Date(1596864000000L), new Date(), new Date(), null, null);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commande)
                .when()
                .put("/commandes")
                .then()
                .statusCode(200)
                .body("numero", equalTo(commande.getNumero()));

        given().when().delete("/commandes/610");
    }

    @Test
    public void creerCommande500() {
        Commande commande = new Commande();
        commande.setNumero(1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(commande)
                .when()
                .put("/commandes")
                .then()
                .statusCode(500);
    }


    @Test
    public void ModifCommande200() {
        Commande commande = new Commande();
        Client newClient = new Client();
        Employe newEmploye = new Employe();

        commande.setNumero(1);
        commande.setClientId(newClient);
        commande.setVendeurId(newEmploye);
        commande.setStatut((short) 1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("numero", commande.getNumero())
                .body(commande)
                .when()
                .put("/commandes/{numero}")
                .then()
                .statusCode(200)
                .body("numero", equalTo(commande.getNumero()));
    }

    @Test
    public void ModifCommande404() {
        Commande commande = new Commande();
        Client newClient = new Client();
        Employe newEmploye = new Employe();

        commande.setNumero(5430);
        commande.setClientId(newClient);
        commande.setVendeurId(newEmploye);
        commande.setStatut((short) 1);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("numero", commande.getNumero())
                .body(commande)
                .when()
                .put("/commandes/{numero}")
                .then()
                .statusCode(404);
    }



    @Test
    public void rechercherCommande200() {
        given().when().get("/commandes/1")
                .then().statusCode(200);
    }

    @Test
    public void rechercherCommande404() {
        given().when().get("/commandes/303")
                .then().statusCode(404);
    }

    @Test
    public void supprimerCommande200() {
        given().when().delete("/commandes/32")
                .then().statusCode(200);
    }

    @Test
     public void supprimerCommande404() {
        given().when().delete("/commandes/1099")
                .then().statusCode(404);
    }

}
