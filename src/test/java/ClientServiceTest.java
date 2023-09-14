import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import sn.ept.git.dic2.entities.Client;
import sn.ept.git.dic2.entities.Client;


import static io.restassured.RestAssured.given;

public class ClientServiceTest extends ServerTest{
    @Test
    public void listeClientsSucces() {
        given().when().get("/clients").then().statusCode(200);
    }

    @Test
    public void creerClient200() {
        Client client = new Client(5609, "Doe", "Janet", "janetdoe@example.com", "123456789", "Adresse", "Etat", "Ville", "34343");


        given()
                .contentType(ContentType.JSON)
                .body(client)
                .when()
                .put("/clients")
                .then()
                .statusCode(200);


    }



    @Test
    public void modifClient404() {
        Client client = new Client();
        client.setId(56090);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", client.getId())
                .body(client)
                .when()
                .put("/clients/{id}")
                .then()
                .statusCode(404);
    }
    @Test
    public void modifClient200() {
        Client client = new Client();
        client.setId(5609);

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .pathParam("id", client.getId())
                .body(client)
                .when()
                .put("/clients/{id}")
                .then()
                .statusCode(200);

    }

    @Test
    public void rechercherClient200() {
        given().when().get("/clients/5")
                .then().statusCode(200);
    }

    @Test
    public void rechercherClient404() {
        given().when().get("/clients/92323")
                .then().statusCode(404);
    }

    @Test
    public void supprimerClient200() {
        given().when().delete("/clients/7")
                .then().statusCode(200);
    }

    @Test
    public void supprimerClient404() {
        given().when().delete("/clients/30035")
                .then().statusCode(404);
    }

}
